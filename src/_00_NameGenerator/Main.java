package _00_NameGenerator;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    static Random rd = new Random();

    /**
     * #letterPairOccurrence - counts the number of times each unique letter pair appeared
     * @param text -  converted from HTML to simple text
     * @return - statistics amount
     */
    public static Map<String, Integer> letterPairOccurrence(String text) {

        //Create Map with key and the value of it occurrence
        Map<String,Integer> statistics = new HashMap<>();
        String textLower = text.toLowerCase();

        for (int i = 0; i <text.length()-1 ; i++) {

            char currentLetter = textLower.charAt(i);
            char nextLetter = textLower.charAt(i + 1);

            if (currentLetter == ' ' || nextLetter == ' ') { continue; }

            String checkedLetters = Character.toString(currentLetter) + nextLetter;

            int num = statistics.getOrDefault(checkedLetters,0);
            statistics.put(checkedLetters,num+1);

            /*if (statistics.containsKey(checkedLetters)) {
                int occurrence = statistics.get(checkedLetters);
                statistics.replace(checkedLetters, occurrence + 1);
            } else {
                statistics.put(checkedLetters, 1);
            }*/
        }
        return statistics;
    }

    /**
     * #getNextLetter - gives next letter based on what was the previous letter (weighted random choice)
     * @param statistics letter pairs with occurrence frequency
     * @param previousLetter can be null to pick first letter
     * @return next letter to add to create nickname
     */
    public static String getNextLetter(Map<String,Integer> statistics, Character previousLetter)  {

        //filtering only pairs that start with specific letter

        if (previousLetter != null) {
            statistics = statistics.entrySet().stream()
                    .filter(one -> one.getKey().charAt(0) == previousLetter)
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        }

        /*List<String> keys = new ArrayList<String>(statistics.keySet());
        int randomLettersIndex = rd.nextInt(keys.size());
        String randomLetters = keys.get(randomLettersIndex);*/

        //This function will return sum of occurrence count from list
        int sumOfValues = statistics.values().stream()
                .mapToInt(value->value)
                .sum();

        //Generate random index from the sum of all keys
        int randomIndex = rd.nextInt(sumOfValues) + 1;

        //Get the key that corresponds to the randomIndex where the probability increase with number of occurrences.
        int sumIndex = 0;
        for (Map.Entry<String,Integer> entry : statistics.entrySet()) {
            sumIndex += entry.getValue();
            if(sumIndex>=randomIndex){
                return Character.toString(entry.getKey().charAt(1));
            }
        }
        throw new RuntimeException("Something wrong with key value - might be null");
    }

    /**
     * #getRandomName builds nickname
     * @param statistics
     * @return generated nickname string
     */
    public static String getRandomName(Map<String, Integer> statistics)  {
        StringBuilder nickname = new StringBuilder();

        //Randomly picked length of nickname 3 to 8 letters long
        int nameLength = rd.nextInt((8-3) + 1)+3;

        // while nickname is too short
        do {
            Character previousLetter = nickname.length() > 0 ? nickname.charAt(nickname.length() - 1) :null;
            nickname.append(getNextLetter(statistics, previousLetter));
        }while(nickname.length()<nameLength);

        return nickname.toString();

        //throw new RuntimeException("There is no name to display");
    }

    public static void main(String[] args) throws IOException {
        URL url = new URL("https://www.gutenberg.org/files/47/47-h/47-h.htm");

        //--I got text from URL without HTML components--//
        String text = Html2Text.parse(new InputStreamReader(url.openStream()));

        //--Remove special characters, numbers and punctuation--//
        text=text.replaceAll("[^a-zA-Z\\s+]|\n", " ");

        Map<String, Integer> statistics = letterPairOccurrence(text);
        System.out.println(getRandomName(statistics));
    }
}

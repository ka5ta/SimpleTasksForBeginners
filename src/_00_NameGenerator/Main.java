package _00_NameGenerator;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;

public class Main {
    static Random rd = new Random();

    public static Map<String, Integer> letterPairOccurrence(String text) {

        //Create Map with key and the value of it occurrence
        Map<String,Integer> statistics = new HashMap<>();

        for (int i = 0; i <text.length()-1 ; i++) {

            char currentLetter = text.charAt(i);
            char nextLetter = text.charAt(i + 1);

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

    public static String getRandomKey(Map<String,Integer> statistics)  {


        /*List<String> keys = new ArrayList<String>(statistics.keySet());
        int randomLettersIndex = rd.nextInt(keys.size());
        String randomLetters = keys.get(randomLettersIndex);*/

        //This function will return sum of values form hashmap
        List<Integer> valuesList = new ArrayList<>(statistics.values());
        int sumOfValues = valuesList.stream()
                .mapToInt(value->value)
                .sum();

        //Generate random index form the sum of all keys
        int randomIndex = rd.nextInt(sumOfValues) + 1;

        //Get the key that corresponds to the randomIndex where the probability increase with number of occurrences.
        int sumIndex = 0;
        for (Map.Entry<String,Integer> key : statistics.entrySet()) {
            sumIndex += key.getValue();
            if(sumIndex>randomIndex){
                return key.getKey();
            }
        }
        throw new RuntimeException("Something wrong with key value - might be null");
    }

    public static void getRandomNames(Map<String, Integer> statistics, int howManyNamesReturn){
    //todo probably last method that will generate randomly length for names
        int nameLength = rd.nextInt(8)+1;
        getRandomKey(statistics);
    }

    public static void main(String[] args) throws IOException {

        URL url = new URL("https://www.gutenberg.org/files/47/47-h/47-h.htm");

        //--I got text from URL without HTML components--//
        String text = Html2Text.parse(new InputStreamReader(url.openStream()));

        //--Remove special characters, numbers and punctuation--//
        text=text.replaceAll("[^a-zA-Z\\s+]", "");

        Map<String, Integer> statistics = letterPairOccurrence(text);
        getRandomNames(statistics,5);

        /*statistics.entrySet().forEach(entry ->
                System.out.printf("Letters: %s, Count: %d\n",entry.getKey(),entry.getValue()));*/




    }



}

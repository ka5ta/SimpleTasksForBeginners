package _00_NameGenerator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {

        URL url = new URL("https://www.gutenberg.org/files/47/47-h/47-h.htm");

        //--I got text from URL without HTML components--//
        //String text = Html2Text.parse(new InputStreamReader(url.openStream()));

        //--Remove special characters, numbers and punctuation--//
        //text=text.replaceAll("[^a-zA-Z\\s+]", "");

        String text = "lambadaba balamba";

        //labamba balamba
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

        statistics.entrySet().forEach(entry ->
                System.out.printf("Letters: %s, Count: %d\n",entry.getKey(),entry.getValue()));

    }

}

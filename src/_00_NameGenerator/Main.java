package _00_NameGenerator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws IOException {

        URL url = new URL("https://www.gutenberg.org/files/47/47-h/47-h.htm");

        //read text returned by server
        /*BufferedReader buffer = new BufferedReader(new InputStreamReader((url.openStream())));

        StringBuffer sb = new StringBuffer();
        int line;
        // windows: \n\r
        // unix: \n
        // old mac: \r
        while((line=buffer.read()) != -1){
            sb.append((char)line);
        }
        buffer.close();*/


        Html2Text htmlConverter = new Html2Text();
        htmlConverter.parse(new InputStreamReader(url.openStream()));
        System.out.println(htmlConverter.getText());


    }

}

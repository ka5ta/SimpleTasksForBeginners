package _00_NameGenerator;
import java.io.*;
import javax.swing.text.html.*;
import javax.swing.text.html.parser.*;

/**
 * Reads HTML
 */
public class Html2Text  {

        public static String parse(Reader input) throws IOException {
            StringBuffer s = new StringBuffer();
            ParserDelegator delegator = new ParserDelegator();
            // the third parameter is TRUE to ignore charset directive
            //analyze input text and removes html part
            delegator.parse(input, new HTMLEditorKit.ParserCallback(){
                public void handleText(char[] text, int pos) {
                    s.append(text);
                }
            }, Boolean.TRUE);

            return s.toString();
        }
    }

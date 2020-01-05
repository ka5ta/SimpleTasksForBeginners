public class HowManyTimesLetterAppearsInWord {

    /**
     * Display only once the letters in a word and show where in the text they appeared
     * and how many times.
     * @param args
     */

    public static void main(String[] args) {

        String str = "KATARZYNA";
        char[] c = str.toCharArray();
        int count = 0;
        
        StringBuilder text = new StringBuilder();

        for (int i = 0; i < c.length; i++) {
            //skips loop iteration if current letter already appeared
            if (!firstOccurrence(c, i)) {
                continue;
            }
            StringBuilder sequence = new StringBuilder();
            for (int j = 0; j < c.length; j++) {
                boolean isOnCheckedPosition = false;
                if (c[i] == c[j]) {
                    count += 1;
                    isOnCheckedPosition = true;
                }
                sequence.append(isOnCheckedPosition?'1':'*');  // 10
            }
            text.append(c[i]).append(" -> ").append(sequence)
                    .append(" and appeared: ").append(count).append(" times. ").append("\n");
            count = 0;
        }
        System.out.println(text);
    }

    private static boolean firstOccurrence(char[] words, int index) {
        for (int i = 0; i < index; i++) {
            if (words[i] == words[index]) {
                return false; //when checked letter appeared already, it is not the first occurrence
            }
        }
        return true; //the checked letter didn't appear before index
    }
}

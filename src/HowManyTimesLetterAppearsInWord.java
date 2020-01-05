public class HowManyTimesLetterAppearsInWord {

    public static void main(String[] args) {

    String str = "KATARZYNA";
    char[] c = str.toCharArray();
    int count = 0;
    char positionInText='0';


    StringBuilder text = new StringBuilder();

        for (int i = 0; i <c.length ; i++) {
            if(!isFirstOccurrence(c, i)){
                continue;
            }
            StringBuilder sequence = new StringBuilder();
            for (int j = 0; j <c.length ; j++) {
                boolean isOnCheckedPosition=false;
                if(c[i]==c[j]){
                    count +=1;
                    positionInText ='1';
                    isOnCheckedPosition=true;
                }
                if(!isOnCheckedPosition){
                    positionInText='*';
                }
                sequence.append(positionInText);  // 1
            }
        text.append(c[i]).append(" -> ").append(sequence)
                .append(" and appeared: ").append(count).append(" times. ").append("\n");
            count = 0;
        }
        System.out.println(text);
    }

    private static boolean isFirstOccurrence(char[] words, int index) {
        for (int i = 0; i <index ; i++) {
            if(words[i]==words[index]){
                return false;
            }
        }
        return true;
    }
}

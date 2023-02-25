public class Chars {
    public static void main(String[] args) {

        boolean isTrue;
        String text= "bacaa";
        StringBuilder strBuilder = new StringBuilder(text);
        strBuilder.reverse();
        String invertedText = strBuilder.toString();

        if(text.equalsIgnoreCase(invertedText)){
            isTrue = true;
        }
        else{
            isTrue = false;
        }

        System.out.println(isTrue);

        System.out.println(isSameCharacters("aabaa"));


    }
    public static boolean isSameCharacters(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        char c = s.charAt(0);
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) != c) {
                return false;
            }
        }
        return true;
    }
}

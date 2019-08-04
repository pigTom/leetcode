package problem;

public class LengthOfLastWord_58 {
    public int lengthOfLastWord(String s) {
        s = s.trim();

        int wordLen = 0;
        int len = s.length() - 1;
        while (len >= 0) {
            if (s.charAt(len) == ' ')
                return wordLen;

            wordLen++;
            len--;
        }
        return wordLen;
    }

    public static void main(String[] args) {
        System.out.println(new LengthOfLastWord_58().lengthOfLastWord("Hello World"));
    }
}

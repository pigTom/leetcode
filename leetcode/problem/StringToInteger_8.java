package problem;

import java.util.ArrayList;
import java.util.function.IntConsumer;

public class StringToInteger_8 {
    class Solution {
        public int myAtoi(String str) {
            // 0 invalid string
            if (str == null)
                return 0;

            // 1 handle leading white space
            str = str.trim();
            if (str.length() == 0)
                return 0;


            // 2 handle sign
            int sign = 1;
            int start = 0;
            if (str.charAt(0) == '+')
                start = 1;

            if (str.charAt(0) == '-') {
                sign = -1;
                start = 1;
            }


            double sum = 0;
            for (int i = start; i < str.length(); i++) {
                char ch = str.charAt(i);
                if (!Character.isDigit(ch))
                    break;
                sum = 10 * sum + (ch - '0');

            }
            if (sign == -1) {
                sum = - sum;
            }
            if (sum >= Integer.MAX_VALUE) {
                sum = Integer.MAX_VALUE;
            } else if (sum <= Integer.MIN_VALUE) {
                sum = Integer.MIN_VALUE;
            }

            return (int)sum;

        }
        public boolean isNumber(char ch) {
            int dis = ch - '0';
            char[] s = new char[10];
            new ArrayList<Integer>().size();
            if (dis >= 0 && dis <= 9) {
                return true;
            }
            return false;
        }
    }

    public static void main(String[] args) {
        StringToInteger_8 stringToInteger_8 = new StringToInteger_8();
        System.out.println(stringToInteger_8.new Solution().myAtoi("-1234567890123456789d"));
    }
}

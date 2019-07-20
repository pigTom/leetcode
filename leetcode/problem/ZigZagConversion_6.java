package problem;

/***
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of
 * rows like this: (you may want to display this pattern in a fixed font for better legibility)
 *
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * And then read line by line: "PAHNAPLSIIGYIR"
 *
 * Write the code that will take a string and make this conversion given a number of rows:
 *
 * string convert(string s, int numRows);
 * Example 1:
 *
 * Input: s = "PAYPALISHIRING", numRows = 3
 * Output: "PAHNAPLSIIGYIR"
 * Example 2:
 *
 * Input: s = "PAYPALISHIRING", numRows = 4
 * Output: "PINALSIGYAHRPI"
 * Explanation:
 *
 * P     I    N
 * A   L S  I G
 * Y A   H R (numRows - 1 - i) + numRows - 1
 * P     I
 */
public class ZigZagConversion_6 {
    public String convert(String s, int numRows) {
        int len = s.length();
        int t = 2 * numRows - 2;
        char[] ch = new char[len];
        int charIndex = 0;
        for (int i = 0; i < numRows; i++) {
            int index = i;
            while (index < len) {
                int in = index % t;
                // there are two situations
                // 1. i
                ch[charIndex++] = s.charAt(index);
                if (i != 0 && i != numRows - 1) {
                    int index2 = index / t * t + 2 * numRows - i - 2;
                    if (index2 < len) {
                        ch[charIndex++] = s.charAt(index2);
                    }
                }
                index += t;
            }
        }
        return new String(ch);
    }

    public static void main(String[] args) {
        String s = "PAYPALISHIRING";
        int size = 3;
        System.out.println(new ZigZagConversion_6().convert(s, size));
    }

}

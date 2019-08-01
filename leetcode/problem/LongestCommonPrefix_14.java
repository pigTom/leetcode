package problem;

public class LongestCommonPrefix_14 {
    public String longestCommonPrefix(String[] strs) {
        int len = Integer.MAX_VALUE;
        if (strs == null || strs.length == 0)
            return "";

        for (String s : strs) {
            if (s == null || s.length() == 0)
                return "";
            if (s.length() < len)
                len = s.length();
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            char temp = strs[0].charAt(i);
            for(String s : strs) {
                if (s.charAt(i) != temp)
                    return sb.toString();
            }
            sb.append(temp);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String[] strs = {"aabb","abb","aa12bb"};
        System.out.println(new LongestCommonPrefix_14().longestCommonPrefix(strs));
    }
}

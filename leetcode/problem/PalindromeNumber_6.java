package problem;

public class PalindromeNumber_6 {
    public boolean isPalindrome(int x) {
        // int to String
        char[] chars = Integer.toString(x).toCharArray();
        int front = 0;
        int rear = chars.length - 1;
        while (front < rear) {
            if (chars[front] != chars[rear]) {
                return false;
            }
            rear --;
            front ++;
        }
        return true;
    }

    public boolean isPalindrome2(int x) {
        int sum = 0;
        int original = x;
        while (x > 0) {
            // 得到末尾的数
            sum = 10 * sum + x % 10;
            x /= 10;// x移除末尾的数
        }


        return original == sum;
    }

    public static void main(String[] args) {
        int i = 11021011;
        System.out.println(new PalindromeNumber_6().isPalindrome2(i));
    }
}

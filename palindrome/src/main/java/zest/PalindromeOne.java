package zest;

public class PalindromeOne {

    public static boolean isPalindrome(int x) {
        // convert input into an array and rest is nothing but a simple two pointer solution
        if(x<-Math.pow(2, 20)) throw new IllegalArgumentException("x is less than -2^20");
        if(x>Math.pow(2, 20)) throw new IllegalArgumentException("x is greater than 2^20-1");

        char[] numbers = String.valueOf(x).toCharArray();
        int start = 0;
        int end = numbers.length - 1;
        while (start < end) {
            if (numbers[start] != numbers[end]) return false;
            start++;
            end--;
        }
        return true;
    }
}

class Solution {
    public int reverse(int x) {
        int rev = 0;
        while(x != 0) {
            int lastDigit = x % 10;
            //positive overflow condition
            if(rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE && lastDigit > 7)) {
                return 0;
            }
            //negative overflow condition
            if(rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE && lastDigit < -8)) {
                return 0;
            }
            rev = (rev * 10) + lastDigit;
            x = x / 10;
        }
        return rev;
    }
}
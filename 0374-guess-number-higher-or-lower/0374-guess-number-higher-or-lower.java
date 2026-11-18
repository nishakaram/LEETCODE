
/**
 * Forward declaration of guess API.
 * @param num your guess
 * @return -1 if num is higher than pick
 *          1 if num is lower than pick
 *          0 if num is equal to pick
 * int guess(int num);
 */

public class Solution extends GuessGame {
    public int guessNumber(int n) {

        int low = 1;
        int high = n;

        while (low <= high) {

            int mid = low + (high - low) / 2;

            int result = guess(mid);

            if (result == 0) {
                return mid;
            }

            else if (result == -1) {
                high = mid - 1;
            }

            else {
                low = mid + 1;
            }
        }

        return -1;
    }
}

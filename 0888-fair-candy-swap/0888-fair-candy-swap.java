
import java.util.Arrays;

class Solution {
    public int[] fairCandySwap(int[] aliceSizes, int[] bobSizes) {

        int sumAlice = 0;
        int sumBob = 0;

        // Calculate total candies
        for (int x : aliceSizes) {
            sumAlice += x;
        }

        for (int y : bobSizes) {
            sumBob += y;
        }

        // Difference required for equal exchange
        int diff = (sumBob - sumAlice) / 2;

        // Sort Bob's array for Binary Search
        Arrays.sort(bobSizes);

        // Find the correct pair
        for (int x : aliceSizes) {

            int target = x + diff;

            int low = 0;
            int high = bobSizes.length - 1;

            while (low <= high) {

                int mid = low + (high - low) / 2;

                if (bobSizes[mid] == target) {
                    return new int[]{x, bobSizes[mid]};
                }

                else if (bobSizes[mid] < target) {
                    low = mid + 1;
                }

                else {
                    high = mid - 1;
                }
            }
        }

        return new int[]{};
    }
}

class Solution {
    public int numOfSubarrays(int[] arr) {
        final int MOD = 1_000_000_007;
        long odd = 0, even = 0, ans = 0;

        for (int x : arr) {
            if (x % 2 == 1) {
                long newOdd = even + 1;
                long newEven = odd;
                odd = newOdd;
                even = newEven;
            } else {
                even = even + 1;
            }
            ans = (ans + odd) % MOD;
        }

        return (int) ans;
    }
}
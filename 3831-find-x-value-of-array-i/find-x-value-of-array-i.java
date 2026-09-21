class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] result = new long[k];
        long[] state = new long[k];
        for (int value : nums) {
            int rem = value % k;
            long[] nextState = new long[k];
            for (int r = 0; r < k; r++) {
                int newRem = (r * rem) % k;
                nextState[newRem] += state[r];
                result[newRem] += state[r];
            }
            nextState[rem]++;
            result[rem]++;
            state = nextState;
        }
        return result;
    }
}
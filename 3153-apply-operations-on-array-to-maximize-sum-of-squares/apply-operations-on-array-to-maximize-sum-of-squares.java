class Solution {
    public int maxSum(List<Integer> nums, int k) {
        List<Long> base = new ArrayList<>();
        long x = 1;

        while (x <= 1000000000) {
            base.add(x);
            x <<= 1;
        }

        int nb = base.size();
        int[] count = new int[nb];

        for (int num : nums) {
            for (int i = 0; i < nb; i++) {
                if ((num & base.get(i)) != 0)
                    count[i]++;
            }
        }

        long[] values = new long[k];

        for (int i = 0; i < nb; i++) {
            for (int j = 0; j < Math.min(k, count[i]); j++) {
                values[j] += base.get(i);
            }
        }

        long res = 0;
        final long mod = 1000000007;

        for (long y : values) {
            res = (res + y * y) % mod;
        }

        return (int) res;
    }
}
class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int right = nums.length - 1;
        long total_cost = 0;
        Deque<int[]> q = new ArrayDeque<>();
        long res = 0;

        for (int left = nums.length - 1; left >= 0; left--) {
            int x = nums[left];
            int c = 1;

            while (!q.isEmpty() && q.peekFirst()[0] < x) {
                int[] node = q.pollFirst();
                int value = node[0];
                int count = node[1];

                total_cost += (long)(x - value) * count;
                c += count;
            }

            q.offerFirst(new int[]{x, c});

            while (total_cost > k) {
                int[] node = q.pollLast();
                int value = node[0];
                int count = node[1];

                total_cost -= (long)value - nums[right];
                right--;

                if (count > 1) {
                    q.offerLast(new int[]{value, count - 1});
                }
            }

            res += right - left + 1;
        }

        return res;
    }
}
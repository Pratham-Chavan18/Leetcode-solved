class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        int left = 0;
        int res = 0;

        Map<Integer, Integer> count = new HashMap<>();
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            count.put(
                nums[i],
                count.getOrDefault(nums[i], 0) + 1
            );

            while (count.get(nums[i]) > k) {
                count.put(
                    nums[left],
                    count.get(nums[left]) - 1
                );

                left++;
            }

            res = Math.max(res, i - left + 1);
        }

        return res;
    }
}
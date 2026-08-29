class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {

        int n = nums.length;

        // Store value and original index
        int[][] arr = new int[n][2];

        for (int i = 0; i < n; i++) {
            arr[i][0] = nums[i];  // value
            arr[i][1] = i;        // original index
        }

        // Sort by value
        Arrays.sort(arr, (a, b) -> a[0] - b[0]);

        int[] result = new int[n];

        int start = 0;

        while (start < n) {

            int end = start;

            // Find all elements belonging to the same group
            while (end + 1 < n &&
                   arr[end + 1][0] - arr[end][0] <= limit) {
                end++;
            }

            // Store original indices of this group
            List<Integer> indices = new ArrayList<>();

            for (int i = start; i <= end; i++) {
                indices.add(arr[i][1]);
            }

            // Sort indices
            Collections.sort(indices);

            // Put smaller values at smaller indices
            for (int i = 0; i < indices.size(); i++) {
                result[indices.get(i)] = arr[start + i][0];
            }

            // Move to the next group
            start = end + 1;
        }

        return result;
    }
}
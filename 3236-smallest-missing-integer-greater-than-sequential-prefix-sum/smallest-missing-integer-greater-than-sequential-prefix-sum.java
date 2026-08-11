class Solution {
    public int missingInteger(int[] nums) {
        int sum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1] + 1) {
                sum += nums[i];
            } else {
                break;
            }
        }

        java.util.HashSet<Integer> seen = new java.util.HashSet<>();

        for (int num : nums) {
            seen.add(num);
        }

        int answer = sum;

        while (seen.contains(answer)) {
            answer++;
        }

        return answer;
    }
}
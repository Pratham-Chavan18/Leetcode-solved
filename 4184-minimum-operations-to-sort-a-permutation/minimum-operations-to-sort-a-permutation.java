class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length, shift = 0;
        while (nums[shift] != 0) shift++;

        boolean along = true, against = true;
        for (int k = 0; k < n && (along || against); k++) {
            int ring = nums[(shift + k) % n];  // the circle, read from 0
            along &= ring == k;  // counts up along the arrow
            against &= ring == (n - k) % n;  // counts up against the arrow
        }
        if (along) return Math.min(shift, n - shift + 2);  // walk ahead, or turn, walk back, turn back
        if (against) return Math.min(shift + 2, n - shift);  // walk ahead and turn, or turn and walk back
        return -1;
    }
}
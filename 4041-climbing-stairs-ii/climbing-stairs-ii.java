class Solution {
    public int climbStairs(int n, int[] costs) {
        int f0 = 0, f1 = 0, f2 = 0;

        for (int c : costs) {
            int next = Math.min(
                f0 + 9,
                Math.min(f1 + 4, f2 + 1)
            ) + c;

            f0 = f1;
            f1 = f2;
            f2 = next;
        }

        return f2;
    }
}
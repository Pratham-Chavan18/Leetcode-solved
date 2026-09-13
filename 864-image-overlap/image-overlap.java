class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        int result = 0;

        for (int dx = -n + 1; dx < n; dx++) {
            for (int dy = -n + 1; dy < n; dy++) {
                int score = 0;

                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        int newI = i + dx;
                        int newJ = j + dy;

                        if (0 <= newI && newI < n &&
                            0 <= newJ && newJ < n) {
                            score += img1[newI][newJ] & img2[i][j];
                        }
                    }
                }

                result = Math.max(result, score);
            }
        }

        return result;
    }
}
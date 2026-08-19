class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> rows = new HashMap<>();

        for (int[] r : reservedSeats) {
            int row = r[0];
            int seat = r[1];

            if (seat == 1 || seat == 10) {
                continue;
            }

            int mask = rows.getOrDefault(row, 0);
            mask |= 1 << seat;
            rows.put(row, mask);
        }

        int total = (n - rows.size()) * 2;

        int leftMask = 0;
        int middleMask = 0;
        int rightMask = 0;

        for (int seat = 2; seat <= 5; seat++) {
            leftMask |= 1 << seat;
        }

        for (int seat = 4; seat <= 7; seat++) {
            middleMask |= 1 << seat;
        }

        for (int seat = 6; seat <= 9; seat++) {
            rightMask |= 1 << seat;
        }

        for (int mask : rows.values()) {
            boolean leftFree = (mask & leftMask) == 0;
            boolean middleFree = (mask & middleMask) == 0;
            boolean rightFree = (mask & rightMask) == 0;

            if (leftFree && rightFree) {
                total += 2;
            } else if (leftFree || middleFree || rightFree) {
                total += 1;
            }
        }

        return total;
    }
}
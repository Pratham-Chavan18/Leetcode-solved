class Solution {
    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }

        return a;
    }

    public String smallestNumber(String num, long t) {
        long temp = t;

        for (int digit = 2; digit <= 9; digit++) {
            while (temp % digit == 0) {
                temp /= digit;
            }
        }

        if (temp != 1) {
            return "-1";
        }

        int n = num.length();
        char[] digits = num.toCharArray();

        long[] remaining = new long[n + 1];
        remaining[0] = t;

        int lastValidPos = n - 1;

        for (int i = 0; i < n; i++) {
            int digit = digits[i] - '0';

            if (digit == 0) {
                lastValidPos = i;
                break;
            }

            long common = gcd(remaining[i], digit);
            remaining[i + 1] = remaining[i] / common;
        }

        if (remaining[n] == 1) {
            return num;
        }

        for (int i = lastValidPos; i >= 0; i--) {
            int currentDigit = digits[i] - '0';

            for (int newDigit = currentDigit + 1;
                 newDigit <= 9;
                 newDigit++) {

                digits[i] = (char) ('0' + newDigit);

                long need = remaining[i];
                need /= gcd(need, newDigit);

                char[] suffix = new char[n - i - 1];
                int suffixSize = 0;

                for (int j = i + 1; j < n; j++) {
                    int chosenDigit = 9;

                    while (chosenDigit > 1 &&
                           need % chosenDigit != 0) {
                        chosenDigit--;
                    }

                    if (need % chosenDigit == 0) {
                        need /= chosenDigit;
                    }

                    suffix[suffixSize++] =
                        (char) ('0' + chosenDigit);
                }

                if (need == 1) {
                    for (int a = 0, b = suffixSize - 1;
                         a < b;
                         a++, b--) {

                        char tmp = suffix[a];
                        suffix[a] = suffix[b];
                        suffix[b] = tmp;
                    }

                    for (int j = i + 1; j < n; j++) {
                        digits[j] = suffix[j - i - 1];
                    }

                    return new String(digits);
                }
            }

            digits[i] = num.charAt(i);
        }

        StringBuilder factors = new StringBuilder();
        long remainingT = t;

        for (int digit = 9; digit >= 2; digit--) {
            while (remainingT % digit == 0) {
                factors.append(digit);
                remainingT /= digit;
            }
        }

        int requiredLength =
            Math.max(n + 1, factors.length());

        while (factors.length() < requiredLength) {
            factors.append('1');
        }

        return factors.reverse().toString();
    }
}
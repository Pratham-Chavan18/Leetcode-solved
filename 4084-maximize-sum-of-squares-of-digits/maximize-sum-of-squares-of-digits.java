class Solution {
    public String maxSumOfSquares(int num, int sum) {
        if (sum > 9 * num)
            return "";
        int k = sum / 9;
        int r = sum % 9;
        StringBuilder sb = new StringBuilder(num);
        for (int i = 0; i < k; i++)
            sb.append('9');
        if (r > 0)
            sb.append((char) ('0' + r));
        while (sb.length() < num)
            sb.append('0');
        return sb.toString();
    }
}
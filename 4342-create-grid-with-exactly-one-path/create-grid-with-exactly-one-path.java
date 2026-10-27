class Solution {
    public String[] createGrid(int m, int n) {
        String[] result = new String[m];
        String rest = "#".repeat(n -1) + ".";
        result[0] = ".".repeat(n);
        for (int i =1; i <m; i++) {
            result[i] = rest;
        }
        return result;
    }
}
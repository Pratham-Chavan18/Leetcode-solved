class Solution {
    private List<String> ans = new ArrayList<>();

    private void backtrack(StringBuilder s, int open, int close, int n) {

        if (s.length() == 2 * n) {
            ans.add(s.toString());
            return;
        }
        if (open < n) {
            s.append('(');

            backtrack(s, open + 1, close, n);

            s.deleteCharAt(s.length() - 1);
        }

        if (close < open) {
            s.append(')');

            backtrack(s, open, close + 1, n);

            s.deleteCharAt(s.length() - 1);
        }
    }

    public List<String> generateParenthesis(int n) {
        ans.clear();

        StringBuilder s = new StringBuilder(2 * n);

        backtrack(s, 0, 0, n);

        return ans;
    }
}
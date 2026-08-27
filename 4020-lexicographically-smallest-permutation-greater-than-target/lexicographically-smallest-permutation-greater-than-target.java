class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] cnt = new int[26];

        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }

        for (int i = n - 1; i >= 0; i--) {

            int[] remain = cnt.clone();
            boolean possible = true;

            for (int j = 0; j < i; j++) {
                int x = target.charAt(j) - 'a';

                if (remain[x] == 0) {
                    possible = false;
                    break;
                }

                remain[x]--;
            }

            if (!possible)
                continue;

            int targetChar = target.charAt(i) - 'a';

            for (int c = targetChar + 1; c < 26; c++) {

                if (remain[c] == 0)
                    continue;

                StringBuilder ans = new StringBuilder(target.substring(0, i));

                ans.append((char) ('a' + c));

                remain[c]--;
                for (int x = 0; x < 26; x++) {
                    for (int t = 0; t < remain[x]; t++) {
                        ans.append((char) ('a' + x));
                    }
                }

                return ans.toString();
            }
        }

        return "";
    }
}
class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        
        int[] cnt = new int[26];
        for(char c : s.toCharArray()) {
            cnt[c-'a']++;
        }
        
        int oddcnt = 0;
        char oddch = 0;
        for(int i = 0; i < 26; i++) {
            if(cnt[i]%2 != 0) {
                oddcnt++;
                oddch = (char) ('a'+i);
            }
        }
        if(oddcnt > 1) return "";
        int n = s.length();
        int halflen = n/2;
        int[] halfcnt = new int[26];
        for(int i = 0; i < 26; i++) {
            halfcnt[i] = cnt[i]/2;
        }
        
        char[] half = new char[halflen];
        if(!backtrack(half, 0, halfcnt, target, false, oddch, n)) {
            return "";
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append(half);
        if(n%2 != 0) {
            sb.append(oddch);
        }
        for(int i = halflen-1; i >= 0; i--) {
            sb.append(half[i]);
        }
        return sb.toString();
    }

    private boolean backtrack(char[] half, int idx, int[] cnt, String target, boolean isGreater, char oddch, int n) {
        if(idx == half.length) {
            
            StringBuilder full = new StringBuilder();
            full.append(half);
            if(n%2 != 0) {
                full.append(oddch);
            }
            for(int i = half.length-1; i >= 0; i--) {
                full.append(half[i]);
            }
            return isGreater || full.toString().compareTo(target) > 0;
        }
        char targetch = target.charAt(idx);
        
        for(int i = 0; i < 26; i++) {
            if(cnt[i] > 0) {
                char curr = (char) ('a'+i);
                
                if(!isGreater && curr < targetch) {
                    continue;
                }
                cnt[i]--;
                half[idx] = curr;
                boolean nextGreater = isGreater || (curr > targetch);
                if(backtrack(half, idx+1, cnt, target, nextGreater, oddch, n)) {
                    return true;
                }
                
                cnt[i]++;
            }
        }
        return false;
    }
}
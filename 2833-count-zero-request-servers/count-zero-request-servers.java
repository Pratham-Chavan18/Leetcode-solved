
class Solution {

    public int[] countServers(int n, int[][] logs, int x, int[] queries) {

        Arrays.sort(logs, (a, b) ->
            Integer.compare(a[1], b[1])
        );

        int q = queries.length;
        int[] ans = new int[q];
        Integer[] order = new Integer[q];

        for (int i = 0; i < q; i++) {
            order[i] = i;
        }

        Arrays.sort(order, (a, b) ->
            Integer.compare(queries[a], queries[b])
        );
   
       int[] freq = new int[n+1];
       int left =0;
       int right = 0;
       int active = 0;

       for(int idx : order){
          int time = queries[idx];
          int start = queries[idx] - x;

          while(right < logs.length && logs[right][1] <= time){
              int id = logs[right][0];
            
              if(freq[id] ==0 ) active++;

              freq[id]++;
              right++;
          }
          while(left < logs.length && logs[left][1] < start ){
            int id = logs[left][0];

            freq[id]--;

            if(freq[id] == 0) active--;

            left++;
          }

          ans[idx] = n- active;
       }
     return ans;
    }
}
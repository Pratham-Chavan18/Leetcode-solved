class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        Arrays.sort(nums);
        List<Integer>ans=new ArrayList<>();
        int end=nums [nums.length-1],index=0,cur=nums[0];
        while(cur<end){
            if(nums[index]==cur){
                cur++;
                index++;
                continue;
            }
            else{
                while(cur<nums [index]){
                    ans.add(cur++);
                }
                index++;
                cur++;
            }
        }
        return ans;
    }
}
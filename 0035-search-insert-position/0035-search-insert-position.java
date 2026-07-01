class Solution {
    public int searchInsert(int[] nums, int x) {
        int n = nums.length;
        int ans = n;
       int low =0,high = n-1;
       while(low<=high){
        int mid = (low+high)/2;
        if(nums[mid]>=x){
            ans=mid;
            high=mid-1;
        }else{
            low = mid+1;
        }
       }return ans;
    }
}
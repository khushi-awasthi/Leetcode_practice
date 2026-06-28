class Solution {
    void reverse(int[] nums, int i, int j) {
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

  void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public void nextPermutation(int[] nums) {
        int dropPoint = -1;

        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i - 1] < nums[i]) {
                dropPoint = i - 1;
                break;
            }
        }

        if (dropPoint == -1) {
            reverse(nums, 0, nums.length - 1);
            return;
        }

        int rightSmallestGreaterThenDP = dropPoint + 1;
        for (int i = dropPoint + 2; i < nums.length; i++) {
            if (nums[i] <= nums[rightSmallestGreaterThenDP] && nums[i] > nums[dropPoint]) {
                rightSmallestGreaterThenDP = i;
            }
        }

        swap(nums, dropPoint, rightSmallestGreaterThenDP);
        reverse(nums, dropPoint + 1, nums.length - 1);
    }
}
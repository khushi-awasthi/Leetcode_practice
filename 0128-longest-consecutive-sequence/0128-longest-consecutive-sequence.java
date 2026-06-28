import java.util.HashSet;

class Solution {
    public int longestConsecutive(int[] nums) {

        int n = nums.length;
        if (n == 0) return 0;

        int longest = 1;

        HashSet<Integer> set = new HashSet<>();

        // Insert all elements into the HashSet
        for (int num : nums) {
            set.add(num);
        }

        // Traverse the HashSet
        for (int it : set) {

            // Check if it is the start of a sequence
            if (!set.contains(it - 1)) {

                int cnt = 1;
                int x = it;

                while (set.contains(x + 1)) {
                    x++;
                    cnt++;
                }

                longest = Math.max(longest, cnt);
            }
        }

        return longest;
    }
}
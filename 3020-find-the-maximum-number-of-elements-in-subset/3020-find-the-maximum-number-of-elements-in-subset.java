import java.util.*;

class Solution {
    public int maximumLength(int[] nums) {

        HashMap<Long, Integer> freq = new HashMap<>();

        for (int x : nums) {
            freq.put((long)x, freq.getOrDefault((long)x, 0) + 1);
        }

        int ans = 1;

        // Special case for 1
        if (freq.containsKey(1L)) {
            int c = freq.get(1L);
            ans = Math.max(ans, (c % 2 == 0) ? c - 1 : c);
        }

        for (long start : freq.keySet()) {

            if (start == 1) continue;

            long cur = start;
            int len = 0;

            while (true) {

                int cnt = freq.getOrDefault(cur, 0);

                if (cnt == 0) {
                    // Current doesn't exist
                    len--;
                    break;
                }

                if (cnt == 1) {
                    // Current becomes center
                    len++;
                    break;
                }

                // cnt >= 2

                // Prevent overflow
                if (cur > 1000000000L / cur) {
                    len++;
                    break;
                }

                long next = cur * cur;

                // If next doesn't exist, current should be center
                if (!freq.containsKey(next)) {
                    len++;
                    break;
                }

                // Use current as pair
                len += 2;
                cur = next;
            }

            ans = Math.max(ans, len);
        }

        return ans;
    }
}
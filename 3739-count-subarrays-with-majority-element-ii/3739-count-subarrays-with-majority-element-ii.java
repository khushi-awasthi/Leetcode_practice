import java.util.*;

class Solution {

    class Fenwick {
        int[] bit;

        Fenwick(int n) {
            bit = new int[n + 2];
        }

        void update(int idx, int val) {
            while (idx < bit.length) {
                bit[idx] += val;
                idx += idx & -idx;
            }
        }

        int query(int idx) {
            int sum = 0;
            while (idx > 0) {
                sum += bit[idx];
                idx -= idx & -idx;
            }
            return sum;
        }
    }

    public long countMajoritySubarrays(int[] nums, int target) {

        int n = nums.length;

        int[] prefix = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            if (nums[i - 1] == target)
                prefix[i] = prefix[i - 1] + 1;
            else
                prefix[i] = prefix[i - 1] - 1;
        }

        int[] sorted = prefix.clone();
        Arrays.sort(sorted);

        HashMap<Integer, Integer> rank = new HashMap<>();

        int id = 1;

        for (int x : sorted) {
            if (!rank.containsKey(x)) {
                rank.put(x, id++);
            }
        }

        Fenwick bit = new Fenwick(id + 2);

        long ans = 0;

        for (int x : prefix) {

            int r = rank.get(x);

            ans += bit.query(r - 1);

            bit.update(r, 1);
        }

        return ans;
    }
}
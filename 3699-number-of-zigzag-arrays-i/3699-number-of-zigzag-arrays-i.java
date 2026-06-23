class Solution {
    static final int MOD = 1_000_000_007;

    public int zigZagArrays(int n, int l, int r) {

        int m = r - l + 1;

        long[] up = new long[m + 1];
        long[] down = new long[m + 1];

        // Length = 2 initialization
        for (int v = 1; v <= m; v++) {
            up[v] = v - 1;      // previous value < v
            down[v] = m - v;    // previous value > v
        }

        // Build lengths 3..n
        for (int len = 3; len <= n; len++) {

            long[] newUp = new long[m + 1];
            long[] newDown = new long[m + 1];

            long[] prefDown = new long[m + 1];
            long[] prefUp = new long[m + 1];

            for (int i = 1; i <= m; i++) {
                prefDown[i] = (prefDown[i - 1] + down[i]) % MOD;
                prefUp[i] = (prefUp[i - 1] + up[i]) % MOD;
            }

            long totalUp = prefUp[m];

            for (int y = 1; y <= m; y++) {

                // previous sign must be DOWN and previous value < y
                newUp[y] = prefDown[y - 1];

                // previous sign must be UP and previous value > y
                newDown[y] =
                        (totalUp - prefUp[y] + MOD) % MOD;
            }

            up = newUp;
            down = newDown;
        }

        long ans = 0;

        if (n == 2) {
            for (int v = 1; v <= m; v++) {
                ans = (ans + up[v] + down[v]) % MOD;
            }
        } else {
            for (int v = 1; v <= m; v++) {
                ans = (ans + up[v] + down[v]) % MOD;
            }
        }

        return (int) ans;
    }
}
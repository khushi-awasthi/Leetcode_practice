import java.util.*;

class Solution {

    int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};

    public int maximumSafenessFactor(List<List<Integer>> grid) {

        int n = grid.size();

        int[][] dist = new int[n][n];
        for (int[] row : dist)
            Arrays.fill(row, -1);

        Queue<int[]> q = new LinkedList<>();

        // Multi-source BFS
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid.get(i).get(j) == 1) {
                    dist[i][j] = 0;
                    q.offer(new int[]{i, j});
                }
            }
        }

        while (!q.isEmpty()) {

            int[] cur = q.poll();

            int x = cur[0];
            int y = cur[1];

            for (int[] d : dirs) {

                int nx = x + d[0];
                int ny = y + d[1];

                if (nx < 0 || ny < 0 || nx >= n || ny >= n)
                    continue;

                if (dist[nx][ny] != -1)
                    continue;

                dist[nx][ny] = dist[x][y] + 1;
                q.offer(new int[]{nx, ny});
            }
        }

        // Max Heap
        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (a, b) -> b[2] - a[2]);

        boolean[][] vis = new boolean[n][n];

        pq.offer(new int[]{0, 0, dist[0][0]});
        vis[0][0] = true;

        while (!pq.isEmpty()) {

            int[] cur = pq.poll();

            int x = cur[0];
            int y = cur[1];
            int safe = cur[2];

            if (x == n - 1 && y == n - 1)
                return safe;

            for (int[] d : dirs) {

                int nx = x + d[0];
                int ny = y + d[1];

                if (nx < 0 || ny < 0 || nx >= n || ny >= n)
                    continue;

                if (vis[nx][ny])
                    continue;

                vis[nx][ny] = true;

                pq.offer(new int[]{
                        nx,
                        ny,
                        Math.min(safe, dist[nx][ny])
                });
            }
        }

        return 0;
    }
}
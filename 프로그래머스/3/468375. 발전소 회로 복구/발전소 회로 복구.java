import java.util.*;

class Node {
    int x, y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Solution {
    static final int INF = 1_000_000_000;

    int n, m;
    int[] dx = {1, 0, -1, 0};
    int[] dy = {0, 1, 0, -1};

    public int solution(int h, String[] grid, int[][] panels, int[][] seqs) {
        n = grid.length;
        m = grid[0].length();
        int k = panels.length;

        int elevatorX = -1;
        int elevatorY = -1;

        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++) {
                if (grid[x].charAt(y) == '@') {
                    elevatorX = x;
                    elevatorY = y;
                }
            }
        }

        int[][][] distances = new int[k][][];

        for (int i = 0; i < k; i++) {
            distances[i] = bfs(
                panels[i][1] - 1,
                panels[i][2] - 1,
                grid
            );
        }

        int[][] move = new int[k][k];

        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                move[i][j] = INF;

                int targetX = panels[j][1] - 1;
                int targetY = panels[j][2] - 1;

                if (panels[i][0] == panels[j][0]) {
                    int distance = distances[i][targetX][targetY];

                    if (distance != -1) {
                        move[i][j] = distance;
                    }
                } else {
                    int toElevator =
                        distances[i][elevatorX][elevatorY];

                    int fromElevator =
                        distances[j][elevatorX][elevatorY];

                    if (toElevator != -1 && fromElevator != -1) {
                        move[i][j] =
                            toElevator
                            + Math.abs(panels[i][0] - panels[j][0])
                            + fromElevator;
                    }
                }
            }
        }

        int[] required = new int[k];

        for (int[] seq : seqs) {
            int before = seq[0] - 1;
            int after = seq[1] - 1;

            required[after] |= 1 << before;
        }

        int stateCount = 1 << k;
        int[][] dp = new int[stateCount][k];

        for (int[] row : dp) {
            Arrays.fill(row, INF);
        }

        dp[0][0] = 0;

        for (int mask = 0; mask < stateCount; mask++) {
            for (int current = 0; current < k; current++) {
                if (dp[mask][current] == INF) continue;

                // 다음으로 활성화할 패널 선택
                for (int next = 0; next < k; next++) {
                    // 이미 켜진 패널은 제외
                    if ((mask & (1 << next)) != 0) continue;

                    // 선행 조건을 충족하지 못하면 제외
                    if ((mask & required[next]) != required[next]) {
                        continue;
                    }

                    if (move[current][next] == INF) continue;

                    int nextMask = mask | (1 << next);
                    int nextTime =
                        dp[mask][current] + move[current][next];

                    dp[nextMask][next] = Math.min(
                        dp[nextMask][next],
                        nextTime
                    );
                }
            }
        }

        int answer = INF;
        int allActivated = stateCount - 1;

        for (int current = 0; current < k; current++) {
            answer = Math.min(answer, dp[allActivated][current]);
        }

        return answer;
    }

    public int[][] bfs(int startX, int startY, String[] grid) {
        int[][] distance = new int[n][m];

        for (int[] row : distance) {
            Arrays.fill(row, -1);
        }

        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(startX, startY));
        distance[startX][startY] = 0;

        while (!q.isEmpty()) {
            Node temp = q.poll();

            for (int i = 0; i < 4; i++) {
                int x = temp.x + dx[i];
                int y = temp.y + dy[i];

                if (x < 0 || x >= n || y < 0 || y >= m) continue;
                if (grid[x].charAt(y) == '#') continue;
                if (distance[x][y] != -1) continue;

                distance[x][y] = distance[temp.x][temp.y] + 1;
                q.offer(new Node(x, y));
            }
        }

        return distance;
    }
}
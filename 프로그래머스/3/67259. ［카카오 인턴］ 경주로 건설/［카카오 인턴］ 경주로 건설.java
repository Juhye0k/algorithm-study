import java.util.*;

class Solution {
    static class Node {
        int x;
        int y;
        int cost;
        int dir;

        Node(int x, int y, int cost, int dir) {
            this.x = x;
            this.y = y;
            this.cost = cost;
            this.dir = dir;
        }
    }

    public int solution(int[][] board) {
        int n = board.length;

        // 방향: 위, 오른쪽, 아래, 왼쪽
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        int[][][] dist = new int[n][n][4];

        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                Arrays.fill(dist[x][y], Integer.MAX_VALUE);
            }
        }

        PriorityQueue<Node> q = new PriorityQueue<>(
            (a, b) -> Integer.compare(a.cost, b.cost)
        );

        // 출발점에서는 아직 이동 방향이 없음
        q.add(new Node(0, 0, 0, -1));
        Arrays.fill(dist[0][0], 0);

        while (!q.isEmpty()) {
            Node current = q.poll();

            // 더 저렴한 경로가 이미 발견된 오래된 후보는 제외
            if (current.dir != -1
                    && current.cost > dist[current.x][current.y][current.dir]) {
                continue;
            }

            // 비용이 작은 순서로 꺼내므로 첫 도착이 최소 비용
            if (current.x == n - 1 && current.y == n - 1) {
                return current.cost;
            }

            for (int dir = 0; dir < 4; dir++) {
                int nx = current.x + dx[dir];
                int ny = current.y + dy[dir];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                    continue;
                }

                if (board[nx][ny] == 1) {
                    continue;
                }

                int moveCost;

                if (current.dir == -1 || current.dir == dir) {
                    // 첫 이동이거나 직진
                    moveCost = 100;
                } else {
                    // 방향 전환: 직선 100 + 코너 500
                    moveCost = 600;
                }

                int nextCost = current.cost + moveCost;

                // 같은 칸, 같은 방향에 더 싸게 도착할 때만 탐색
                if (nextCost < dist[nx][ny][dir]) {
                    dist[nx][ny][dir] = nextCost;
                    q.add(new Node(nx, ny, nextCost, dir));
                }
            }
        }

        return -1;
    }
}
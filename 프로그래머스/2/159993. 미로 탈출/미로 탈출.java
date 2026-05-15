import java.util.*;

class Point {
    int x, y;
    char str;

    public Point(int x, int y, char str) {
        this.x = x;
        this.y = y;
        this.str = str;
    }
}

class Node {
    int x, y;
    char str;
    int num;

    public Node(int x, int y, char str, int num) {
        this.x = x;
        this.y = y;
        this.str = str;
        this.num = num;
    }
}

class Solution {
    static Point[][] graph;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int n, m;

    public int solution(String[] maps) {
        int answer = 0;

        n = maps.length;
        m = maps[0].length();

        Point start = null;
        Point L = null;
        Point end = null;

        graph = new Point[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                graph[i][j] = new Point(i, j, maps[i].charAt(j));

                if (maps[i].charAt(j) == 'S') {
                    start = new Point(i, j, maps[i].charAt(j));
                }

                if (maps[i].charAt(j) == 'L') {
                    L = new Point(i, j, maps[i].charAt(j));
                }

                if (maps[i].charAt(j) == 'E') {
                    end = new Point(i, j, maps[i].charAt(j));
                }
            }
        }

        int result1 = bfs(start, L);
        int result2 = bfs(L, end);

        if (result1 == -1 || result2 == -1) {
            answer = -1;
        } else {
            answer = result1 + result2;
        }

        return answer;
    }

    public static int bfs(Point start, Point end) {
        Queue<Node> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];

        visited[start.x][start.y] = true;
        q.add(new Node(start.x, start.y, start.str, 0));

        while (!q.isEmpty()) {
            Node temp = q.poll();

            if (temp.x == end.x && temp.y == end.y) {
                return temp.num;
            }

            for (int i = 0; i < 4; i++) {
                int x = temp.x + dx[i];
                int y = temp.y + dy[i];

                if (x < 0 || x >= n || y < 0 || y >= m) {
                    continue;
                }

                if (graph[x][y].str == 'X') {
                    continue;
                }

                if (visited[x][y]) {
                    continue;
                }

                visited[x][y] = true;
                q.add(new Node(x, y, graph[x][y].str, temp.num + 1));
            }
        }

        return -1;
    }
}
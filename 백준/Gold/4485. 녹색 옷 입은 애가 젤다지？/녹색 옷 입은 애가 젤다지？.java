import java.io.*;
import java.util.*;

class Node implements Comparable<Node> {
    int x, y, cost;

    public Node(int x, int y, int cost) {
        this.x = x;
        this.y = y;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.cost, other.cost);
    }
}

public class Main {
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int count = 1;

        while (true) {
            int N = Integer.parseInt(br.readLine());
            if (N == 0) break; // N이 0이면 종료

            int[][] ar = new int[N][N];    // 도둑 루피 정보
            int[][] dist = new int[N][N];  // 원점에서 각 지점까지 걸리는 최소 비용

            // dist 배열 초기화
            for (int i = 0; i < N; i++) {
                Arrays.fill(dist[i], Integer.MAX_VALUE);
            }

            // 각 행의 값을 입력받기
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    ar[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 시작점 (0,0)에서 시작 (비용은 ar[0][0])
            bfs(new Node(0, 0, ar[0][0]), ar, dist, N);

            bw.write("Problem " + count + ": " + dist[N - 1][N - 1] + "\n");
            count++;
        }

        bw.flush();
        bw.close();
    }

    public static void bfs(Node start, int[][] ar, int[][] dist, int size) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(start);
        dist[start.x][start.y] = start.cost;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (cur.cost > dist[cur.x][cur.y]) continue; // 이미 더 좋은 경로가 있다면 무시

            // 상하좌우 네 방향 탐색
            for (int k = 0; k < 4; k++) {
                int nx = cur.x + dx[k];
                int ny = cur.y + dy[k];

                // 배열 범위 체크
                if (nx >= 0 && nx < size && ny >= 0 && ny < size) {
                    int newCost = dist[cur.x][cur.y] + ar[nx][ny];
                    if (dist[nx][ny] > newCost) {
                        dist[nx][ny] = newCost;
                        pq.offer(new Node(nx, ny, newCost));
                    }
                }
            }
        }
    }
}

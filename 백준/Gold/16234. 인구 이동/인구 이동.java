import java.io.*;
import java.util.*;

public class Main {

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    static int N;
    static int L, R;
    static int[][] ar;

    public static void main(String[] args) throws IOException {
        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        ar = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                ar[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;

        while (true) {
            // 날짜가 바뀌면 연합을 새로 탐색
            boolean[][] visited = new boolean[N][N];
            boolean moved = false;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        if (bfs(new int[]{i, j}, visited)) {
                            moved = true;
                        }
                    }
                }
            }

            // 하루 동안 어느 나라의 인구도 바뀌지 않았다면 종료
            if (!moved) break;

            answer++;
        }

        System.out.println(answer);
    }

    // 연합을 찾고 인구를 변경한 뒤, 변경 여부를 반환
    static boolean bfs(int[] start, boolean[][] visited) {
        Queue<int[]> q = new ArrayDeque<>();

        // 이번 연합에 속한 나라들의 좌표
        List<int[]> union = new ArrayList<>();

        visited[start[0]][start[1]] = true;
        q.offer(start);
        union.add(start);

        int sum = ar[start[0]][start[1]];

        while (!q.isEmpty()) {
            int[] temp = q.poll();

            for (int d = 0; d < 4; d++) {
                int nx = temp[0] + dx[d];
                int ny = temp[1] + dy[d];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                    continue;
                }

                if (visited[nx][ny]) continue;

                int chai = Math.abs(
                        ar[nx][ny] - ar[temp[0]][temp[1]]
                );

                if (chai >= L && chai <= R) {
                    visited[nx][ny] = true;
                    sum += ar[nx][ny];

                    int[] next = {nx, ny};

                    q.offer(next);
                    union.add(next);
                }
            }
        }

        int average = sum / union.size();
        boolean changed = false;

        // 지도 전체 대신, 이번 연합의 나라들만 변경
        for (int[] country : union) {
            int x = country[0];
            int y = country[1];

            if (ar[x][y] != average) {
                changed = true;
            }

            ar[x][y] = average;
        }

        return changed;
    }
}
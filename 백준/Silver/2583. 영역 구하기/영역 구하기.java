import java.io.*;
import java.sql.SQLOutput;
import java.util.*;





public class Main {

    static boolean[][] visited;
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    static int N,M;
    static int[][] ar;
    public static void main(String[] args) throws IOException {
        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        ar = new int[N][M];
        visited = new boolean[N][M];
        List<Integer> list = new ArrayList<>();
        for(int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());

            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());

            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            for(int j=x1; j<x2; j++) {
                for(int k=y1; k<y2; k++) {
                    ar[j][k] = 1;
                }
            }
        }
        int answer = 0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(ar[i][j] != 1 && !visited[i][j]) {
                    int result = bfs(new int[]{i,j});
                    list.add(result);
                }
            }
        }
        Collections.sort(list);
        StringBuilder sb = new StringBuilder();
        sb.append(list.size()).append("\n");
        for(int i=0; i<list.size(); i++) {
            sb.append(list.get(i)).append(" ");
        }

        System.out.println(sb);
    }
    public static int bfs(int[] start) {
        int result = 1;
        Queue<int[]> q = new ArrayDeque<>();
        q.add(start);
        visited[start[0]][start[1]] = true;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            for(int i=0; i<4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (nx<0 || nx>=N  || ny<0 || ny>=M) continue;
                if(ar[nx][ny]==1 || visited[nx][ny]) continue;
                visited[nx][ny] = true;
                ar[nx][ny] = 1;
                q.add(new int[]{nx,ny});
                result++;
            }
        }
        return result;

    }
}
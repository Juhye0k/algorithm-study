import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int ans = 0;
    static boolean[] visited;
    static int[][] ar;
    public static void main(String[] args) throws IOException{
        // Please write your code here.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        visited = new boolean[N+1];

        ar = new int[N+1][N+1];
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            
            ar[start][end] = 1;
            ar[end][start] = 1;
        }
        dfs(1);
        System.out.println(ans);

    }
    public static void dfs(int start) {
        visited[start] = true;
        for(int i=1; i<=N; i++) {
            if(!visited[i] && ar[start][i] == 1) {
                visited[i] = true;
                ans++;
                dfs(i);
            }
        }
    }
}
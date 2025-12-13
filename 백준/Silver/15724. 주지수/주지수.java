

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


class Node {
    int x;
    int y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {

    static int ar[][];

    static int N,M;
    static int dp[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 영토의 크기 N,M
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dp =  new int[N+1][M+1];
        ar = new int[N+1][M+1];for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=M; j++) {
                int num =  Integer.parseInt(st.nextToken());
                ar[i][j] = num;
            }
        }
        for(int i=1; i<=N; i++){
            for(int j=1; j<=M; j++){
                dp[i][j] = -dp[i-1][j-1] + dp[i-1][j] +dp[i][j-1]+ar[i][j];
            }
        }

        int K = Integer.parseInt(br.readLine());
        for(int i=0; i<K; i++){
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            sb.append(dp[x2][y2]-dp[x1-1][y2]-dp[x2][y1-1]+dp[x1-1][y1-1]).append("\n");
        }
        System.out.println(sb);
    }
}

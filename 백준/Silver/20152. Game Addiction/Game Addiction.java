


import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {
    int x,y;
    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}



public class Main {


    static int H;
    static int result = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());

        // 집의 좌표 H
        // PC방의 좌표 N
        H = Integer.parseInt(st.nextToken())+1;
        int N = Integer.parseInt(st.nextToken())+1;

        int start = Math.min(H, N);
        int end = Math.max(H, N);

        long [][] dp = new long[35][35];
        for(int i=0; i<=N; i++) {
            Arrays.fill(dp[i], 0);
        }
        dp[start][start] = 1;

        for(int i=start; i<=end; i++) {
            for(int j=start; j<=end; j++) {

                if(i<j) continue;

                if(i==start && j==start) continue;
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        System.out.println(dp[end][end]);
    }

}
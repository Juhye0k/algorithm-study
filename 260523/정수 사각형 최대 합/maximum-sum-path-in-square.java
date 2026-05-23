import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException{
        // Please write your code here.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] ar = new int[N+1][N+1];
        for(int i=1; i<=N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++) {
                ar[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[N+1][N+1];

        dp[1][1] = ar[1][1];
        for(int i=2; i<=N; i++) {
            dp[i][1] = dp[i-1][1] + ar[i][1];
        }
        for(int i=2; i<=N; i++) {
            dp[1][i] = dp[1][i-1] + ar[1][i];
        }
        for(int i=2; i<=N ;i++) {
            for(int j=2; j<=N; j++) {
                dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j])+ar[i][j];
            }
        }
        System.out.println(dp[N][N]);

    }
}
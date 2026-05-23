import java.io.*;
import java.util.*;
public class Main {
    static int[][] ar;
    static int[][] dp;
    static int N;
    public static void main(String[] args) throws IOException{
        // Please write your code here.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        ar = new int[N+1][N+1];
        dp = new int[N+1][N+1];

        for(int i=1; i<=N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++)
                ar[i][j] = Integer.parseInt(st.nextToken());
        }
        initialize();

        for(int i=2; i<=N; i++) {
            for(int j=N-1; j>=1; j--) {
                dp[i][j] = Math.min(dp[i][j+1], dp[i-1][j]) + ar[i][j]; 
            }
        }
        System.out.println(dp[N][1]);


    }
    static void initialize() {
        dp[1][N] = ar[1][N];

        for(int i=N-1; i>=1; i--) 
            dp[1][i] = dp[1][i+1] + ar[1][i];
        for(int i=2; i<=N; i++)
            dp[i][N] = dp[i-1][N] + ar[i][N];
    }
}
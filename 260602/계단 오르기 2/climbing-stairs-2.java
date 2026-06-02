import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException{
        // Please write your code here.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] ar = new int[n+1];
        for(int i=1; i<=n; i++)
            ar[i] = Integer.parseInt(st.nextToken());
        
        int[][] dp = new int[n+1][4];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], -1);
        }
        dp[0][0] = 0;
       

        for(int i=1; i<=n; i++) {
            for(int j=0; j<=3; j++) {
                  if (j >= 1 && dp[i - 1][j - 1] != -1) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + ar[i]);
                }

                // 2. i-2층에서 2계단 올라오는 경우
                if (i >= 2 && dp[i - 2][j] != -1) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 2][j] + ar[i]);
                }
            }
        }
        int ans = 0;
        for(int j=1; j<=3; j++)
            ans = Math.max(ans,dp[n][j]);
        
        System.out.println(ans);
    }
}
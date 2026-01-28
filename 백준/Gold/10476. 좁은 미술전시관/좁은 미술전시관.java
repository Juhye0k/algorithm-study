

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            // 미술관의 세로 길이 N
            int N = Integer.parseInt(st.nextToken());
            // 닫아야 하는 방의 개수 K
            int K = Integer.parseInt(st.nextToken());
            int [][] ar = new int[N+1][2];
            int [][][] dp = new int[N+1][K+1][3];

            if(N==0 && K==0) break;
            for(int i=1; i<=N; i++) {
                st = new StringTokenizer(br.readLine());
                ar[i][0] = Integer.parseInt(st.nextToken());
                ar[i][1] = Integer.parseInt(st.nextToken());
            }
            for(int i=0; i<=N; i++) {
                for(int j=0; j<=K; j++) {
                    dp[i][j][0] = dp[i][j][1] = dp[i][j][2] = -1;
                }
            }
            dp[1][0][0] = ar[1][0] + ar[1][1];
            if(K>0) {
                dp[1][1][1] = ar[1][1];
                dp[1][1][2] = ar[1][0];
            }
            int result = 0;
            for(int i=2; i<=N; i++) {
                for(int j=0; j<=K; j++) {
                    // 둘다 여는 경우 -> 이전까지 j개를 닫음 + 이전 상태 0,1,2 모두 가능
                    int maxPrev = -1;
                    if(dp[i-1][j][0] != -1) maxPrev = Math.max(maxPrev, dp[i-1][j][0]);
                    if(dp[i-1][j][1] != -1) maxPrev = Math.max(maxPrev, dp[i-1][j][1]);
                    if(dp[i-1][j][2] != -1) maxPrev = Math.max(maxPrev, dp[i-1][j][2]);
                    if(maxPrev != -1) {
                        dp[i][j][0] = maxPrev + ar[i][0] + ar[i][1];

                    }
                    // 하나 닫는 경우
                    if(j>=1) {
                        // 왼쪽 닫음
                        int prev1 = -1;
                        if(dp[i-1][j-1][0] != -1) prev1 = Math.max(prev1,dp[i-1][j-1][0]);
                        if(dp[i-1][j-1][1] != -1) prev1 = Math.max(prev1, dp[i-1][j-1][1]);
                        if(prev1 != -1)
                            dp[i][j][1] = prev1 + ar[i][1];
                        // 오른쪽 닫음
                        int prev2 = -1;
                        if(dp[i-1][j-1][0] != -1) prev2 = Math.max(prev2, dp[i-1][j-1][0]);
                        if(dp[i-1][j-1][2] != -1) prev2 = Math.max(prev2, dp[i-1][j-1][2]);
                        if(prev2 != -1)
                            dp[i][j][2] = prev2 + ar[i][0];
                    }
                }
            }
            result = Math.max(dp[N][K][0], Math.max(dp[N][K][1], dp[N][K][2]));
            System.out.println(result);
        }

    }
}
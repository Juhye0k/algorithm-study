
import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;



public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int mod = 1000000000+7;
        int N = Integer.parseInt(br.readLine());
        long[][] dp = new long[N+1][3];
        // 상태 : 총 3개
        /* 한 줄에 모두 못듣는 학생은 없음
        1. 왼쪽의 학생만 듣는다
        2. 오른쪽 학생만 듣는다
        3. 둘다 듣는다
         */
        dp[1][0] = 1;
        dp[1][1] = 1;
        dp[1][2] = 1;
        for(int i = 2; i<=N; i++) {
            dp[i][0] = (dp[i-1][0] + dp[i-1][1] + dp[i-1][2])%mod;
            dp[i][1] = (dp[i-1][0] + dp[i-1][1] + dp[i-1][2])%mod;
            dp[i][2] = (dp[i-1][0] + dp[i-1][1])%mod;
        }
        long ans = (dp[N][0] + dp[N][1] + dp[N][2])%mod;
        System.out.println(ans);
    }
}
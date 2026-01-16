import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;




public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int mod = 1000000000;
        int N = Integer.parseInt(br.readLine());
        long[][] dp = new long[N+1][10];

        for(int i=1; i<=9; i++) {
            dp[1][i] = 1;
        }
        if(N==1) {
            System.out.println(9);
            return;
        }

        for(int i=2; i<=N; i++) {
            for(int j=0; j<=9; j++) {
                if(j==0) {
                    dp[i][j] = dp[i-1][1]%mod;
                }
                else if (j==9) {
                    dp[i][j] = dp[i-1][8]%mod;
                }
                else {
                    dp[i][j] = (dp[i-1][j-1] + dp[i-1][j+1])%mod;
                }
            }
        }
        long result = 0;
        for(int i=0; i<=9; i++) {
            result = (result+dp[N][i])%mod;
        }
        System.out.println(result);
    }
}

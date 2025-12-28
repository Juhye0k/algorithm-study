
import org.w3c.dom.Node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;




public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<T;i++){
            int n = Integer.parseInt(br.readLine());
            int [][] ar = new int[2][n];
            // dp 배열을 어떻게 저장하지? 칸은 몇개 필요할까
            int [][] dp = new int[2][n];
            for(int j=0; j<2; j++ ){
                st = new StringTokenizer(br.readLine());
                for(int k=0; k<n; k++){
                    int a = Integer.parseInt(st.nextToken());
                    ar[j][k] = a;
                }
            }
            dp[0][0] = ar[0][0];
            dp[1][0] = ar[1][0];
            if(n==1) {
                sb.append(Math.max(dp[0][0],dp[1][0])).append("\n");
                continue;
            }
            if(n>=2){
                dp[0][1] = ar[0][1]+dp[1][0];
                dp[1][1] = ar[1][1]+dp[0][0];
                for(int j=2; j<n; j++){
                    dp[0][j] = ar[0][j] + Math.max(dp[1][j-1],dp[1][j-2]);
                    dp[1][j] = ar[1][j] + Math.max(dp[0][j-1],dp[0][j-2]);
                }
                sb.append(Math.max(dp[0][n-1],dp[1][n-1])).append("\n");
            }

        }
        System.out.println(sb);
    }
}
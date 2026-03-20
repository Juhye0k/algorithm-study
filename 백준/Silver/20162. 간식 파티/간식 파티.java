import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;




public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        int N = Integer.parseInt(br.readLine());
        int [] ar = new int[N+1];
        int [] dp = new int[N+1];
        int answer = 0;

        for(int i=1; i<=N; i++) {
            ar[i] = Integer.parseInt(br.readLine());
        }
        for(int i=1; i<=N; i++) {
            dp[i] = ar[i];
            for(int j=1; j<=i; j++) {
                if(ar[j]<ar[i]) {
                    dp[i] = Math.max(dp[i], dp[j]+ar[i]);
                }
            }
            answer = Math.max(answer, dp[i]);
        }
        System.out.println(answer);
    }
}
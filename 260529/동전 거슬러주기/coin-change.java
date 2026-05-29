import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException{
        // Please write your code here.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] coin = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0 ;i<N; i++) {
            int num = Integer.parseInt(st.nextToken());
            coin[i] = num;
        }
        int[] dp = new int[M+1];
        for(int i=0; i<=M; i++)
            dp[i] = Integer.MAX_VALUE;
        dp[0] = 0;

        for(int i=1; i<=M; i++) {
            for(int j=0; j<N; j++) {
                if(i>=coin[j]) {
                    if(dp[i-coin[j]] == Integer.MAX_VALUE)
                        continue;
                    dp[i] = Math.min(dp[i], dp[i-coin[j]]+1);
                }
            }
        }
        int ans = dp[M];
        if(ans == Integer.MAX_VALUE)
            ans = -1;
        System.out.println(ans);
    }
}
import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException{
        // Please write your code here.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] coin = new int[N];
        int[] dp = new int[M+1];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            coin[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0; i<=M; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        dp[0] = 0;
        for(int i=0; i<N; i++) {
            for(int j=M; j>=coin[i]; j--) {
                if(dp[j-coin[i]]==Integer.MAX_VALUE) continue;

                dp[j] = Math.min(dp[j], dp[j-coin[i]]+1);
            }
        }

        if(dp[M] == Integer.MAX_VALUE)
            System.out.println(-1);
        else
            System.out.println(dp[M]);


    }
}
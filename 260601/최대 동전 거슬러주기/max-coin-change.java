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
        int[] dp = new int[M+1];
        for(int i=0; i<=M; i++) 
            dp[i] = -1;
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            coin[i] = Integer.parseInt(st.nextToken());
        }
        dp[0] = 0;
        for(int i=1; i<=M; i++) {
            for(int j=0; j<N; j++) {
                if(i>=coin[j]) {
                    if(dp[i-coin[j]]!=-1)
                        dp[i] = Math.max(dp[i], dp[i-coin[j]]+1);
                }
            }
        }
        if(dp[M]==-1) {
            System.out.println(-1);
            return;
        }
        System.out.println(dp[M]);

    }
}
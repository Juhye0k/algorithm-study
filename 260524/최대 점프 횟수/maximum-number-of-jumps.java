import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException{
        // Please write your code here.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] ar = new int[N];
        int[] dp = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) 
            ar[i] = Integer.parseInt(st.nextToken());
        Arrays.fill(dp,Integer.MIN_VALUE);
        dp[0] = 0;
        for(int i=1; i<N; i++) {
            for(int j=0; j<i; j++) {
                if(dp[j]==Integer.MIN_VALUE) continue;
                if(ar[j]+j>=i)
                    dp[i] = Math.max(dp[i], dp[j]+1);
            }
        }
        int ans = 0;
        for(int i=0; i<N; i++)
            ans = Math.max(ans,dp[i]);
        System.out.println(ans);
    }
}
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        // Please write your code here.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] ar = new int[N];
        int[] dp = new int[N];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            ar[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.fill(dp,1);
        for(int i=0; i<N; i++) {
            for(int j=0; j<i; j++) {
                if(ar[j]>ar[i])
                    dp[i] = Math.max(dp[i], dp[j]+1);
            }
        }
        int ans = 0;
        for(int i=0; i<N; i++)
            ans = Math.max(ans,dp[i]);
        System.out.println(ans);
    }
}
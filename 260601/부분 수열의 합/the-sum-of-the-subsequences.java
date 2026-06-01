import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException{
        // Please write your code here.

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] ar = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            ar[i] = Integer.parseInt(st.nextToken());
        }
        int[] dp = new int[M+1];
        Arrays.fill(dp,-1);
        dp[0] = 0;
        
        for(int i=0; i<N; i++) {
            for(int j=M; j>=0; j--) {
                if(j>=ar[i]) {
                    if(dp[j-ar[i]]!=-1)
                        dp[j] = Math.max(dp[j],dp[j-ar[i]]+1);
                }
            }
        }
        if(dp[M]!=-1) System.out.println("Yes");
        else System.out.println("No");
    }
}
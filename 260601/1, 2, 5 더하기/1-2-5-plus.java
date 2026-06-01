import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException{
        // Please write your code here.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] dp = new int[N+1];
        int[] ar = new int[]{1,2,5};

       
        dp[0] = 1;

        for(int i=1; i<=N; i++) {
            for(int j=0; j<3; j++) {
                if(i>=ar[j]) {

                    dp[i] = (dp[i]+ dp[i-ar[j]])%10007;
                }
            }
        }
        System.out.println(dp[N]%10007);
    }
}

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] bright = new int[N];
        int[] ar = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            bright[i] = Integer.parseInt(st.nextToken());
        }
        int first = 0;
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            int status = Integer.parseInt(st.nextToken());
            if(status ==1) {
                ar[i] = -bright[i];
                first+=bright[i];
            }
            else {
                ar[i] = bright[i];
            }
        }
        int[] dp = new int[N];
        dp[0] = ar[0];
        for(int i=1; i<N; i++) {
            dp[i] = Math.max(dp[i-1]+ar[i],ar[i]);
        }
        int max = dp[0];
        for(int i=0; i<N; i++) {
            max = Math.max(max, dp[i]);
        }
        System.out.println(first+max);
    }
}

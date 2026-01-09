import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;



public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 어린이 수를 나타내는 정수
        int N = Integer.parseInt(br.readLine());

        int ar[] = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            ar[i] = Integer.parseInt(st.nextToken());
        }
        int[] dp = new int[N+1];
        int result = 0;
        for(int i=1; i<=N; i++) {
            int num = ar[i];
            dp[num] = dp[num-1]+1;
            result = Math.max(result, dp[num]);
        }
        System.out.println(N-result);
    }
}

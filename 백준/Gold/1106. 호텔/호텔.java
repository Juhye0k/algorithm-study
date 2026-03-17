import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 늘리고 싶은 사람
        int C = Integer.parseInt(st.nextToken());
        // 도시 개수
        int N = Integer.parseInt(st.nextToken());
        int [] dp = new int[C+1];
        int [] cos = new int[N+1];
        int [] count = new int[N+1];
        for(int i=1;  i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            // 비용
            int cost = Integer.parseInt(st.nextToken());
            // 고객
            int customer = Integer.parseInt(st.nextToken());
            cos[i] = cost;
            count[i] = customer;
        }
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for(int i=1; i<=N; i++) {
            for(int j=1; j<=C; j++) {
                dp[j] = Math.min(dp[j], dp[Math.max(0, j-count[i])]+cos[i]);
            }
        }
        System.out.println(dp[C]);

    }
}

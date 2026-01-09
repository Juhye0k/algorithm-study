import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;



public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        // 주문의 수 N
        int N = Integer.parseInt(st.nextToken());
        // 주방에 남은 치즈버거 개수 M
        int M = Integer.parseInt(st.nextToken());
        // 주방에 남은 감자튀김 개수 K
        int K = Integer.parseInt(st.nextToken());
        int [][] dp = new int[M+1][K+1];


        // 주문 내용
        for(int n=0; n<N; n++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            for(int i=M; i>=x; i--) {
                for(int j=K; j>=y; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i-x][j-y]+1);
                }
            }
        }
        // 치즈버거와 감자튀김은 세트인데, 어떻게 순서를 매겨야하지? 작은거부터 넣어야 가장 많이 처리할 수 있을거 같은데
        System.out.println( dp[M][K]);
    }
}

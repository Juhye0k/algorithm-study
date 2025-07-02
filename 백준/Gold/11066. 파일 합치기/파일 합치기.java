import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;



public class Main {

    static int dp[][];
    static int ar[];

    public static int solve(int left, int right){
        // 기저 조건
        if(left >= right)
            return 0;
        // 캐시
        if(dp[left][right]!=-1)
            return dp[left][right];
        dp[left][right] = Integer.MAX_VALUE;
        // mid를 돌려가면서 부분 구하기
        for(int mid=left; mid<right; mid++){
            dp[left][right] = Math.min(dp[left][right], solve(left,mid)+solve(mid+1,right)+ar[right]-ar[left-1]);
        }
        return dp[left][right];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        // 테스트 데이터 T 입력
        int T = Integer.parseInt(br.readLine());
        for(int i=0; i<T; i++){
            // 장의 개수 K
            int K = Integer.parseInt(br.readLine());
            // 1장부터 K장까지 수록한 파일의 크기를 나타나는 양의 정수 K개
            st = new StringTokenizer(br.readLine());
            ar = new int[K+1];
            ar[0] = 0;
            for(int j=1; j<=K; j++){
                ar[j] = ar[j-1]+Integer.parseInt(st.nextToken());
            }
            dp = new int[K+1][K+1];
            // dp 배열 -1로 초기화
            for(int j=1; j<=K; j++){
                for(int k=1; k<=K; k++){
                    dp[j][k] = -1;
                }
            }
            bw.write(solve(1,K)+"\n");
        }
        bw.flush();
        bw.close();
    }
}
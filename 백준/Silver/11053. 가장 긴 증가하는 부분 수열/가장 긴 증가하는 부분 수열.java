import java.io.*;
import java.util.StringTokenizer;




public class Main {

    static int N;
    static int [] dp;
    static int[] ar;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 수열 A의 크기 N
        int N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        dp = new int[N+1];
        ar = new int[N+1];
        for(int i=1; i<=N; i++)
            ar[i] = Integer.parseInt(st.nextToken());
        for(int i=0;i<N+1;i++)
            dp[i] = -1;

        for(int i=1; i<=N; i++){
            dp[i] = 1;
            for(int j=0; j<i; j++){
                if(ar[i]>ar[j]){
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
        }
        int result = 0;
        for(int i = 1; i<=N; i++){
            result = Math.max(result, dp[i]);
        }
        System.out.println(result);

    }
}
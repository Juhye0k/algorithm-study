import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int ar[][][];



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int dp[] = new int[n+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        dp[1] = Integer.parseInt(st.nextToken());
        int result = dp[1];
        for(int i=2; i<=n; i++){
            int num=Integer.parseInt(st.nextToken());
            dp[i] = Math.max(dp[i-1] + num, num);
            if(dp[i]>result)
                result=dp[i];
        }
        bw.write(result+"\n");
        bw.flush();
        bw.close();
    }
}
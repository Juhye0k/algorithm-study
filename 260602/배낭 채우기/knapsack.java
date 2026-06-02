import java.util.*;
import java.io.*;
class Ruby{
    int weight;
    int value;

    Ruby(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }
    
}

public class Main {
    public static void main(String[] args) throws IOException{
        // Please write your code here.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        List<Ruby> list = new ArrayList<>();
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            list.add(new Ruby(weight,value));
        }
        int[] dp = new int[M+1];
        Arrays.fill(dp,-1);
        dp[0] = 0;
        for(int i=0; i<N; i++) {
            int weight = list.get(i).weight;
            int value = list.get(i).value;
            for(int j=M; j>=1; j--) {
                if(j>=weight) {
                    if(dp[j-weight]!=-1)
                        dp[j] = Math.max(dp[j], dp[j-weight]+value);
                }
            }
        }
        int ans = 0;
        for(int i=1; i<=M; i++) {
            ans = Math.max(ans,dp[i]);
        }
        System.out.println(ans);
    }
}
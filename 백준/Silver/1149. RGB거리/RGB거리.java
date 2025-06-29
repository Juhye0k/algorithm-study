import java.io.*;
import java.util.StringTokenizer;



public class Main {
    static int N;
    static int dp[][];
    static int list[][];
    public static int solve(int house, int color){
        // 범위 넘어가면 return
        if(house > N)
            return 0;
        if(dp[house][color] != -1)
            return dp[house][color];
        int minCost = Integer.MAX_VALUE;
        for(int i=0; i<3; i++){
            if(i!=color)
                minCost = Math.min(minCost, solve(house+1, i));
        }
        dp[house][color] = list[house][color] + minCost;
        return dp[house][color];

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        // 첫째 줄에 집의 수 N이 주어짐
        N = Integer.parseInt(br.readLine());
        dp = new int[N+1][4];
        for(int i =1; i<=N; i++){
            for(int j = 0; j<3; j++){
                dp[i][j] = -1;
            }
        }
        list = new int[N+1][3];
        for(int i =1; i<=N; i++){
           st = new StringTokenizer(br.readLine());
           for(int j = 0; j<3; j++){
               list[i][j] = Integer.parseInt(st.nextToken());
           }
        }
        int result = Math.min(solve(1,0),Math.min(solve(1,1),solve(1,2)));
        System.out.println(result);
    }
}
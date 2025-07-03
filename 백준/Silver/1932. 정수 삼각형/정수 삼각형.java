import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class Main {

    static List<List<Integer>> triangle = new ArrayList<>();
    static int[][] dp;
    static int n;
    public static int solve(int row, int col){
       if(row == n)
            return triangle.get(row-1).get(col-1);
       if(dp[row][col] != -1)
           return dp[row][col];

       int left = solve(row+1, col);
       int right = solve(row+1, col+1);
       dp[row][col] = triangle.get(row-1).get(col-1) + Math.max(left, right);
       return dp[row][col];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 삼각형의 크기 n
        n = Integer.parseInt(br.readLine());
        // dp 배열 선언하기
        dp = new int[n+1][n+1];
        StringTokenizer st;
        // 삼각형 입력받기
        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            triangle.add(new ArrayList<>());
            for(int j = 1; j<=i; j++){
                triangle.get(i-1).add(Integer.parseInt(st.nextToken()));
            }
        }
        // dp 배열 -1로 초기화
        for(int i = 1; i<=n; i++){
            for(int j = 1; j<=i; j++)
                dp[i][j] = -1;
        }
        System.out.println(solve(1, 1));
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.*;


public class Main{

    static int ar[][];
    static int M, N;
    static int result;
    static int dp[][];
    public static boolean check(int i, int j){
        if(i<0 || i>=M || j<0 || j>=N)  // 만약 인덱스가 범위를 벗어난다면 이동하지 못하는 곳
            return false;
        else
            return true;
    }
    public static int solve(int i, int j){
        // 만약 해당 점이 끝 점이다 -> 도달 가능한 코스
        if(i==M-1 && j==N-1)
            return 1;
        // 이미 초기화된 곳
        if(dp[i][j]!=-1)
            return dp[i][j];
        int result = 0;
        if(check(i+1,j) && ar[i][j]>ar[i+1][j]) // 인덱스를 벗어나지 않고, 이동하고자 하는 위치가 현재 값보다 작을 때
            result += solve(i+1,j);
        if(check(i-1,j) && ar[i][j]>ar[i-1][j])
            result += solve(i-1,j);
        if(check(i,j+1) && ar[i][j]>ar[i][j+1])
            result += solve(i,j+1);
        if(check(i,j-1) && ar[i][j]>ar[i][j-1])
            result += solve(i,j-1);
        dp[i][j] = result;
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        M =Integer.parseInt(st.nextToken()); // 세로의 크기 M
        N =Integer.parseInt(st.nextToken()); // 가로의 크기 N

        ar = new int[M][N];
        dp = new int[M][N];
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                ar[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i=0; i<M; i++)
            Arrays.fill(dp[i],-1);
        int result = solve(0,0);
        System.out.println(result);
    }
}

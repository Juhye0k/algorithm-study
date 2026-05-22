import java.io.*;
import java.util.*;
public class Main {
    static int[] memo;
    public static void main(String[] args) throws IOException{
        // Please write your code here.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        memo = new int[N+1];
        Arrays.fill(memo,-1);
        int answer = solve(N);
        System.out.println(answer%10007);
    }
    public static int solve(int n) {
        if(n<=1) return 0;
        // n층 -> n-2  + n-3 + 2임.
        if(n==2) memo[n] = 1;
        else if(n==3) memo[n] =1;
        else if(memo[n]!=-1) return memo[n];
        else
            memo[n] = (solve(n-2) + solve(n-3))%10007;
        return memo[n];
    }
}
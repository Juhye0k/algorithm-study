
import java.io.*;

public class Main {
    static int count1=0,count2=0;
    static String A,B;
    static int dp[][];
    public static int dfs(int i, int j)
    {
        if(i == A.length() || j == B.length())
        {
            return 0;
        }
        if (dp[i][j] != -1)
        {
            return dp[i][j];
        }
        int res = 0;
        res = Math.max(res,dfs(i+1,j)); // 행동 1
        res = Math.max(res,dfs(i,j+1)); // 행동 2
        if (A.charAt(i) == B.charAt(j)) // 특수 행동
        {
           res = Math.max(res, dfs(i+1,j+1) +1); 
        }
        return dp[i][j] = res;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // n이 주어짐
        A = br.readLine();
        B = br.readLine();
        int alen = A.length(), blen = B.length();
        dp = new int[alen+1][blen+1];
        for(int i =0; i<=alen;++i)
        {
            for(int j =0; j<=blen;++j)
            {
                dp[i][j] = -1;
            }
        }
        System.out.println(dfs(0,0));
    }

}
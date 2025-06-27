
import java.io.*;

public class Main {
    static int count1=0,count2=0;
    static String A,B,C;
    static int dp[][][];
    public static int dfs(int i, int j, int k)
    {
        if(i == A.length() || j == B.length() ||  k == C.length())
        {
            return 0;
        }
        if (dp[i][j][k] != -1)
        {
            return dp[i][j][k];
        }
        int res = 0;
        res = Math.max(res,dfs(i+1,j,k)); // 행동 1
        res = Math.max(res,dfs(i,j+1,k)); // 행동 2
        res = Math.max(res,dfs(i,j,k+1));
        if (A.charAt(i) == B.charAt(j) && C.charAt(k)== B.charAt(j)) // 특수 행동
        {
           res = Math.max(res, dfs(i+1,j+1,k+1) +1);
        }
        return dp[i][j][k] = res;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // n이 주어짐
        A = br.readLine();
        B = br.readLine();
        C = br.readLine();
        int alen = A.length(), blen = B.length() , clen = C.length();
        dp = new int[alen+1][blen+1][clen + 1];
        for(int i =0; i<=alen;++i)
        {
            for(int j =0; j<=blen;++j)
            {
                for(int k =0; k<=clen;++k)
                {
                    dp[i][j][k] = -1;
                }
            }
        }
        System.out.println(dfs(0,0,0));
    }

}
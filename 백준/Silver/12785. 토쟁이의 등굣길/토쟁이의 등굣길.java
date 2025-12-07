
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;


class Node {
    int x;
    int y;
    int distance;
    public Node(int x, int y, int distance) {
        this.x = x;
        this.y = y;
        this.distance = distance;
    }
}

public class Main {

    static int w,h;
    static final int mod =1000007;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new  StringTokenizer(br.readLine());

        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        st =  new  StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        long distance1 = solveDP(x, y);
        long distance2 = solveDP(w-x+1, h-y+1);
        System.out.println(distance1*distance2%mod);

    }

    public static long solveDP(int x, int y) {
        long[][] dp = new long[w+1][h+1];

        for(int i=1; i<=x; i++) {
            for(int j=1; j<=y; j++) {
                if(i==1 && j==1)
                    dp[i][j] = 1;
                else
                    dp[i][j] = (dp[i-1][j] + dp[i][j-1]) % mod;            }
        }
        return dp[x][y];
    }
}
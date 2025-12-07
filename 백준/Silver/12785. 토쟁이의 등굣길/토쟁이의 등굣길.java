
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
    static int[] dx= {1,0,-1,0};
    static int[] dy = {0,1,0,-1};
    static int goal;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        st= new StringTokenizer(br.readLine());

        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        BigInteger path1 = solveDP(x, y);
        BigInteger path2 = solveDP(w-x+1, h-y+1);
        System.out.println(path1.multiply(path2).remainder(new BigInteger("1000007")));
    }



    public static BigInteger solveDP(int width, int height) {
        BigInteger[][] dp = new BigInteger[height+1][width+1];
        for (int i = 0; i <= height; i++) dp[i][0] = BigInteger.ZERO;
        for (int j = 0; j <= width; j++) dp[0][j] = BigInteger.ZERO;
        for(int i=1; i<=height; i++) {
            for(int j=1; j<=width; j++) {
                if(i==1 && j==1)
                    dp[i][j] = BigInteger.ONE;
                else
                    dp[i][j] = dp[i-1][j].add(dp[i][j-1]);
            }
        }
        return dp[height][width];
    }
}

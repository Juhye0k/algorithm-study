import java.io.*;
import java.util.Arrays;


public class Main {

    static final int UNREACHABLE = 10;

    public static void main(String[] args) throws IOException {
        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[][] price = new int[n][n];
        for(int seller = 0; seller < n; seller++) {
            String line = br.readLine();
            for(int buyer = 0; buyer < n; buyer++) {
                price[seller][buyer] = line.charAt(buyer) - '0';
            }
        }

        int stateCount = 1 << n;
        int[][] dp = new int[stateCount][n];

        for (int[] row : dp) {
            Arrays.fill(row, UNREACHABLE);
        }
        dp[1][0] = 0;

        int answer = -1;

        for (int mask = 1; mask < stateCount; mask++) {
            for (int owner = 0; owner < n; owner++) {
                int paid = dp[mask][owner];
                if (paid == UNREACHABLE) continue;

                answer = Math.max(answer, Integer.bitCount(mask));

                for (int next = 0; next < n; next++) {
                    int bit = 1 << next;
                    if ((mask & bit) !=0) continue;

                    int nextPrice = price[owner][next];
                    if(nextPrice < paid) continue;

                    int nextMask = mask | bit;
                    dp[nextMask][next] = Math.min(dp[nextMask][next], nextPrice);
                }
            }
        }
        System.out.println(answer);
    }
}
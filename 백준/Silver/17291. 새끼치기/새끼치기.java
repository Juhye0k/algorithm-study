import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        
        int[] dp = new int[21];
        int[] born = new int[21];

        dp[1] = 1;
        born[1] = 1;

        for (int i = 2; i <= N; i++) {
            int dead = 0;
            
            if (i - 3 >= 1 && (i - 3) % 2 != 0) {
                dead += born[i - 3];
            }
            if (i - 4 >= 1 && (i - 4) % 2 == 0) {
                dead += born[i - 4];
            }

            born[i] = dp[i - 1];

          
            dp[i] = dp[i - 1] + born[i] - dead;
        }

        System.out.println(dp[N]);
    }
}
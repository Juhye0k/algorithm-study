import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        
        if (n == 0) {
            System.out.println(0);
            System.out.println(0);
            return;
        }

    
        if (n > 0) {
            System.out.println(1);
        } else {
            if (Math.abs(n) % 2 == 0) {
                System.out.println(-1);
            } else {
                System.out.println(1);
            }
        }

        int target = Math.abs(n);
        int mod = 1000000000;
        
        int a = 0; // F(0)
        int b = 1; // F(1)
        
        if (target == 1) {
            System.out.println(1);
        } else {
            int c = 0;
            for (int i = 2; i <= target; i++) {
                c = (a + b) % mod;
                a = b;
                b = c;
            }
            System.out.println(c);
        }
    }
}
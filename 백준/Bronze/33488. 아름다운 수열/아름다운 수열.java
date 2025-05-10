import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine().trim());
            sb.append("YES\n");
            for (int i = 1; i <= N; i++) {
                sb.append(i).append(i == N ? '\n' : ' ');
            }
        }

        System.out.print(sb);
    }
}

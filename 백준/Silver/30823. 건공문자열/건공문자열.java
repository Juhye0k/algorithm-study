import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        String S = br.readLine();

        int reverseCount = N - K + 1;
        StringBuilder answer = new StringBuilder(N);

        // 뒤집을 때마다 왼쪽에 확정되는 글자들을 순서대로 추가
        answer.append(S, K - 1, N);

        // 처음 K-1글자는 뒤집은 횟수에 따라 순서를 결정
        if (reverseCount % 2 == 0) {
            answer.append(S, 0, K - 1);
        } else {
            for (int i = K - 2; i >= 0; i--) {
                answer.append(S.charAt(i));
            }
        }

        System.out.println(answer);
    }
}
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken()); // 낮에 올라가는 거리
        int B = Integer.parseInt(st.nextToken()); // 밤에 미끄러지는 거리
        int V = Integer.parseInt(st.nextToken()); // 목표 높이

        // 낮에 올라가는 거리 A가 목표 높이 V보다 크거나 같다면 하루만에 도달 가능
        if (A >= V) {
            System.out.println(1);
        } else {
            int days = (V - A + (A - B) - 1) / (A - B) + 1;
            System.out.println(days);
        }
    }
}

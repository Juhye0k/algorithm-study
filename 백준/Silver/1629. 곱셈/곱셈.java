import java.io.*;
import java.util.*;

public class Main {

    public static long power(long a, long b, long c) {
        if (b == 0)
            return 1;

        long mid = power(a, b / 2, c);
        mid = (mid * mid) % c; // 중간 연산에서도 %c 적용

        if (b % 2 == 1) { // b가 홀수일 때 추가 곱셈
            mid = (mid * a) % c;
        }

        return mid;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        long C = Long.parseLong(st.nextToken());
        long result = power(A, B, C);
        System.out.println(result);
    }
}

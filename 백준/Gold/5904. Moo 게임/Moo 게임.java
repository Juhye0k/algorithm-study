import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;




public class Main {

    static int k;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        // 우리가 찾고자 하는 인덱스 N -> 길이가 몇인 곳에서 찾을 수 있을까?
        // 처음 길이
        int num = 3;
        // K의 차수
        k = 0;
        int len = 0;
        while(N>=num) {
            k++;
            // 중간에 새로 넣는 길이
            len = k+3;
            // 기존 길이 * 2 + 중간에 새로 넣는 길이
            num = 2 * num + len;
            // 차수 증가
        }
        solve(N,k,num);
    }

    // n : 구하고자 하는 인덱스, k : 차수, len : 좌,우 문자열 길이
    public static void solve(int n, int k, int len) {
        int prev_len = (len-(k+3))/2;
        // 1 구역일 때
        if(n<=prev_len) {
            solve(n,k-1,prev_len);
        }
        // 2 구역일 때
        else if(n>prev_len+k+3) {
            solve(n-(prev_len+k+3), k-1, prev_len);
        }
        // 3 구역일 때
        else {
            if(n==prev_len+1)
                System.out.println("m");
            else
                System.out.println("o");
        }
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;



public class Main {

    static int result;
    static int N;
    static boolean[] isBanned;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        // 자연수 N
        N = Integer.parseInt(st.nextToken());
        // 집합 S의 크기
        int M = Integer.parseInt(st.nextToken());
        isBanned = new boolean[10001];
        // 집합의 크기가 0인 경우, 금지되는 숫자가 없음
        if(M!=0) {
            st = new StringTokenizer(br.readLine());
            // 집합 안에 포함되어 있는 수를 찾기위해 설정
            for(int i=0; i<M; i++) {
                isBanned[Integer.parseInt(st.nextToken())] = true;
            }
        }
        result = Integer.MAX_VALUE;
        // 숫자 3개를 선택하자
        for(int i=1; i<=1001; i++) {
            if(isBanned[i]) continue;
            for(int j=1; j<=1001; j++) {
                if(isBanned[j]) continue;
                for(int k=1; k<=1001; k++) {
                    if(isBanned[k]) continue;
                    int num = i*j*k;
                    result = Math.min(result, Math.abs(N-num));
                    if(num>N) break;

                }
            }
        }
        System.out.println(result);
    }

}

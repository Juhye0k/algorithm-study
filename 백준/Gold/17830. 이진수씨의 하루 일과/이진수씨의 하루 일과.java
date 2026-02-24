
import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 테스트 케이스 T -> 20
        int T = Integer.parseInt(br.readLine());

        for(int i=0; i<T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            // 길이 N -> 10^6
            int N = Integer.parseInt(st.nextToken());

            // B
            String B = st.nextToken();
            int maxResult = getLength(B, N, '1');
            int minResult = getLength(B, N, '0');
            System.out.println(maxResult+" "+minResult);

        }

    }
    public static int getLength(String B, int N, char replaceChar) {
        // 1이 처음으로 나오는 인덱스
        int firstOneIndex = -1;
        // 1의 개수
        int oneCount = 0;

        for(int i=0; i<N; i++) {
            char currentBit = B.charAt(i);

            if(currentBit == '?') {
                currentBit = replaceChar;
            }
            if (currentBit == '1') {
                if(firstOneIndex == -1) {
                    firstOneIndex = i;
                }
                oneCount++;
            }
        }
        // B에 1이 없다 -> 결과가 0이다
        if(oneCount == 0) {
            return 1;
        }
        int L = N-firstOneIndex;
        // 1이 딱 하나있다 ->
        if(oneCount == 1) {
            return N+L-1;
        }
        // 1이 2개 이상일 때 -> 맨 앞자리가 안전하게 살아남아 자릿수 유지
        else {
            return N+L;
        }
    }

}
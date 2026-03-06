
import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        int[] ar = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            ar[i] = Integer.parseInt(st.nextToken());
        }
        long result = Long.MAX_VALUE;
        // 이미 원하는 자리에 있는 경우 시행 금지 ..'
        for(int i=0; i<N; i++) {
            long temp = 0;
            // 인덱스가 첫 번째일 경우
            if(i==0) {
                // 차이를 구해라
                temp = Math.max(0, X - Math.abs(ar[1] - ar[0]));
            }
            // 인덱스가 마지막일 경우
            else if(i==N-1) {
                temp = Math.max(0, X - Math.abs(ar[N-2] - ar[N-1]));
            }
            // 그 외의 경우
            else {
                long c2_peek = Math.max(ar[i], Math.max(ar[i-1]+X,ar[i+1]+X));
                long cost_peak = c2_peek - ar[i];

                long c1_valley = Math.max(ar[i-1],ar[i]+X);
                long c3_valley = Math.max(ar[i+1],ar[i]+X);
                long cost_valley = (c1_valley-ar[i-1]) + (c3_valley-ar[i+1]);

                long c2_stairUp = Math.max(ar[i], ar[i-1]+X);
                long c3_stairUp = Math.max(ar[i+1], c2_stairUp+X);
                long cost_stairUp = (c2_stairUp-ar[i]) + (c3_stairUp-ar[i+1]);

                long c2_stairDown = Math.max(ar[i], ar[i+1]+X);
                long c1_stairDown = Math.max(ar[i-1], c2_stairDown + X);
                long cost_stairDown = (c2_stairDown - ar[i])+(c1_stairDown-ar[i-1]);
                temp = Math.min(cost_peak, Math.min(cost_valley, Math.min(cost_stairUp, cost_stairDown)));
            }
            result = Math.min(result, temp);
        }
        System.out.println(result);
    }
}

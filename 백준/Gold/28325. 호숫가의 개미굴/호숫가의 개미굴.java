


import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        long [] ar = new long[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            ar[i] = Long.parseLong(st.nextToken());
        }
        // 쪽방부터 배치하는게 나을듯. 쪽방 있는 곳은 쪽방부터 배치하고, 해당 방은 배치하지 않기
        // dp로 푼다 -> 어떤 상태를 저장해야하지?
        long [][] dp1 = new long[N+1][2];
        dp1[1][0] = ar[1];
        dp1[1][1] = -1;
        for(int i=1; i<N; i++) {
            dp1[i+1][1] = dp1[i][0] + 1;
            dp1[i+1][0] = Math.max(dp1[i][0], dp1[i][1]) + ar[i+1];
        }
        long ans1 = Math.max(dp1[N][0], dp1[N][1]);

        long [][] dp2 = new long[N+1][2];
        dp2[1][0] = -1;
        dp2[1][1] = 1;
        for(int i=1; i<N; i++) {
            dp2[i+1][0] = Math.max(dp2[i][1], dp2[i][0])+ar[i+1];
            dp2[i+1][1] = dp2[i][0] + 1;
        }
        long ans2 = dp2[N][0];

        System.out.println(Math.max(ans1,ans2));
    }
}
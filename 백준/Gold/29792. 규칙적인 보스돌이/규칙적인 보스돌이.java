
import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;




public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());         // 캐랙터의 개수 N
        int M = Integer.parseInt(st.nextToken());         // 하루에 사용할 캐랙터의 개수 M
        int K = Integer.parseInt(st.nextToken());         // 보스의 가짓수 K


        // 캐랙터가 1초에 가하는 데미지
        long[] damage = new long[N+1];
        for(int i=1; i<=N; i++) {
            damage[i] = Long.parseLong(br.readLine());
        }

        long[] hp = new long[K+1];
        long[] money = new long[K+1];
        for(int i=1; i<=K; i++) {
            st = new StringTokenizer(br.readLine());
            // 체력 P
            hp[i] = Long.parseLong(st.nextToken());
            // 드랍 메소 Q
            money[i] =  Long.parseLong(st.nextToken());
        }
        /*
        보스는 1회 씩만 처치
        캐랙터 변경 불가능
         */
        long[] best = new long[N+1];
        // 사람 1명씩 배낭 dp 돌려서, 보스 잡는데 걸리는 이익을 계산해보자
        for(int i=1; i<=N; i++) {
            long D = damage[i];
            long[] dp = new long[901];

            for(int j=1; j<=K; j++) {
                long needLong = (hp[j]+D-1)/D;
                if(needLong>900) continue;

                int need = (int)needLong;
                for(int a = 900; a>=need; a--)
                    dp[a] = Math.max(dp[a],dp[a-need]+money[j]);
            }
            best[i] = dp[900];

        }
        Arrays.sort(best);
        long answer = 0;
        for(int i=N; i>=N-M+1; i--) {
            answer+=best[i];
        }
        System.out.println(answer);
    }
}

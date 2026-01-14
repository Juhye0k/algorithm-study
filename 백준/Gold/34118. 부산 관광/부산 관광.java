import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 부산에 머무르는 기간 기간 N
        int N = Integer.parseInt(br.readLine());
        int [] a = new int[N];
        int [] b = new int[N];
        int [] cost = new int[4];

        // dp 배열 선언 후 매우 큰 값으로 초기화
        int [][][] dp = new int[N+1][5][5];
        for(int i=0; i<=N; i++) {
            for(int j=0; j<=4; j++) {
                for(int k=0; k<=4; k++) {
                    dp[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }
        dp[0][0][0] = 0;
        // 한국이의 관광 일정 A
        String A = br.readLine();
        // 정올이의 관광 일정 B
        String B = br.readLine();

        // p1, p3, p5, pair가 공백으로 주어짐
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<4; i++)
            cost[i] = Integer.parseInt(st.nextToken());

        for(int i=0; i<N; i++) {
            for(int j=0; j<5; j++) {
                for(int k=0; k<5; k++) {
                    if(dp[i][j][k] == Integer.MAX_VALUE) continue;
                    boolean needA = (A.charAt(i) == '1');
                    boolean needB = (B.charAt(i) == '1');

                    // 1. 묶음권 구매시도 -> 묶음권은 4일지속. 오늘 사용하면 내일 기준 3일남음
                    // 묶음권을 샀으니 유효기간은 충분하므로 needA, needB 체크 불필요
                    // 여기서 가능한 모든 구매 조합 계산 (티켓 안삼, A만 구매, B만 구매, 둘다 구매)
                    int nextJ = 3;
                    int nextK = 3;
                    dp[i+1][nextJ][nextK] = Math.min(dp[i+1][nextJ][nextK], dp[i][j][k] + cost[3]);

                    // 개별 티켓 구매 시도
                    int[] days = {0,1,3,5};
                    int[] prices = {0, cost[0], cost[1], cost[2]};
                    for(int aOpt = 0; aOpt<4; aOpt++) {
                        for(int bOpt = 0; bOpt<4; bOpt++) {

                            //한국이 상태 계산
                            int powerA = Math.max(j, days[aOpt]);
                            if(needA && powerA ==0) continue;

                            int powerB = Math.max(k, days[bOpt]);
                            if(needB && powerB ==0) continue;

                            // 다음날 상태 계산
                            int nextJ1 = Math.max(0, powerA-1);
                            int nextJ2 = Math.max(0, powerB-1);

                            int newCost = dp[i][j][k] + prices[aOpt] + prices[bOpt];
                            dp[i+1][nextJ1][nextJ2] = Math.min(dp[i+1][nextJ1][nextJ2], newCost);

                        }
                    }
                }
            }
        }
        // 정답 찾기: N일차까지 마쳤을 때, 남은 기간(j, k)에 상관없이 최소 비용 찾기
        int ans = Integer.MAX_VALUE;
        for(int j=0; j<5; j++) {
            for(int k=0; k<5; k++) {
                ans = Math.min(ans, dp[N][j][k]);
            }
        }
        System.out.println(ans);

    }
}

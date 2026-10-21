import java.util.*;

class Solution {
    // 전체 상담 유형 수
    static int types;
    // 해당 유형에 멘토 count명을 배정했을 때 총 대기시간
    static int[][] wait;
    // 지금까지 확인한 배분 중 총 대기 시간
    static int answer;

    public int solution(int k, int n, int[][] reqs) {
        types = k;
        answer = Integer.MAX_VALUE;
        
        // 한 유형에 최대로 배치할 수 있는 인원
        int maxMentors = n - k + 1;

        // 각 유형별 멘토를 배치했을 때 대기시간
        wait = new int[k + 1][maxMentors + 1];

        
        for (int type = 1; type <= k; type++) {
            for (int count = 1; count <= maxMentors; count++) {
                wait[type][count] =
                    calculateWait(type, count, reqs);
            }
        }

        dfs(1, n, 0);

        return answer;
    }

    static int calculateWait(int type, int count, int[][] reqs) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < count; i++) {
            pq.add(0);
        }

        int totalWait = 0;

        for (int[] req : reqs) {
            if (req[2] != type) {
                continue;
            }

            int arrive = req[0];
            int duration = req[1];

            int available = pq.poll();
            int start = Math.max(arrive, available);

            totalWait += start - arrive;
            pq.add(start + duration);
        }

        return totalWait;
    }

    static void dfs(int type, int remaining, int totalWait) {
        if (type == types) {
            int result = totalWait + wait[type][remaining];

            answer = Math.min(answer, result);
            return;
        }

        int remainingTypes = types - type;
        int maxCount = remaining - remainingTypes;

        for (int count = 1; count <= maxCount; count++) {
            dfs(
                type + 1,
                remaining - count,
                totalWait + wait[type][count]
            );
        }
    }
}
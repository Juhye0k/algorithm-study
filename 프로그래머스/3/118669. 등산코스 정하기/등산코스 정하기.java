import java.util.*;

class Solution {
    static class Edge {
        int to;
        int time;

        Edge(int to, int time) {
            this.to = to;
            this.time = time;
        }
    }

    public int[] solution(
        int n, int[][] paths, int[] gates, int[] summits
    ) {
        List<List<Edge>> graph = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] path : paths) {
            int a = path[0];
            int b = path[1];
            int time = path[2];

            graph.get(a).add(new Edge(b, time));
            graph.get(b).add(new Edge(a, time));
        }

        boolean[] isGate = new boolean[n + 1];
        boolean[] isSummit = new boolean[n + 1];

        for (int gate : gates) {
            isGate[gate] = true;
        }

        for (int summit : summits) {
            isSummit[summit] = true;
        }

        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        // [지점 번호, 해당 지점까지의 intensity]
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[1], b[1])
        );

        // 모든 출입구에서 동시에 출발
        for (int gate : gates) {
            dist[gate] = 0;
            pq.add(new int[]{gate, 0});
        }

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int num = current[0];
            int intensity = current[1];

            // 더 좋은 경로가 이미 발견된 오래된 정보
            if (intensity > dist[num]) continue;

            // 정상은 목적지이므로 경유하지 않음
            if (isSummit[num]) continue;

            for (Edge edge : graph.get(num)) {
                // 출입구를 중간에 방문하지 않음
                if (isGate[edge.to]) continue;

                int nextIntensity =
                    Math.max(intensity, edge.time);

                if (nextIntensity < dist[edge.to]) {
                    dist[edge.to] = nextIntensity;
                    pq.add(new int[]{edge.to, nextIntensity});
                }
            }
        }

        int answerSummit = Integer.MAX_VALUE;
        int answerIntensity = Integer.MAX_VALUE;

        for (int summit : summits) {
            if (dist[summit] < answerIntensity
                || (dist[summit] == answerIntensity
                    && summit < answerSummit)) {

                answerSummit = summit;
                answerIntensity = dist[summit];
            }
        }

        return new int[]{answerSummit, answerIntensity};
    }
}
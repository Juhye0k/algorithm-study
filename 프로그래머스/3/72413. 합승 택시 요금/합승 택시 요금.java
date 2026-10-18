import java.util.*;

class State {
    int node;
    int cost;

    State(int node, int cost) {
        this.node = node;
        this.cost = cost;
    }
}

class Edge {
    int num;
    int value;

    Edge(int num, int value) {
        this.num = num;
        this.value = value;
    }
}

class Solution {
    static List<List<Edge>> list;

    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;

        list = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }

        // 1. 그래프를 먼저 완성
        for (int[] fare : fares) {
            int start = fare[0];
            int end = fare[1];
            int value = fare[2];

            list.get(start).add(new Edge(end, value));
            list.get(end).add(new Edge(start, value));
        }

        // 2. 완성된 그래프에서 다익스트라 세 번 실행
        int[] slist = dijkstra(s, n);
        int[] alist = dijkstra(a, n);
        int[] blist = dijkstra(b, n);

        // 3. 헤어질 지점을 하나씩 시도
        for (int x = 1; x <= n; x++) {
            if (slist[x] == Integer.MAX_VALUE
                    || alist[x] == Integer.MAX_VALUE
                    || blist[x] == Integer.MAX_VALUE) {
                continue;
            }

            int total = slist[x] + alist[x] + blist[x];
            answer = Math.min(answer, total);
        }

        return answer;
    }

    public static int[] dijkstra(int start, int n) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<State> q = new PriorityQueue<>(
            (a, b) -> Integer.compare(a.cost, b.cost)
        );

        dist[start] = 0;
        q.add(new State(start, 0));

        while (!q.isEmpty()) {
            State current = q.poll();
            int node = current.node;
            int cost = current.cost;

            if (cost > dist[node]) {
                continue;
            }

            for (Edge edge : list.get(node)) {
                int next = edge.num;
                int nextCost = cost + edge.value;

                if (nextCost < dist[next]) {
                    dist[next] = nextCost;
                    q.add(new State(next, nextCost));
                }
            }
        }

        return dist;
    }
}
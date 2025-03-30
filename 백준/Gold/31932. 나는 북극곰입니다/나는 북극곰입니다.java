import java.io.*;
import java.util.*;

class Edge {
    int vertex;
    long value;   // 간선 이동 시간
    long second;  // 다리 붕괴 시간

    public Edge(int v, long vv, long vvv) {
        vertex = v;
        value = vv;
        second = vvv;
    }
}

public class Main {
    static PriorityQueue<Edge> pq;
    static List<List<Edge>> list;
    static long dist[];
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }

        long maxCollapse = 0;  // 모든 간선의 붕괴시간 최댓값
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            long distance = Long.parseLong(st.nextToken());
            long second = Long.parseLong(st.nextToken());
            list.get(start).add(new Edge(end, distance, second));
            list.get(end).add(new Edge(start, distance, second));
            maxCollapse = Math.max(maxCollapse, second);
        }

        long left = 0;
        long right = maxCollapse;
        long mid;
        long ans = -1;
        while (left <= right) {
            mid = left + (right - left) / 2;
            long result = dijkstra(1, mid);
            if (result != Long.MAX_VALUE) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        System.out.println(ans);
    }

    public static long dijkstra(int start, long candidate) {
        pq = new PriorityQueue<>((a, b) -> Long.compare(a.value, b.value));
        dist = new long[N + 1];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[start] = 0;
        pq.offer(new Edge(start, 0, 0));

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            if (dist[cur.vertex] < cur.value) continue;
            for (Edge edge : list.get(cur.vertex)) {
                long newTime = cur.value + edge.value;
                if ((edge.second - newTime) >= candidate && newTime < dist[edge.vertex]) {
                    dist[edge.vertex] = newTime;
                    pq.offer(new Edge(edge.vertex, newTime, 0));
                }
            }
        }
        return dist[N];
    }
}

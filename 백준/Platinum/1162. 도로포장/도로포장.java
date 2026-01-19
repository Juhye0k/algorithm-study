
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


class Node {
    int vertex;
    long value;
    public Node(int vertex, long value) {
        this.vertex = vertex;
        this.value = value;
    }
}

class Edge implements Comparable<Edge>{
    int vertex;
    long value;
    int count;
    public Edge(int vertex, long value, int count) {
        this.vertex = vertex;
        this.value = value;
        this.count = count;
    }
    @Override
    public int compareTo(Edge o) {
        return Long.compare(this.value , o.value);
    }
}


public class Main {

    static List<List<Node>> graph;
    static int N,K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 도시의 수 N
        N = Integer.parseInt(st.nextToken());
        // 도로의 수 M
        int M = Integer.parseInt(st.nextToken());
        // 포장할 도로의 수 K
        K = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for(int i=0; i<=N; i++)
            graph.add(new ArrayList<>());

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            // 양방향 설정
            graph.get(start).add(new Node(end, cost));
            graph.get(end).add(new Node(start, cost));
        }
        dijkstra(1);
    }
    public static void dijkstra(int start) {
        long [][] dist = new long[N+1][K+1];
        long result = Long.MAX_VALUE;
        for(int i=0; i<=N; i++) {
            Arrays.fill(dist[i], Long.MAX_VALUE);
        }
        dist[start][0] = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(start,0,0));
        // 다익스트라 구성 시, 포장 여부를 고려해서 거리를 갱신해야함. 그냥 갱신하면 안된다
        while(!pq.isEmpty()) {
            Edge cur = pq.poll();
            if(dist[cur.vertex][cur.count] < cur.value) continue;
            for(Node next: graph.get(cur.vertex)) {

                if(cur.count<K) {
                    if(dist[next.vertex][cur.count+1] > dist[cur.vertex][cur.count]) {
                        dist[next.vertex][cur.count+1] = dist[cur.vertex][cur.count];
                        pq.offer(new Edge(next.vertex, dist[cur.vertex][cur.count], cur.count+1));
                    }
                }
                // 다음 도로를 포장하지 않겠다
                if (dist[next.vertex][cur.count] > dist[cur.vertex][cur.count]+next.value) {
                    dist[next.vertex][cur.count] = dist[cur.vertex][cur.count]+next.value;
                    pq.offer(new Edge(next.vertex, dist[cur.vertex][cur.count]+next.value, cur.count));
                }
            }
        }
        for(int i=0; i<=K; i++) {
            result = Math.min(result, dist[N][i]);
        }
        System.out.println(result);
    }
}

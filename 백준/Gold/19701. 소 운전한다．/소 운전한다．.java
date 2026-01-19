import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node {
    int vertex;
    int time;
    int k;
    public Node(int vertex, int time, int k) {
        this.vertex = vertex;
        this.time = time;
        this.k = k;
    }
}

class Edge implements Comparable<Edge> {
    int vertex;
    int time;
    int eaten;
    public Edge(int vertex, int time, int eaten) {
        this.vertex = vertex;
        this.time = time;
        this.eaten = eaten;
    }
    @Override
    public int compareTo(Edge o) {
        return this.time - o.time;
    }
}

public class Main {

    static int [] dongas;
    static int V;
    static List<List<Node>> graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        // 도시의 개수 V
        V = Integer.parseInt(st.nextToken());
        // 고속도로의 개수 E
        int E = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for(int i=0; i<=V; i++) {
            graph.add(new ArrayList<>());
        }
        dongas = new int[V+1];
        // 각각의 고속도로 정보
        for(int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            // 돈까스의 맛
            int k = Integer.parseInt(st.nextToken());
            graph.get(x).add(new Node(y,time, k));
            graph.get(y).add(new Node(x, time, k));

        }
        dijkstra(1);


    }
    public static void dijkstra(int start) {
        int [][] dist = new int[V+1][2];
        for(int i=0; i<=V; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        dist[start][0] = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(start,0,0));
        while(!pq.isEmpty()) {
            Edge cur = pq.poll();
            if(dist[cur.vertex][cur.eaten] < cur.time) continue;
            for(Node next: graph.get(cur.vertex)) {
                if(cur.eaten == 0) {
                    if(dist[next.vertex][0] > dist[cur.vertex][0] + next.time) {
                        dist[next.vertex][0] = dist[cur.vertex][0] + next.time;
                        pq.offer(new Edge(next.vertex, dist[next.vertex][0], 0));
                    }
                    if(dist[next.vertex][1] > dist[cur.vertex][0] + next.time - next.k) {
                        dist[next.vertex][1] = dist[cur.vertex][0] + next.time - next.k;
                        pq.offer(new Edge(next.vertex, dist[next.vertex][1], 1));
                    }

                }
                if(cur.eaten == 1) {
                    if(dist[next.vertex][1] > dist[cur.vertex][1] + next.time) {
                        dist[next.vertex][1] = dist[cur.vertex][1] + next.time;
                        pq.offer(new Edge(next.vertex, dist[next.vertex][1], 1));
                    }
                }
            }

        }
        StringBuilder sb = new StringBuilder();
        for(int i=2; i<=V; i++) {
            sb.append(dist[i][1]).append("\n");
        }
        System.out.println(sb);
    }
}

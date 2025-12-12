import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.*;


class Node implements Comparable<Node>{
    int vertex;
    int weight;

    public Node(int vertex, int weight) {
        this.vertex = vertex;
        this.weight = weight;
    }

    public int compareTo(Node n) {
        return this.weight-n.weight;
    }
}

public class Main {

    static List<Integer> house;
    static List<List<Node>> graph;
    static int V;
    static PriorityQueue<Node> pq;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        // 기사단 팀원의 수 N
        int N = Integer.parseInt(st.nextToken());
        // 장소의 수 V
        V = Integer.parseInt(st.nextToken());
        // 도로의 수 E
        int E = Integer.parseInt(st.nextToken());

        house = new ArrayList<>();
        graph = new ArrayList<>();
        pq = new PriorityQueue<>();
        for(int i=0; i<=V; i++) {
            graph.add(new ArrayList<>());
        }
        st = new StringTokenizer(br.readLine());
        // KIST위치 A
        int A = Integer.parseInt(st.nextToken());
        //씨알푸드의 위치 B
        int B = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        // 팀원 N명의 집의 위치 H
        for (int i = 0; i < N; i++) {
            house.add(Integer.parseInt(st.nextToken()));
        }
        // 도로의 양 끝 장소 a,b와 길이 l
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Node(b, l));
            graph.get(b).add(new Node(a, l));
        }
        int result = 0;
        for(int i: house) {
            // 집-KIST 최단거리 + 집-씨알푸드 최단거리
            result = result + dijkstra(i,A) + dijkstra(i,B);
        }
        System.out.println(result);

    }
    public static int dijkstra(int start, int end) {
        // 거리 배열 초기화
        int dist[] = new int[V+1];
        // 최단경로이므로 MAX_VALUE로 초기화
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        // 시작 지점에 연결된 노드를 우선순위큐에 삽입
        pq.add(new Node(start, 0));
        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            // 해당 노드와 연결된 노드 탐색
            for(Node next: graph.get(cur.vertex)) {
                if(dist[cur.vertex] + next.weight < dist[next.vertex]) {
                    dist[next.vertex] = dist[cur.vertex] + next.weight;
                    pq.add(new Node(next.vertex, dist[next.vertex]));
                }
            }
        }
        // 만약 갈 수 없는 길이다 -> return -1
        if(dist[end] == Integer.MAX_VALUE) return -1;
        // 도착 지점까지의 거리
        return dist[end];
    }
}

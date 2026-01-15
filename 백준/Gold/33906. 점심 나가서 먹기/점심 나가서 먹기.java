import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


class Edge {
    int vertex;
    int value;
    public Edge(int vertex, int value) {
        this.vertex = vertex;
        this.value = value;
    }
}

public class Main {

    static List<List<Edge>> graph;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        // 준호의 집을 포함한 건물의 수 N (2~100,000)
        N = Integer.parseInt(st.nextToken());
        // 건물 간의 도로 개수 M (1~200,000)
        int M = Integer.parseInt(st.nextToken());

        int[] food = new int[N+1];
        int[] cafe = new int[N+1];

        graph = new ArrayList<>();
        for(int i=0; i<=N; i++) {
            graph.add(new ArrayList<>());
        }
        // i번 건물 식당들 중 최소 가격
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            food[i] = Integer.parseInt(st.nextToken());
        }
        // j번 건물 카페들 중 최소 가격
        st = new StringTokenizer(br.readLine());
        for(int j=1; j<=N; j++) {
            cafe[j] = Integer.parseInt(st.nextToken());
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            graph.get(u).add(new Edge(v, value));
            graph.get(v).add(new Edge(u, value));
        }
        // 집에서 출발하는 다익스타를 먼저 실행 -> 그래야 어디를 방문할 수 있는지 체크 가능
        int[] dist1 = dijkstra(1);

        // 가장 싼 식당, 가장 싼 식당 찾기
        int foodPrice = Integer.MAX_VALUE;
        int foodIndex = -1;
        for(int i =0; i<=N; i++) {
            if(food[i]!=0 && dist1[i] != Integer.MAX_VALUE && food[i]<foodPrice) {
                foodPrice = food[i];
                foodIndex = i;
            }
        }

        // 방문가능한 곳 중 가장 싼 카페 찾기
        int cafePrice = Integer.MAX_VALUE;
        int cafeIndex = -1;
        for(int i =0; i<=N; i++) {
            if(cafe[i]!=0 && dist1[i] != Integer.MAX_VALUE && cafe[i]<cafePrice) {
                cafePrice = cafe[i];
                cafeIndex = i;
            }
        }
        // 어제 선택된 식당에서 출발하는 다익스트라 실행
        int[] dist2 = dijkstra(foodIndex);
        long answer = (long)dist1[foodIndex] + dist2[cafeIndex] + dist1[cafeIndex];
        System.out.println(answer);

    }
    public static int[] dijkstra(int start) {
        int [] dist = new int[N+1];
        PriorityQueue<Edge> pq = new PriorityQueue<>((a,b) -> a.value-b.value);
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        pq.offer(new Edge(start,0));
        while(!pq.isEmpty()) {
            Edge cur = pq.poll();
            if(dist[cur.vertex] < cur.value) continue;
            for(Edge next: graph.get(cur.vertex)) {
                if(dist[cur.vertex] != Integer.MAX_VALUE && dist[next.vertex] > dist[cur.vertex] + next.value) {
                    dist[next.vertex] = dist[cur.vertex] + next.value;
                    // 지금까지 발견한 경로 중, 시작점에서 가장 가까운 정점부터 꺼내서 탐색해야함 -> 우선순위 큐에서 내부적으로 오름차순 정렬중
                    // 만약 next.value를 넣으면 시작점에서 얼마나 떨어져있는지 상관없이, 눈앞에 보이는 도로 비용이 싼 것부터 나오기 때문에 안됨
                    pq.offer(new Edge(next.vertex, dist[next.vertex]));
                }
            }
        }
        return dist;
    }
}

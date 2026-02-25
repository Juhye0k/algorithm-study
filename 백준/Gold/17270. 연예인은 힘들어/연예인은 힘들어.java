
import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


class Edge implements Comparable<Edge>{
    int vertex;
    int value;
    public Edge(int vertex, int value) {
        this.vertex = vertex;
        this.value = value;
    }
    public int compareTo(Edge e){
        return this.value-e.value;
    }
}

public class Main {

    static List<List<Edge>> graph;
    static int V;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        // 약속장소 후보 수 V
        V = Integer.parseInt(st.nextToken());

        // 총 길이수 M
        int M =  Integer.parseInt(st.nextToken());
        graph = new LinkedList<>();
        for(int i=0; i<=V;i++){
            graph.add(new LinkedList<>());
        }
        for(int i=0; i<M; i++){
            st =  new StringTokenizer(br.readLine());

            int a =  Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Edge(b,c));
            graph.get(b).add(new Edge(a,c));
        }
        st = new StringTokenizer(br.readLine());
        // 지헌이 시작
        int J = Integer.parseInt(st.nextToken());
        // 성하 시작
        int S = Integer.parseInt(st.nextToken());
        // 지헌이 출발 다익스트라
        int[] jdist= dijkstra(J);
        int [] sdist = dijkstra(S);

        // 일단 최단거리 구하기
        int distance = Integer.MAX_VALUE;
        for(int i=1; i<=V; i++) {
            if(i==J || i==S) continue;
            
            if(jdist[i]!=Integer.MAX_VALUE && sdist[i]!=Integer.MAX_VALUE){
                distance = Math.min(distance,jdist[i]+sdist[i]);
            }
        }
        // 도착 후보지
        List<Integer> list = new LinkedList<>();
        // 지헌이 다익스트라
        for(int i=1; i<=V; i++){
            // 성하, 지연의 출발지는 제외
            if (i==J || i==S) continue;
            // 최단거리이면서, 지헌이가 성하보다 빨리 도착하는 곳만 후보지
            if(jdist[i]+sdist[i]==distance && jdist[i]<=sdist[i]){
                list.add(i);
            }
        }
        // 정답 번호
        int ans = Integer.MAX_VALUE;
        // 정답 거리
        int ansValue = Integer.MAX_VALUE;
        for(int i=0; i<list.size(); i++){
            if(jdist[list.get(i)]<=ansValue) {
                // 만약 거리가 같으면, 번호가 작은 곳이 정답
                if(jdist[list.get(i)]==ansValue){
                    ans = Math.min(ans,list.get(i));
                }
                // 아니면 갱신
                else {
                    ans = list.get(i);
                }
                ansValue = jdist[list.get(i)];
            }
        }
        if(ans==Integer.MAX_VALUE){
            System.out.println(-1);
            return;
        }
        System.out.println(ans);

    }

    static int[] dijkstra(int start) {
        int[] dist = new int[V+1];
        Arrays.fill(dist,Integer.MAX_VALUE);
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start,0));
        dist[start] = 0;
        while(!pq.isEmpty()){
            Edge e = pq.poll();
            if (e.value > dist[e.vertex]) continue;
            for(Edge e1: graph.get(e.vertex)) {
                if(dist[e1.vertex]>dist[e.vertex]+e1.value){
                    dist[e1.vertex] = dist[e.vertex]+e1.value;
                    pq.add(new Edge(e1.vertex,dist[e1.vertex]));
                }
            }
        }
        return dist;
    }
}
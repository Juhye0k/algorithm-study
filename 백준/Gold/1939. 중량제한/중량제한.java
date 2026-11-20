import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.io.*;

class Edge {
    int num;
    int value;

    public Edge(int num, int value) {
        this.num = num;
        this.value = value;
    }
}

public class Main {

    static List<List<Edge>> graph;
    static int N;
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());


        graph = new ArrayList<>();
        for(int i=0; i<=N; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            graph.get(start).add(new Edge(end, value));
            graph.get(end).add(new Edge(start, value));
        }
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        int[] dist = dijkstra(start);
        System.out.println(dist[end]);
    }
    public static int[] dijkstra(int start) {
        int[] dist = new int[N+1];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        dist[start] = Integer.MAX_VALUE;
        pq.add(new int[]{start,dist[start]});
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            if(cur[1] < dist[cur[0]]) continue;
            for(Edge e : graph.get(cur[0])) {
                int nextWeight = Math.min(cur[1], e.value);
                if(nextWeight > dist[e.num]) {
                    dist[e.num] = nextWeight;
                    pq.offer(new int[]{e.num, nextWeight});
                }

            }
        }
        return dist;
    }
}

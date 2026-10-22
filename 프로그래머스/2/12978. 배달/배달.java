import java.util.*;
class Edge {
    int num;
    int value;
    
    public Edge(int num, int value) {
        this.num = num;
        this.value = value;
    }
}
class Solution {
    static List<List<Edge>> graph;
    static int N;
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        this.N = N;
        
        graph = new LinkedList<>();
        for(int i=0; i<=N; i++) {
            graph.add(new LinkedList<>());
        }
        for(int i=0; i<road.length; i++) {
            int start = road[i][0];
            int end = road[i][1];
            int value = road[i][2];
            graph.get(start).add(new Edge(end,value));
            graph.get(end).add(new Edge(start,value));
        }
        int[] dist = dijkstra(1);
        for(int i=1; i<dist.length; i++) {
            if(dist[i]<=K) answer++;
        }
        return answer;
    }
    public static int[] dijkstra(int start) {
        int[] dist = new int[N+1];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->a[1]-b[1]);
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[start] = 0;
        pq.add(new int[]{start,0});
        while(!pq.isEmpty()) {
            int [] temp = pq.poll();
            int num = temp[0];
            int value = temp[1];
            for(Edge edge : graph.get(num)) {
                if(value>dist[num]) continue;
                
                if(value + edge.value<dist[edge.num]) {
                    dist[edge.num] = edge.value + value;
                    pq.add(new int[]{edge.num, dist[edge.num]});
                }
            }
        }
        return dist;
    }
}
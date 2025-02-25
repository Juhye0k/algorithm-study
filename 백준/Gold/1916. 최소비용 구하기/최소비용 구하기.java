
import java.io.*;
import java.util.*;


class Edge implements Comparable<Edge> {
    int vertex;
    long value;
    public Edge(int vertex, long value) {
        this.vertex = vertex;
        this.value = value;
    }
    @Override
    public int compareTo(Edge other) {
        return Long.compare(this.value, other.value);
    }
}

public class Main {
    static int result;
    static List<List<Integer>> graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            long num = Long.parseLong(st.nextToken());
            graph.get(v1).add(new Edge(v2, num));
        }
        st=new StringTokenizer(br.readLine());
        int start=Integer.parseInt(st.nextToken());
        int end=Integer.parseInt(st.nextToken());
        long[] dist=new long[n+1];
        Arrays.fill(dist,Long.MAX_VALUE);
        dist[start]=0;
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(start,0));
        while(!pq.isEmpty()) {
            Edge cur = pq.poll();
            if(dist[cur.vertex]<cur.value) continue;
            for(Edge next:graph.get(cur.vertex)) {
                if (dist[next.vertex] > dist[cur.vertex] + next.value)
                {
                    dist[next.vertex] = dist[cur.vertex] + next.value;
                    pq.offer(new Edge(next.vertex, dist[next.vertex]));
                }
            }
        }
        System.out.println(dist[end]);;
    }
}

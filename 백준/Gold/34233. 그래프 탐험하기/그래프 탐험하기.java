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
    static List<List<Edge>> pen;
    static int N;
    static long answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        graph = new ArrayList<>();
        pen = new ArrayList<>();
        for(int i=0; i<=N; i++)
            graph.add(new ArrayList<>());
        for(int i=0; i<=N; i++)
            pen.add(new ArrayList<>());

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            graph.get(start).add(new Edge(end, value));
            graph.get(end).add(new Edge(start, value));
        }
        for (int i = 1; i <= N; i++) {
            graph.get(i).sort(Comparator.comparingInt(e -> e.vertex));
        }
        bfs(1);
        System.out.println(answer);
    }
    public static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        boolean[] visited = new boolean[N+1];
        visited[start] = true;
        int now = start;
        while(!q.isEmpty()) {
            int temp = q.poll();
            int cost = dfs(now, temp, new  boolean[N+1]);
            answer+=cost;
            now = temp;
            for(Edge edge:graph.get(temp)) {
                if(!visited[edge.vertex]) {
                    q.add(edge.vertex);
                    visited[edge.vertex] = true;
                    pen.get(edge.vertex).add(new Edge(temp, edge.value));
                    pen.get(temp).add(new Edge(edge.vertex, edge.value));
                }
            }
        }
        answer +=dfs(now,1,new boolean[N+1]);
    }
    public static int dfs(int now, int end,boolean[] visited) {
        if(now==end) return 0;
        visited[now] = true;
        for(Edge edge:pen.get(now)) {
            if(!visited[edge.vertex]) {
                int res = dfs(edge.vertex, end, visited);
                if(res!=-1)
                    return res+edge.value;
            }
        }
        return -1;
    }
}

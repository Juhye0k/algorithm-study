import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Edge implements Comparable<Edge>{
    int start;
    int end;
    int value;
    public Edge(int start, int end, int value) {
        this.start = start;
        this.end = end;
        this.value = value;
    }
    @Override
    public int compareTo(Edge o) {
        return this.value-o.value;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Edge> edgeList = new ArrayList<>();
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            edgeList.add(new Edge(start, end, cost));
        }
        Collections.sort(edgeList);

        parent = new int[N+1];
        for(int i=1; i<=N; i++) parent[i] = i;
        int sum = 0;
        int count = 0;

        for(Edge e: edgeList) {
            if (count == N - 2) break;

            if(find(e.start)!=find(e.end)) {
                union(e.start,e.end);
                sum+=e.value;
                count++;
            }
        }
        System.out.println(sum);
    }
    static int[] parent;
    static int find(int x) {
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }
    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if(a!=b) parent[b] = a;
    }
}

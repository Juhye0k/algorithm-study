import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


class Edge implements Comparable<Edge>{
    int vertex;
    long value;
    public Edge(int vertex, long value) {
        this.vertex = vertex;
        this.value = value;
    }
    @Override
    public int compareTo(Edge o) {
        return Long.compare(this.value, o.value);
    }
}

public class Main {

    static List<List<Edge>> graph;
    static int N;
    static int [] ar;
    static boolean[] isPrime;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        isPrime = new boolean[10000001];
        // 버스 정류장의 개수 N
        N = Integer.parseInt(st.nextToken());
        // 양방향 도로의 개수 M
        int M = Integer.parseInt(st.nextToken());
        makePrimes();
        ar = new int[N+1];
        // 각 버스 정류장의 재난 코드
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            int code = Integer.parseInt(st.nextToken());
            ar[i] = code;
        }
        graph = new ArrayList<>();
        for(int i=0; i<=N; i++) {
            graph.add(new ArrayList<>());
        }
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            if(!isPrime[ar[u]+ar[v]])  continue;
            graph.get(u).add(new Edge(v, value));
            graph.get(v).add(new Edge(u, value));
        }
        long [] dist = dijkstra(1);
        if(dist[N] == Long.MAX_VALUE) System.out.println("Now where are you?");
        else System.out.println(dist[N]);
    }
    public static long[] dijkstra(int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        long [] dist = new long[N+1];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[1] = 0;
        pq.offer(new Edge(start,0));
        while(!pq.isEmpty()) {
            Edge cur = pq.poll();
            if(dist[cur.vertex]<cur.value) continue;

            for(Edge next: graph.get(cur.vertex)) {
                if(dist[next.vertex]>dist[cur.vertex]+next.value){
                    dist[next.vertex] = dist[cur.vertex]+next.value;
                    pq.offer(new Edge(next.vertex, dist[next.vertex]));
                }
            }
        }
        return dist;
    }
    public static void makePrimes(){
        int max = 10000000;
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        for(int i=2; i*i<=max; i++) {
            if(isPrime[i]) {
                for(int j = i*i; j<=max; j+=i)
                    isPrime[j] = false;
            }
        }
    }
}

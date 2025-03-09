import java.io.*;
import java.util.*;

class Edge{
    int vertex;
    int value;
    public Edge(int vertex, int value){
        this.vertex = vertex;
        this.value = value;
    }
}

class EdgeComparator implements Comparator<Edge>{
    public int compare(Edge e1, Edge e2){
        return e1.value - e2.value;
    }
}

public class Main {
    static List<List<Edge>> graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st=new StringTokenizer(br.readLine());
        PriorityQueue<Edge> queue=new PriorityQueue<>(new EdgeComparator());
        // 정점의 개수 V
        int V=Integer.parseInt(st.nextToken());
        // 간선의 개수 E
        int E=Integer.parseInt(st.nextToken());
        // 시작 정점의 번호 K
        int K=Integer.parseInt(br.readLine());
        graph=new ArrayList<>();
        for(int i=0;i<=V;i++)
            graph.add(new ArrayList<>());

        for(int i=0;i<E;i++){
            st=new StringTokenizer(br.readLine());
            int u=Integer.parseInt(st.nextToken());
            int v=Integer.parseInt(st.nextToken());
            int w=Integer.parseInt(st.nextToken());
            graph.get(u).add(new Edge(v,w));
        }
        queue.add(new Edge(K,0));
        int dist[]=new int[V+1];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[K]=0;
        while(!queue.isEmpty()){
            Edge cur=queue.poll(); // 큐에서 값을 뺀다
            if(dist[cur.vertex]<cur.value) continue;
            for(Edge next:graph.get(cur.vertex)){   // 연결된 간선들을 차례로 방문해보자
                if(dist[next.vertex]>dist[cur.vertex]+next.value){  //
                    dist[next.vertex]=dist[cur.vertex]+next.value;
                    queue.add(new Edge(next.vertex,dist[next.vertex]));
                }
            }
        }
        for(int i=1;i<=V;i++){
            if(dist[i]==Integer.MAX_VALUE) bw.write("INF"+"\n");
            else bw.write(dist[i]+"\n");
        }
        bw.flush();
        bw.close();
    }
}

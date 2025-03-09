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
        Map<Integer,Integer> map=new HashMap<>();
        int ans=0;
        int result=0;
        // 지역의 개수 n
        int n=Integer.parseInt(st.nextToken());
        // 수색 범위 m
        int m=Integer.parseInt(st.nextToken());
        // 길의 개수 r
        int r=Integer.parseInt(st.nextToken());
        graph=new ArrayList<>();
        for(int i=0;i<=n;i++)
            graph.add(new ArrayList<>());
        st=new StringTokenizer(br.readLine());
        for(int i=1;i<=n;i++){
            map.put(i,Integer.parseInt(st.nextToken()));
        }
        // 각 구역에 있는 아이템 수
        for(int i=0;i<r;i++){
            st=new StringTokenizer(br.readLine());
            int a=Integer.parseInt(st.nextToken());
            int b=Integer.parseInt(st.nextToken());
            int l=Integer.parseInt(st.nextToken());
            graph.get(a).add(new Edge(b,l));
            graph.get(b).add(new Edge(a,l));
        }
        for(int i=1;i<=n;i++){
            result=0;
            queue.add(new Edge(i,0)); // 시작점 초기에 큐에 넣기
            int dist[]=new int[n+1];           // 거리 초기화
            Arrays.fill(dist,20);
            dist[i]=0;                        // 시작 정점 거리 0 세팅
            while(!queue.isEmpty()){
                Edge e=queue.poll(); // 큐에서 간선을 하나 뺌
                // 만약 그 간선이 이미 확정 --> continue
                if(dist[e.vertex]<e.value) continue;
                for(Edge next:graph.get(e.vertex)){
                    if(dist[next.vertex]>dist[e.vertex]+next.value){
                        dist[next.vertex]=dist[e.vertex]+next.value;
                        queue.offer(new Edge(next.vertex,dist[next.vertex]));
                    }

                }
            }
            for(int j=1;j<=n;j++){
                if(dist[j]<=m){
                    result+=map.get(j);
                }
            }
            ans=Math.max(ans,result);
        }
        System.out.println(ans);
    }
}

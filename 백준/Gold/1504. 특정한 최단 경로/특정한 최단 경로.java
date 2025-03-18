import java.io.*;
import java.util.*;


class Edge{
    int vertex;
    int value;
    public Edge(int vertex,int value){
        this.vertex=vertex;
        this.value=value;
    }
}
public class Main {
    static List<List<Edge>> graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long ans=0;
        int N=Integer.parseInt(st.nextToken()); // 정점의 개수
        int E=Integer.parseInt(st.nextToken()); // 간선의 개수
        graph=new ArrayList<>();
        for(int i=0;i<=N;i++){
            graph.add(new ArrayList<>());
        }
        for(int i=0;i<E;i++){
            st = new StringTokenizer(br.readLine());
            int start=Integer.parseInt(st.nextToken());
            int end=Integer.parseInt(st.nextToken());
            int value=Integer.parseInt(st.nextToken());
            graph.get(start).add(new Edge(end,value));
            graph.get(end).add(new Edge(start,value));
        }
        st=new StringTokenizer(br.readLine());
        int a1=Integer.parseInt(st.nextToken());
        int a2=Integer.parseInt(st.nextToken());
        int result1[]=dijkstra(1,N);
        int result2[]=dijkstra(a1,N);
        int result3[]=dijkstra(a2,N);
        long path1=(long)result1[a1]+result2[a2]+result3[N];
        long path2=(long)result1[a2]+result3[a1]+result2[N];
        ans=Math.min(path1,path2);
        if(ans >= Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(ans);
        }
    }
    public static int[] dijkstra(int start,int N){
        int dist[]=new int[N+1];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[start]=0;
        PriorityQueue<Edge> q=new PriorityQueue<>((a,b)->a.value-b.value);
        q.add(new Edge(start,0));
        while(!q.isEmpty()){
            Edge cur=q.poll();
            if(cur.value>dist[cur.vertex]) continue;
            for(Edge edge:graph.get(cur.vertex)){
                if(dist[edge.vertex]>dist[cur.vertex]+edge.value){
                    dist[edge.vertex]=dist[cur.vertex]+edge.value;
                    q.add(new Edge(edge.vertex,dist[edge.vertex]));
                }
            }
        }
        return dist;
    }
}
/*
풀이 요약
- 두 정점을 반드시 지나야하는데, 이걸 어떻게 해야하지? --> 아 다익스트라를 여러번 돌리면 되겠다!
- 다익스트라 함수를 만들어서 시작정점을 1, a1,a2(a1,a2는 반드시 지나야 하는 점)로 설정
- 이때 핵심은 1->a1->a2->N, 1->a2->a1->N 두 방법이 된다는 것이다. 처음에 한 경우만 생각해서 틀림
- 그리고 오버플로우시 -1도 체크 안해서 틀림
*/


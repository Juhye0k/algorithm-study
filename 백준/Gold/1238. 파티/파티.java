import java.io.*;
import java.util.*;

class Edge{
    int vertex;
    int value;
    public Edge(int vertex, int value){
        this.vertex=vertex;
        this.value=value;
    }
}

public class Main {
    static List<List<Edge>> list;
    static List<List<Edge>> reverseList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int N=Integer.parseInt(st.nextToken()); // N명의 학생
        int M=Integer.parseInt(st.nextToken()); // M개의 단방향 도로
        int X=Integer.parseInt(st.nextToken()); // 모임 장소

        list=new ArrayList<>(); // 정방향 그래프 생성
        for(int i=0;i<=N;i++){
            list.add(new ArrayList<>());
        }

        for(int i=0;i<M;i++){
            st=new StringTokenizer(br.readLine());
            int start=Integer.parseInt(st.nextToken());   // 도로의 시작점
            int end=Integer.parseInt(st.nextToken());     // 도로의 끝점
            int value=Integer.parseInt(st.nextToken());   // 도로를 지나는데 필요한 소요시간
            list.get(start).add(new Edge(end,value));
        }
        reverseList=new ArrayList<>();
        for(int i=0;i<=N;i++){
            reverseList.add(new ArrayList<>());
        }
        for(int i=1;i<=N;i++){
            for(Edge edge:list.get(i)){
                reverseList.get(edge.vertex).add(new Edge(i,edge.value));
            }
        }
        int[] distFromX=dijkstra(X,list,N);
        int[] distToX=dijkstra(X,reverseList,N);
        int maxTime=0;
        for (int i = 1; i <= N; i++) {
            // 경로가 없는 경우는 무시
            if (distFromX[i] == Integer.MAX_VALUE || distToX[i] == Integer.MAX_VALUE)
                continue;
            maxTime = Math.max(maxTime, distFromX[i] + distToX[i]);
        }
        bw.write(maxTime + "\n");
        bw.flush();
        bw.close();
        br.close();



    }
    static int[] dijkstra(int start,List<List<Edge>> graph, int N){
        int[] dist=new int[N+1];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[start]=0;
        PriorityQueue<Edge> pq=new PriorityQueue<>((a,b)->a.value-b.value);
        pq.add(new Edge(start,0));
        while(!pq.isEmpty()){
            Edge cur=pq.poll();
            int curVertex=cur.vertex;
            int curDist=cur.value;
            // 이미 더 짧은 경로가 존재
            if(curDist>dist[curVertex]) continue;
            for(Edge edge:graph.get(curVertex)){
                if(dist[edge.vertex]>curDist+edge.value){
                    dist[edge.vertex]=curDist+edge.value;
                    pq.add(new Edge(edge.vertex,dist[edge.vertex]));
                }
            }
        }
        return dist;
    }

}


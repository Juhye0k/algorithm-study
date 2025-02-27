import java.io.*;
import java.util.*;

class Edge{
    int vertex;
    int value;
    public Edge(int v, int e){
        vertex=v;
        value=e;
    }
}
class EdgeComparator implements Comparator<Edge> {
    public int compare(Edge e1, Edge e2) {
        return e1.value -e2.value;
    }
}

public class Main {

    static List<List<Edge>> list;
    static PriorityQueue<Edge> pq;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        // 도시의 개수 n
        int n=Integer.parseInt(br.readLine());
        // 버스의 개수 m
        int m=Integer.parseInt(br.readLine());
        pq=new PriorityQueue<>(new EdgeComparator());
        list=new ArrayList<>();
        for(int i=0;i<=n;i++){
            list.add(new ArrayList<>());
        }
        int dist[]=new int[n+1];
        int prev[]=new int[n+1];
        Arrays.fill(dist,Integer.MAX_VALUE);
        // 버스 출발 도시의 번호, 다음에는 도착지의 도시 번호, 또 그 버스 비용
        for(int i=0;i<m;i++){
            st=new StringTokenizer(br.readLine());
            int start=Integer.parseInt(st.nextToken());
            int end=Integer.parseInt(st.nextToken());
            int value=Integer.parseInt(st.nextToken());
            list.get(start).add(new Edge(end,value));
        }
        st=new StringTokenizer(br.readLine());
        int start=Integer.parseInt(st.nextToken());
        int end=Integer.parseInt(st.nextToken());
        dist[start]=0;
        pq.offer(new Edge(start,0));


        while(!pq.isEmpty()){
            Edge cur=pq.poll();
            if(dist[cur.vertex]<cur.value) continue;
            for (Edge edge : list.get(cur.vertex)) {
                if (dist[edge.vertex] > dist[cur.vertex] + edge.value) {
                    dist[edge.vertex] = dist[cur.vertex] + edge.value;
                    prev[edge.vertex] = cur.vertex;
                    pq.offer(new Edge(edge.vertex, dist[edge.vertex]));
                }
            }

        }
        bw.write(dist[end]+"\n");
        List<Integer> path=new ArrayList<>();
        int cur=end;
        while(cur!=0){
            path.add(cur);
            cur=prev[cur];
        }
        Collections.reverse(path);
        bw.write(path.size() + "\n");
        for (int city : path) {
            bw.write(city + " ");
        }
        bw.write("\n");

        bw.flush();
        bw.close();
        br.close();
    }
}

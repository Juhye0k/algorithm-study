import java.io.*;
import java.util.*;


public class Main {
    static List<List<Integer>> graph;
    static boolean[] visited;
    static Queue<Node> queue;
    static class Node{
        int v, cnt;
        public Node(int v, int cnt) {
            this.v = v;
            this.cnt = cnt;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        queue = new LinkedList<>();
        graph= new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N=Integer.parseInt(st.nextToken()); // 도시의 개수 N
        int M=Integer.parseInt(st.nextToken()); // 도로의 개수 M
        int K=Integer.parseInt(st.nextToken()); // 거리 정보 K
        int X=Integer.parseInt(st.nextToken()); // 출발 도시 번호 x

        for(int i=0;i<N+1;i++){
            graph.add(new ArrayList<>());
        }
        visited=new boolean[N+1];

        for(int i=0; i<M; i++){
            st=new StringTokenizer(br.readLine());
            int start=Integer.parseInt(st.nextToken());
            int end=Integer.parseInt(st.nextToken());
            graph.get(start).add(end);
        }
        List<Integer> result=new ArrayList<>();
        BFS(X,K,result);
        Collections.sort(result);
        if(result.size()!=0){
            for(int i:result){
                bw.write(i+"\n");
            }
        }
        else {
            bw.write("-1\n");
        }
        bw.flush();
        bw.close();
    }
    public static void BFS(int start,int K,List<Integer> result){
        visited[start]=true;
        queue.offer(new Node(start,0));
        while(!queue.isEmpty()){
            Node cur=queue.poll();
            if(cur.cnt==K){
                result.add(cur.v);
            }
            for(int num:graph.get(cur.v)){
                if(!visited[num]){
                    queue.add(new Node(num,cur.cnt+1));
                    visited[num]=true;
                }
            }
        }
    }
}


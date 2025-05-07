import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class Main {
    static int N;
    static boolean visited[];
    static List<List<Integer>> graph;
    static BufferedWriter bw;
    static int parent[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        graph = new ArrayList<>();
        parent=new int[N+1];
        for(int i=0; i<=N; i++){
            graph.add(new ArrayList<>());
        }

        for(int i=0; i<N-1; i++){
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            graph.get(parent).add(child);
            graph.get(child).add(parent);
        }
        visited=new boolean[N+1];
        dfs(1);

        for(int i=2; i<=N; i++){
            bw.write(parent[i]+"\n");
        }
        bw.flush();
        bw.close();

    }
    public static void dfs(int start) throws IOException {
        visited[start]=true;
        // dfs 루트에서 탐색
        for(int i:graph.get(start)){
            // 만약 탐색한 점이 v랑 같다
           if(!visited[i]){
               parent[i]=start;
               dfs(i);
           }
        }
    }
}



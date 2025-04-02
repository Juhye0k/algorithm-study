import java.io.*;
import java.util.*;

public class Main {

    static List<List<Integer>> graph;
    static boolean visited[];
    static int result;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        result=0;
        int N=Integer.parseInt(st.nextToken()); // 정점의 개수 N
        int M=Integer.parseInt(st.nextToken()); // 간선의 개수 M
        graph=new ArrayList<>();
        for(int i=0;i<=N;i++)
            graph.add(new ArrayList<>());
        visited=new boolean[N+1];
        for(int i=1;i<=M;i++){
            st=new StringTokenizer(br.readLine());
            int u=Integer.parseInt(st.nextToken());
            int v=Integer.parseInt(st.nextToken());
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        for(int i=1;i<=N;i++){
            if(!visited[i]){
                dfs(i);
                result++;
            }
        }
        System.out.println(result);


    }
    public static void dfs(int num){
        visited[num]=true;
        for(int i:graph.get(num)){
            if(!visited[i])
                dfs(i);
        }
    }
}

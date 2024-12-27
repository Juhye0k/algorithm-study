import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N,M,R,count;
    static boolean[] visited;
    static int order[];
    static List<List<Integer>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        graph=new ArrayList<>();

        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        R=Integer.parseInt(st.nextToken());

        visited=new boolean[N+1];
        order=new int[N+1];
        count=1;

        for(int i=0;i<=N;i++)
            graph.add(new ArrayList<>());


        for(int i=0;i<M;i++)
        {
            st=new StringTokenizer(br.readLine());
            int x=Integer.parseInt(st.nextToken());
            int y=Integer.parseInt(st.nextToken());
            graph.get(x).add(y);
            graph.get(y).add(x);
        }
        for(int i=1;i<=N;i++)
        {
            Collections.sort(graph.get(i), Comparator.reverseOrder());
        }

        DFS(R);
        StringBuilder sb=new StringBuilder();
        for(int i=1;i<=N;i++)
            sb.append(order[i]).append("\n");
        System.out.println(sb);

    }
    public static void DFS(int start){
        visited[start]=true;
        order[start]=count++;
        for(int index:graph.get(start))
        {
            if(!visited[index])
            {
                DFS(index);
            }
        }
    }
}

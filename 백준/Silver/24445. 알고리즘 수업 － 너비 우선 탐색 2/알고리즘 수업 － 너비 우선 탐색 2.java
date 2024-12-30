import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N,M,R;

    //간선의 정보를 받기 위한 이중 List 선언
    static List<List<Integer>> graph;
    // 방문한 정점을 기록하기 위해 boolean 배열 선언
    static boolean[] visited;

    // 순서를 기록
    static int node[];
    static int count;
    // 큐 선언 (BFS는 큐를 이용하기 때문)
    static Queue<Integer> q;

    public static void main(String[] args) throws IOException {

        // 입력을 받음
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        q=new LinkedList<>();
        int N=Integer.parseInt(st.nextToken());
        int M=Integer.parseInt(st.nextToken());
        int R=Integer.parseInt(st.nextToken());

        graph=new ArrayList<>();
        node=new int[N+1];
        count=1;

        for(int i=0;i<=N;i++)
        {
            graph.add(new ArrayList<>());
        }
        visited=new boolean[N+1];

        for(int i=1;i<=M;i++)
        {
            st=new StringTokenizer(br.readLine());
            int x=Integer.parseInt(st.nextToken());
            int y=Integer.parseInt(st.nextToken());
            graph.get(x).add(y);
            graph.get(y).add(x);
        }
        for(int i=1;i<=N;i++)
        {
            Collections.sort(graph.get(i),Comparator.reverseOrder());
        }
        bfs(R);
        for(int i=1;i<=N;i++)
            System.out.println(node[i]);
    }
    public static void bfs(int start)
    {
        q.add(start);
        visited[start]=true;
        node[start]=count++;
        while(!q.isEmpty())
        {
            int u=q.poll();
            for(int v:graph.get(u))
            {
                if(!visited[v])
                {
                    visited[v]=true;
                    node[v]=count++;
                    q.add(v);
                }
            }

        }
    }
}

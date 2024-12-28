import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N,M,R;
    static Queue<Integer> q;
    static List<List<Integer>> graph;
    static boolean[] visited;
    static int count;
    static int[] node;
    public static void main(String[] args) throws IOException {
       BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
       StringTokenizer st=new StringTokenizer(br.readLine());
       // 정점, 간선, 시작 정점 입력
       N=Integer.parseInt(st.nextToken());
       M=Integer.parseInt(st.nextToken());
       R=Integer.parseInt(st.nextToken());
       count=1;
       q=new LinkedList<>();
       graph=new ArrayList<>();
       visited=new boolean[N+1];
       node=new int[N+1];
       // 그래프 초기화
       for(int i=0;i<=N;i++)
       {
           graph.add(new ArrayList<>());
       }

       //간선 입력
       for(int i=1;i<=M;i++)
       {
           st=new StringTokenizer(br.readLine());
           int x=Integer.parseInt(st.nextToken());
           int y=Integer.parseInt(st.nextToken());
           graph.get(x).add(y);
           graph.get(y).add(x);
       }

       // 오름차순이므로 정렬
       for(int i=1;i<=N;i++)
       {
           Collections.sort(graph.get(i));
       }
       BFS(R);
       for(int i=1;i<=N;i++){
           System.out.println(node[i]);
       }


    }

    public static void BFS(int start)
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

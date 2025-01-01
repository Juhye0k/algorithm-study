import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static boolean visited[];
    static List<List<Integer>> graph;
    static Queue<Integer> queue;
    static int count;
    public static void main(String[] args) throws IOException {

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        // 컴퓨터의 수 M 입력
        int M=Integer.parseInt(br.readLine());
        // 연결되어 있는 컴퓨터 쌍의 수 N 입력
        int N=Integer.parseInt(br.readLine());

        queue=new LinkedList<Integer>();
        //그래프 초기화
        graph=new ArrayList<>();
        for(int i=0;i<=M;i++)
        {
            graph.add(new ArrayList<>());
        }

        // 방문한 정점 visited 배열 초기화
        visited=new boolean[M+1];

        // 바이러스에 걸린 컴퓨터의 수
        count=0;

        StringTokenizer st;

        // 번호 쌍 입력
        for(int i=0;i<N;i++)
        {
            st=new StringTokenizer(br.readLine());
            int x=Integer.parseInt(st.nextToken());
            int y=Integer.parseInt(st.nextToken());
            graph.get(x).add(y);
            graph.get(y).add(x);
        }
        BFS(1);
        System.out.println(count);

    }
    public static void BFS(int start)
    {
        queue.add(start);
        visited[start]=true;
        while(!queue.isEmpty())
        {
            for(int q:graph.get(queue.poll()))
            {
                if(!visited[q])
                {
                    queue.add(q);
                    visited[q]=true;
                    count++;
                }
            }
        }
    }
}

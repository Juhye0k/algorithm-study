import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {
    public static int N;
    public static int M;
    public static int V;
    public static boolean[] visit;
    public static int[][] arr;
    public static StringBuilder sb=new StringBuilder();
    public static Queue<Integer> q=new LinkedList<>();


    public static void main(String[] args)throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        V=Integer.parseInt(st.nextToken());

        arr=new int[N+1][N+1];
        visit=new boolean[N+1];

        for(int i=0;i<M;i++)
        {
            st=new StringTokenizer(br.readLine());
            int x=Integer.parseInt(st.nextToken());
            int y=Integer.parseInt(st.nextToken());
            arr[x][y]=arr[y][x]=1;
        }
        DFS(V);
        Arrays.fill(visit,false);
        sb.append("\n");
        BFS(V);
        System.out.println(sb);

    }
    public static void DFS(int v)
    {
        visit[v]=true;
        sb.append(v).append(" ");
        for(int i=1;i<=N;i++)
        {
            if(arr[v][i]==1 && !visit[i])
            {
                DFS(i);
            }
        }
    }
    public static void BFS(int v)
    {
        visit[v]=true;
        q.add(v);
        sb.append(v).append(" ");
        while(!q.isEmpty())
        {
            int x=q.poll();
            for(int y=1;y<=N;y++)
            {
                if(arr[x][y]==1 && !visit[y])
                {
                    q.add(y);
                    sb.append(y).append(" ");
                    visit[y]=true;
                }
            }
        }
    }


}


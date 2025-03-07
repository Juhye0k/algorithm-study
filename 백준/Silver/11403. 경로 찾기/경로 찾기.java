import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int ar[][];
    static Queue<Integer> queue;
    static boolean visited[];
    static int result[][];
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        // 정점의 개수 N
        N=Integer.parseInt(br.readLine());
        // 그래프의 인접 행렬
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                ar=new int[N][N];
                result=new int[N][N];
            }
        }


        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                int num=Integer.parseInt(st.nextToken());
                ar[i][j]=num;
            }
        }
        for(int i=0;i<N;i++){
            bfs(i);
        }
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                bw.write(result[i][j]+" ");
            }
            bw.write("\n");
        }
        bw.flush();
        bw.close();

    }
    public static void bfs(int start) {
        queue=new LinkedList<>();
        visited=new boolean[N];
        queue.add(start);
        while (!queue.isEmpty()) {
            int temp = queue.poll();
            for (int i=0;i<N;i++) {
                if (ar[temp][i] == 1&&!visited[i]) {
                    visited[i]=true;
                    result[start][i] = 1;
                    queue.add(i);
                }
            }
        }
    }
}

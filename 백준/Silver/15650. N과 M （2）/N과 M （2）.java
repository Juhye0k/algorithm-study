import java.io.*;
import java.util.*;

public class Main {
    static boolean visited[];
    static int N,M;
    static int[] arr;
    static List<Integer> list;
    static BufferedWriter bw;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw=new BufferedWriter(new OutputStreamWriter(System.out));
        // 자연수 N과 M이 주어짐
        StringTokenizer st=new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        arr=new int[M];
        list=new ArrayList<>();
        visited=new boolean[N+1];

        dfs(1,0);
        bw.flush();
        bw.close();

    }
    public static void dfs(int start,int depth) throws IOException {
       if(depth==M){
           for(int i=0;i<M;i++){
               bw.write(arr[i]+" ");
           }
           bw.write("\n");
           return;
       }
       for(int i=start;i<=N;i++){
           if(!visited[i]){
               visited[i]=true;
               arr[depth]=i;
               dfs(i,depth+1);
               visited[i]=false;
           }
       }
    }
}
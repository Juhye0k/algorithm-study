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
        st=new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            list.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(list);
        dfs(0);
        bw.flush();
        bw.close();

    }
    public static void dfs(int depth) throws IOException {
       if(depth==M){
           for(int i=0;i<M;i++){
               bw.write(arr[i]+" ");
           }
           bw.write("\n");
           return;
       }
       for(int i=0;i<list.size();i++){
           int num=list.get(i);
           if(depth>=1&&arr[depth-1]>num)
               continue;
           arr[depth]=num;
           dfs(depth+1);
       }
    }
}
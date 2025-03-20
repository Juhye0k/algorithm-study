import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int max=-1;
    static int A,B;
    static int arr[];
    static boolean visited[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        A=Integer.parseInt(st.nextToken());
        B=Integer.parseInt(st.nextToken());
        String s=String.valueOf(A);
        arr = new int[s.length()];
        for(int i=0;i<s.length();i++){
            arr[i]=s.charAt(i)-'0';
        }
        visited=new boolean[s.length()];
        dfs(0,0,arr.length);
        System.out.println(max);
    }
    public static void dfs(int value, int depth, int r){
        // 해당 자릿수가 A가 더 크다 --> return, 백트래킹
        // B가 더 이상 없다 --> -1
        if(depth==r){
            max=Math.max(max,value);
            return;
        }
        for(int i=0;i<arr.length;i++){
            if(visited[i] || (depth==0 && arr[i]==0)) continue; //  첫째 자리가 0일 때 또는 이미 방문한 것일때
            if(value*10+arr[i]>B) continue;

            visited[i]=true;
            dfs(value*10+arr[i],depth+1,r);
            visited[i]=false;
        }
    }

}


import java.io.*;
import java.util.*;

public class Main {
    static List<Integer> list;
    static boolean visited[];
    static Queue<Integer> queue;
    static int result;
    static int dx[]={1,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine());
        StringTokenizer st=new StringTokenizer(br.readLine());
        queue=new LinkedList<>();
        list=new ArrayList<>();
        visited=new boolean[n];
        result=0;
        for(int i=0;i<n;i++){
            list.add(Integer.parseInt(st.nextToken()));
        }
        int Ai=Integer.parseInt(br.readLine());
        bfs(Ai-1,n);
        System.out.println(result);

    }
    public static void bfs(int start,int n){
        visited[start]=true;
        queue.add(start);
        result++;
        while(!queue.isEmpty()){
            int temp=queue.poll();
            // 해당 인덱스의 리스트만큼 점프해야함
            for(int i:dx){
                int next=temp+i*list.get(temp); // 다음 인덱스
                if(next>=0 && next<n && !visited[next]){ // 다음 좌,우 인덱스가 범위 내이면
                    queue.add(next);
                    visited[next]=true;
                    result++;
                }
            }
        }
    }
}


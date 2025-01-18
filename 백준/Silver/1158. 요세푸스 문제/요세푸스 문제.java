import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb=new StringBuilder();
        Queue<Integer> q=new LinkedList<>();
        StringTokenizer st=new StringTokenizer(br.readLine());
        int N=Integer.parseInt(st.nextToken());
        int K=Integer.parseInt(st.nextToken());
        for(int i=1;i<=N;i++){
            q.add(i);
        }
        sb.append("<");
        while(!q.isEmpty()){
            for(int i=0;i<K-1;i++){
                int x=q.poll();
                q.add(x);
            }
            if(q.size()==1){
                sb.append(q.poll());
            }
            else{
                sb.append(q.poll()+", ");
            }
        }
        sb.append(">");
        bw.append(sb.toString());
        bw.flush();
        bw.close();
    }
}

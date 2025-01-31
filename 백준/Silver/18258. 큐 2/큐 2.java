import java.io.*;
import java.util.*;



public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Deque<Integer> q = new LinkedList<>();
        StringTokenizer st;
        int N=Integer.parseInt(br.readLine());
        for(int i=1;i<=N;i++){
            st=new StringTokenizer(br.readLine());
            String s=st.nextToken();
            switch (s){
                case "push":
                    int num=Integer.parseInt(st.nextToken());
                    q.addFirst(num);
                    break;
                case "front":
                    if(q.isEmpty())
                        bw.write(-1+"\n");
                    else
                        bw.write(q.peekLast()+"\n");
                    break;
                case "back":
                    if(q.isEmpty())
                        bw.write(-1+"\n");
                    else
                        bw.write(q.peekFirst()+"\n");
                    break;
                case "size":
                    bw.write(q.size()+"\n");
                    break;
                case "empty":
                    if(q.isEmpty())
                        bw.write(1+"\n");
                    else
                        bw.write(0+"\n");
                    break;
                case "pop":
                    if(q.isEmpty())
                        bw.write(-1+"\n");
                    else
                        bw.write(q.removeLast()+"\n");
                    break;
            }
        }
        bw.flush();
        bw.close();
    }
}

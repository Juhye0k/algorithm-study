import java.io.*;
import java.util.*;

class Balloon{
    int index;
    int value;
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb=new StringBuilder();


        // 덱 정의
        Deque<Balloon> deque = new ArrayDeque<>();
        // 자연수 N 입력
        int N=Integer.parseInt(br.readLine());
        StringTokenizer st=new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            int n=Integer.parseInt(st.nextToken());
            Balloon balloon=new Balloon();
            balloon.index=i+1;
            balloon.value=n;
            deque.add(balloon);
        }
        int index=0;
        int value=0;
        while(deque.size()>0){
            Balloon balloon=deque.removeFirst();
            index=balloon.index;
            sb.append(index+" ");
            value=balloon.value;
            if (deque.isEmpty()) break;
            if(value>0){
                for(int i=0;i<value-1;i++){
                    Balloon balloon2=deque.removeFirst();
                    deque.addLast(balloon2);
                }
            }
            else{
                for(int i=0;i<Math.abs(value);i++){
                    Balloon balloon2=deque.removeLast();
                    deque.addFirst(balloon2);
                }
            }
        }
        bw.append(sb.toString());
        bw.flush();
        bw.close();
    }
}

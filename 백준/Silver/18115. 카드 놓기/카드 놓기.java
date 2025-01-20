import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Deque<Integer> deque=new ArrayDeque<>();
        int N=Integer.parseInt(br.readLine());
        int ar[]=new int[N];
        StringTokenizer st=new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            ar[i]=Integer.parseInt(st.nextToken());
        }
        for(int i=N-1;i>=0;i--){
            int num=ar[i];
            switch (num){
                case 1:
                    deque.addFirst(N-i);
                    break;
                case 2:
                    int temp=deque.removeFirst();
                    deque.addFirst(N-i);
                    deque.addFirst(temp);
                    break;
                case 3:
                    deque.addLast(N-i);
                    break;
            }
        }
        for(int i:deque)
            bw.append(i+" ");
        bw.flush();
        bw.close();
    }
}

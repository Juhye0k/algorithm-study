import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Queue<Integer> q = new LinkedList<>();
        int n = Integer.parseInt(br.readLine());
        while(true){
            int num=Integer.parseInt(br.readLine());
            if(num==-1) break;
            if(num==0) {
                q.poll();
                continue;
            }
            if(q.size()<n){
                q.add(num);
            }
        }
        if(q.size()==0){
            bw.append("empty");
        }
        else{
            for(int num:q){
                bw.append(num+" ");
            }
        }
        bw.flush();
        bw.close();
    }
}

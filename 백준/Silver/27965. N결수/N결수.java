import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st=new StringTokenizer(br.readLine());
        int N=Integer.parseInt(st.nextToken());
        long K=Long.parseLong(st.nextToken());

        long answer=0;
        long next=10;
        for(int i=1;i<=N;i++){
            if(i==next){
                next*=10;
            }
            long length=String.valueOf(i).length();
            answer= (((answer%K)*next+i%K)%K);
        }
        bw.write(answer+"");
        bw.flush();
        bw.close();
    }
}
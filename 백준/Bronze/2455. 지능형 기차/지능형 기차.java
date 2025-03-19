import java.io.*;
import java.util.StringTokenizer;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int now=0;
        int max=0;
        for(int i=0;i<4;i++){
            st=new StringTokenizer(br.readLine());
            int out=Integer.parseInt(st.nextToken());
            now=now-out;
            int in=Integer.parseInt(st.nextToken());
            now=now+in;
            max=Math.max(max,now);
        }
        System.out.println(max);
    }
}


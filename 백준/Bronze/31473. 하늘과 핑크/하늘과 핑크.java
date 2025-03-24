import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        long k=0;
        // 머리카락 쌍의 개수 N
        long N=Integer.parseInt(br.readLine());
        // A
        long asum=0,bsum=0;
        st=new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            asum+=Integer.parseInt(st.nextToken());
        }
        // B
        st=new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            bsum+=Integer.parseInt(st.nextToken());
        }
        System.out.println(bsum+" "+asum);
    }
}

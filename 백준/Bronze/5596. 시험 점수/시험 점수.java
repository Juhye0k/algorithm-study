import java.io.*;
import java.util.*;


public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int a=0;
        int b=0;
        st=new StringTokenizer(br.readLine());
        for(int i=0;i<4;i++){
            a+=Integer.parseInt(st.nextToken());
        }
        st=new StringTokenizer(br.readLine());
        for(int i=0;i<4;i++){
            b+=Integer.parseInt(st.nextToken());
        }
        System.out.println(Math.max(a,b));
    }
}

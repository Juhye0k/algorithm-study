import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N=Integer.parseInt(st.nextToken());
        int M=Integer.parseInt(st.nextToken());
        int ar[]=new int[N+1];

        st=new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++) {
            ar[i]=Integer.parseInt(st.nextToken());
        }
        int s[]=new int[N+1];
        for(int i=1;i<=N;i++) {
            s[i]=ar[i]+s[i-1];
        }
        for(int i=1;i<=M;i++) {
            st=new StringTokenizer(br.readLine());
            int start=Integer.parseInt(st.nextToken());
            int end=Integer.parseInt(st.nextToken());
            bw.write(s[end]-s[start-1]+"\n");
        }
        bw.flush();
        bw.close();
    }
}

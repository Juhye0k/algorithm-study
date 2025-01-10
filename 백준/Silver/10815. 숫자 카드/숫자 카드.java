import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
       BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
       BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));

       StringTokenizer st;
       Set<Integer> set=new HashSet<>();

       int N=Integer.parseInt(br.readLine());
       st=new StringTokenizer(br.readLine());
       for(int i=0;i<N;i++){
           int a=Integer.parseInt(st.nextToken());
           set.add(a);
       }

       int M=Integer.parseInt(br.readLine());
       st=new StringTokenizer(br.readLine());
       for(int i=0;i<M;i++){
           int a=Integer.parseInt(st.nextToken());
           int result = set.contains(a) ? 1 : 0;
           bw.write(result + " ");
       }
       bw.flush();
       bw.close();



    }
}

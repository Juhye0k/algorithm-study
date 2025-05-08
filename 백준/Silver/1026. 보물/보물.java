import java.io.*;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        List<Integer> A = new ArrayList<>();
        List<Integer> B = new ArrayList<>();
        int result=0;
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
           int a=Integer.parseInt(st.nextToken());
           A.add(a);
        }
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            int b=Integer.parseInt(st.nextToken());
            B.add(b);
        }
        Collections.sort(A,Collections.reverseOrder());
        Collections.sort(B);
        for(int i=0;i<N;i++){
            result+=A.get(i)*B.get(i);
        }
        bw.write(result+"");
        bw.flush();
        bw.close();
        br.close();
    }
}



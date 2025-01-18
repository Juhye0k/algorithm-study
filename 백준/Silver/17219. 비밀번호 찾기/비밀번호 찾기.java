import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Map<String,String> map=new HashMap<>();
        StringTokenizer st=new StringTokenizer(br.readLine());
        int N=Integer.parseInt(st.nextToken());
        int M=Integer.parseInt(st.nextToken());
        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());
            String address=st.nextToken();
            String password=st.nextToken();
            map.put(address,password);
        }
        for(int i=0;i<M;i++){
            bw.append(map.get(br.readLine())+"\n");
        }
        bw.flush();
        bw.close();
    }
}

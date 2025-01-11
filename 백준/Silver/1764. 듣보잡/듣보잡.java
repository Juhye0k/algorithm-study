import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Set<String> set1=new HashSet<>();
        Set<String> set2=new HashSet<>();
        Set<String> result=new TreeSet<>();
        StringTokenizer st=new StringTokenizer(br.readLine());
        int N=Integer.parseInt(st.nextToken());
        int M=Integer.parseInt(st.nextToken());
        int count=0;
        for (int i = 0; i < N; i++) {
            set1.add(br.readLine());
        }
        for (int i = 0; i < M; i++) {
            set2.add(br.readLine());
        }
        for(String s:set1){
            if(set2.contains(s)){
                count++;
                result.add(s);
            }
        }
        bw.write(count+"\n");
        for(String s:result){
            bw.write(s+"\n");
        }
        bw.flush();
        bw.close();

    }
}

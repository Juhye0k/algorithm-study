import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Map<Integer,String> map1=new HashMap<>();
        Map<String,Integer> map2=new HashMap<>();
        StringTokenizer st=new StringTokenizer(br.readLine());
        int N=Integer.parseInt(st.nextToken());
        int M=Integer.parseInt(st.nextToken());

        for(int i=0;i<N;i++){
            String str=br.readLine();
            map1.put(i+1,str);
            map2.put(str,i+1);
        }
        for(int i=0;i<M;i++){
            String str=br.readLine();
            if(map2.containsKey(str)){
                bw.write(map2.get(str)+"\n");
            }else{
                bw.write(map1.get(Integer.parseInt(str))+"\n");
            }
        }
        bw.flush();
        bw.close();
    }
}

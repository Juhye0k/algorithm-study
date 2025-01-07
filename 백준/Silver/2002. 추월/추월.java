import java.io.*;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
        Map<String,Integer> inMap=new HashMap<>();
        Map<String,Integer> outMap=new HashMap<>();
        int  count=0;
        // N 입력
        int N=Integer.parseInt(br.readLine());
        String ar[]=new String[N];
        // 대근이 차량
        for(int i=0;i<N;i++)
        {
            inMap.put(br.readLine(),i);
        }
        //영식이 차량
        for(int i=0;i<N;i++)
        {
            String name=br.readLine();
            outMap.put(name,inMap.get(name));
            ar[i]=name;
        }

        for(int i=0;i<N-1;i++)
        {
            for(int j=i+1;j<N;j++)
            {
                if(outMap.get(ar[i])>outMap.get(ar[j]))
                {
                    count++;
                    break;
                }
            }
        }
        bw.write(count+"\n");
        bw.flush();
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N=Integer.parseInt(br.readLine());
        Map<String,List<String>> map = new HashMap<>();
        StringBuilder sb=new StringBuilder();
        List<String> list=new ArrayList<>();
        StringTokenizer st;
        int count=0;
        for(int i=0;i<N;i++)
        {
            st=new StringTokenizer(br.readLine());
            String name=st.nextToken();
            String ring=st.nextToken();
            if(ring.equals("-"))
                continue;
            map.putIfAbsent(ring, new ArrayList<>());
            map.get(ring).add(name);
        }
        for(List<String> group:map.values())
        {
            if(group.size()==2)
            {
                count++;
                sb.append(group.get(0)+" "+group.get(1)+"\n");

            }
        }
        System.out.println(count);
        System.out.println(sb);
    }
}


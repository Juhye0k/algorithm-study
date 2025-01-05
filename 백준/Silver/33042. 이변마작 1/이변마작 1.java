import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N=Integer.parseInt(br.readLine());
        Map<String,Integer> map=new HashMap<>();
        for(int i=1;i<=9;i++)
        {
            map.put(i+"m",4);
        }
        for(int i=1;i<=9;i++)
        {
            map.put(i+"p",4);
        }
        for(int i=1;i<=9;i++)
        {
            map.put(i+"s",4);
        }
        for(int i=1;i<=7;i++)
        {
            map.put(i+"z",4);
        }
        StringTokenizer st=new StringTokenizer(br.readLine());
        int count=1;
        for(int i=0;i<N;i++)
        {
            String str=st.nextToken();
            if(map.get(str)>0)
                map.replace(str,map.get(str)-1);
            else
            {
                System.out.println(count);
                return;
            }
            count++;
        }
        System.out.println(0);



    }
}
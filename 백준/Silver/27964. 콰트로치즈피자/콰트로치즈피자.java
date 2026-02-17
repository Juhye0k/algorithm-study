
import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;



public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        Map<String,Integer> map = new HashMap<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            String str = st.nextToken();
            if(str.length()>=6 && str.substring(str.length()-6,str.length()).equals("Cheese")) {
                map.put(str,map.getOrDefault(str,0)+1);
            }
        }
        if(map.size()>=4)
            System.out.println("yummy");
        else
            System.out.println("sad");


    }

}
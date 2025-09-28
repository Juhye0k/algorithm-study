

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    static Map<String, Integer> map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<T; i++){
            int n = Integer.parseInt(br.readLine());
            int result=1;
            // 의상의 이름과 의상의 종류
            map = new HashMap<>();

            for(int j=0; j<n; j++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                String name = st.nextToken();
                String type = st.nextToken();
                map.put(type,map.getOrDefault(type,0)+1);
            }
            for(int c : map.values()){
                result*=(c+1);
            }
            result-=1;
            sb.append(result).append("\n");
        }
        System.out.println(sb);
    }


}
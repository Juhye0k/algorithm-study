

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    static Map<String, Integer> map;
    static int ar[];
    public static void main(String[] args) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       int N = Integer.parseInt(br.readLine());
       // 과일의 종류가 몇 개인지 파악하기
        // 만약 이미 두 종류 이하의 과일로만 이루어져 있다면 N이 답임
        // 그런데 3종류 이상 있다 -> 앞, 뒤에서 적절하게 빼야함 -> 앞,뒤 중에서 어느 것부터 뽑아야하나?
        // 뺐을 때 종류가 2개 이하인 것을 어떻게 판별하나?

        // 투 포인터 설정
        int start = 0;
        int end = 0;
        int result=0;
        // 개수를 카운트하기 위한 map
        map = new HashMap<>();
        ar = new int[N];
        // 과일 입력받기
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            ar[i] = Integer.parseInt(st.nextToken());
        }
        while(end!=N){
            map.put(String.valueOf(ar[end]),map.getOrDefault(String.valueOf(ar[end]),0)+1);
            // 만약 map의 사이즈가 3 이상이다
            if(map.size()>2){
                map.put(String.valueOf(ar[start]),map.get(String.valueOf(ar[start]))-1);
                if(map.get(String.valueOf(ar[start]))==0){
                    map.remove(String.valueOf(ar[start]));
                }
                start++;
            }
            result=Math.max(result,end-start+1);
            end++;
        }
        System.out.println(result);

    }


}
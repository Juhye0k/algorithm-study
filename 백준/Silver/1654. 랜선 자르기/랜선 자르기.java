import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        // 이미 가지고 있는 랜선의 개수 K
        int K=Integer.parseInt(st.nextToken());
        List<Integer> list=new ArrayList<>();
        // 필요한 랜선의 개수 N
        int N=Integer.parseInt(st.nextToken());
        // 이미 가지고 있는 각 랜선의 길이
        for(int i=0;i<K;i++){
            list.add(Integer.parseInt(br.readLine()));
        }
        long left=1;
        long right = Collections.max(list);
        long ans=0;
        while(left<=right){
            long mid = left + (right - left) / 2;
            long pivot=0;
            for(int i=0;i<list.size();i++){
                pivot+=list.get(i)/mid;
            }
            if(pivot>=N){
                ans=mid;
                left=mid+1;
            }
            else{
                right=mid-1;
            }
        }
        System.out.println(ans);
    }
}

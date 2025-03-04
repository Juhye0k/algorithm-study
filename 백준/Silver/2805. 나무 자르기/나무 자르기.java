import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        List<Integer> list=new ArrayList<>();
        // 나무의 수 N
        int N=Integer.parseInt(st.nextToken());
        // 집으로 가져가려고 하는 나무의 길이
        int M=Integer.parseInt(st.nextToken());
        st=new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            int n=Integer.parseInt(st.nextToken());
            list.add(n);
        }
        long left=1;
        long right=2000000000;
        long ans=0;
        while(left<=right){
            long mid=left+(right-left)/2;
            long pivot=0;
            for(int i=0;i<list.size();i++){
                if(list.get(i)>mid)
                    pivot+=list.get(i)-mid;
            }
            if(pivot>=M){
                ans=mid;
                left=mid+1;
            }
            else
                right=mid-1;
        }
        System.out.println(ans);
    }
}

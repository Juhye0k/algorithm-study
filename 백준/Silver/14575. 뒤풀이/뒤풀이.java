import java.io.*;
import java.util.*;

class People{
    int min;
    int max;
    public People(int min, int max){
        this.min = min;
        this.max = max;
    }
}
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st=new StringTokenizer(br.readLine());
        // 참가자의 수 N , 술의 총량 T
        int N=Integer.parseInt(st.nextToken());
        int T=Integer.parseInt(st.nextToken());
        List<People> people=new ArrayList<>();
        for(int i=0;i<N;i++){
            // Li, Ri가 주어짐
            st=new StringTokenizer(br.readLine());
            People s=new People(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
            people.add(s);
        }
        long left=0;
        long right=1000000000;
        long ans=-1;
        boolean valid=true;
        while(left<=right){
            long mid=left+(right-left)/2;
            long minPivot=0;
            long maxPivot=0;
            valid=true;
            for(int i=0;i<people.size();i++){
                long min=people.get(i).min;
                long max=people.get(i).max;
                if(min>mid){
                    valid=false;
                    break;
                }
                if(max>mid)
                    max=mid;
                minPivot+=min;
                maxPivot+=max;
            }
            if(valid&&minPivot<=T && maxPivot>=T){
                ans=mid;
                right=mid-1;
            }
            else{
                left=mid+1;
            }
        }
        System.out.println(ans);
    }
}

import java.io.*;
import java.util.*;

class Ball{
    long x;
    long y;
    public Ball(long x, long y){
        this.x = x;
        this.y = y;
    }
}

class BallComparator implements Comparator<Ball> {

    @Override
    public int compare(Ball o1, Ball o2) {
        return (int) ((o1.x*o1.x)+(o2.x+o2.y)-(o2.x+o2.x)+(o2.y*o2.y));
    }
}

public class Main {
    static long out;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        // 타구의 개수 N
        int N=Integer.parseInt(br.readLine());
        List<Long> list=new ArrayList<>();
        // 타구가 떨어진 지점 xi, yi
        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());
            long x=Integer.parseInt(st.nextToken());
            long y=Integer.parseInt(st.nextToken());
            if(y>=x && y>=-x)
            {
                list.add(x * x + y * y);
            }
            else{
                out++;
            }
        }
        Collections.sort(list);
        // 담장까지의 거리의 후보 수 Q
        int Q=Integer.parseInt(br.readLine());
        for(int i=0;i<Q;i++){
            long R=Long.parseLong(br.readLine());
            int left=0;
            int right=list.size()-1;
            int mid=0;
            int ans=list.size();
            while(left<=right){
                mid=right+(left-right)/2;
                if(list.get(mid) > R*R){
                    ans=mid;
                    right=mid-1;
                }
                else {
                    left = mid + 1;
                }
            }
            long hit = ans;
            long homeRun=list.size()-hit;
            bw.write(out+" "+hit+" "+homeRun+"\n");
        }
        bw.flush();
        bw.close();
    }


}

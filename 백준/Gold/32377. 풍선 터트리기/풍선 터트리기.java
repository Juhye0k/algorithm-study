import java.io.*;

import java.util.StringTokenizer;


public class Main {  // 백준 32377

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st=new StringTokenizer(br.readLine());

        int N=Integer.parseInt(st.nextToken()); // 풍선의 개수
        int x=Integer.parseInt(st.nextToken());
        int y=Integer.parseInt(st.nextToken());
        int z=Integer.parseInt(st.nextToken());
        long left=1;
        long right=Long.MAX_VALUE;
        long time=0;
        while(left<=right){
            long mid=left+(right-left)/2; // mid 설정
            long pivot=(mid/x)+(mid/y)+(mid/z);  // mid를 통해 터뜨릴 수 있는 풍선 개수
            if(pivot<0){    // 오버플로우 방지
                pivot=Long.MAX_VALUE;
            }
            if(pivot>=N){          // 풍선의 개수가
                time=mid;
                right=mid-1;
            }
            else{
                left=mid+1;
            }
        }
        // 자 ans를 구했다. 이제 마지막에 터뜨린 사람을 어떻게 구해야하니?
        long beforeCount=(time-1)/x+(time-1)/y+(time-1)/z;
        long diff=N-beforeCount;
        if(time%x==0){
            diff--;
            if(diff==0)
                bw.write("A win");
        }
        if(time%y==0){
            diff--;
            if(diff==0)
                bw.write("B win");
        }
        if(time%z==0){
            diff--;
            if(diff==0)
                bw.write("C win");
        }
        bw.flush();
        bw.close();
    }
}

import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int love=0;   // 초기 호감도
        //  정수 N이 주어짐
        int N=Integer.parseInt(br.readLine());
        List<Integer> list=new ArrayList<>();
        // N개의 정수 A1,A2...AN이 공백으로 구분
        StringTokenizer st=new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            list.add(Integer.parseInt(st.nextToken()));  // A 수열 리스트에 추가
        }
        // 정수 L이 주어짐
        int L=Integer.parseInt(br.readLine());
        long left=1;
        long right=1000000000;
        long ans=L;
        while(left<=right){
            long mid=left+(right-left)/2;         // 풀어야 하는 수학 문제 수
            long pivot=0;                         // 해당 문제로 얻은 호감도
              for(int i=0;i<N;i++){
                int x=list.get(i);
                if(mid>=x) // 풀고자하는 문제가 Ai보다 크다
                    pivot+=mid;
                else      // Ai보다 작다
                    pivot+=2*(mid-x);
            }
            if(pivot>=L){
                ans=mid;
                right=mid-1;
            }
            else{
                left=mid+1;
            }
        }
        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
    }
}

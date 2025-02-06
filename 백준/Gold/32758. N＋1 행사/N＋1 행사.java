import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {  // 백준 32758 N+1 행사
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        List<Integer> list=new ArrayList<>();
        int M=Integer.parseInt(br.readLine()); // 상품 종류의 개수 M
        StringTokenizer st=new StringTokenizer(br.readLine()); // 행사값 n1,n2 ... nM이 공백으로 주어짐
        for(int i=0; i<M; i++){
            list.add(Integer.parseInt(st.nextToken()));
        }
        st=new StringTokenizer(br.readLine());   // 가져가고 싶은 상품의 목표 개수 a1,a2... am
        for(int i=0; i<M; i++){
            int productCount=Integer.parseInt(st.nextToken());
            if(productCount==0){
                bw.write("0 ");
                continue;
            }
            int left=1;                                  // 목표 개수가 10^9 --> 행사값이 10^5까지 가능하니, 10^5만큼 사야 1개 더줄 수 있음.
            int right=1000000000;                        // 근데 그 상품의 개수가 10^5이다? 시간초과 날 확률이 높으니 이분탐색 이용해보자
            int mid=1;
            int ans=0;
            int event=list.get(i);
            if(event == 1) {
                bw.write("1 ");
                continue;
            }
            while(left<=right){
                mid=(left+right)/2;
                // N+1로 받은 상품을 다시 N+1에 사용하는 로직을 어떻게 작성하지 ?
                int pivot=mid;
                int coupons=mid;
                while(coupons>=event){
                    int free=coupons/event;
                    pivot+=free;
                    coupons=free+(coupons%event);
                }
                if(pivot>=productCount){
                    ans=mid;
                    right=mid-1;
                }
                else{
                    left=mid+1;
                }
            }
            bw.write(ans+" ");
        }
        bw.flush();
        bw.close();
    }
}

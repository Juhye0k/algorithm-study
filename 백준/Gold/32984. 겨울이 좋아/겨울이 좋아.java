import java.io.*;

import java.util.*;




public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        List<Integer> leaf=new ArrayList<>();
        List<Integer> fall=new ArrayList<>();

        // 나무의 그루 수 N 입력
        int N=Integer.parseInt(br.readLine());
        int day[]=new int[N];

        // 나뭇잎 수 입력
        st=new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            int num=Integer.parseInt(st.nextToken());
            leaf.add(num);
        }

        // 하루에 떨어지는 개수 입력
        st=new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            int num=Integer.parseInt(st.nextToken());
            fall.add(num);
        }
        for(int i=0;i<N;i++){
            int num=leaf.get(i)/fall.get(i);
            if(leaf.get(i)%fall.get(i)>0)
                num++;
            day[i]=(num);   // 각 나무 당 걸리는 날짜 저장
        }

        long left=1;
        long right=1000000000;
        long ans=0;
        while(left<=right){  // 10*6
            long mid=left+(right-left)/2;      // 날짜
            long pivot=0;
            for(int i=0;i<N;i++){            // 해당 날짜로 나뭇잎을 다 떨어뜨릴 수 있는지를 체크해야함
                if(day[i]-mid>0)   // 일반적으로 떨어질 때 해당 나뭇잎을 다떨어지게 하는지 --> 0 이상이면 마법이 필요함
                {
                    pivot+=day[i]-mid;
                }
            }
            // pivot이 mid보다 작거나 같다 -->해당 날짜에 다 떨어뜨릴 수 있다, 즉 right를 이동
            if(pivot<=mid) {
                ans=mid;
                right=mid-1;
            }
            else {
                left = mid + 1;
            }
        }
        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
    }
}


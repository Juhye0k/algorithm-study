import java.io.*;

import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        List<Integer> list=new ArrayList<>();
        int result=0;
        // N과 K가 주어짐
        st=new StringTokenizer(br.readLine());
        int N=Integer.parseInt(st.nextToken());    // 동전의 종류
        int K=Integer.parseInt(st.nextToken());    // 원하는 가치
        // 동전의 가치가 오름차순으로 주어짐
        for(int i=0;i<N;i++){
            int num=Integer.parseInt(br.readLine());
            list.add(num);
        }
        Collections.sort(list,Collections.reverseOrder());
        for(int i=0; i<N; i++){
            int num=list.get(i);
            if(K/num!=0){
                result+=K/num;
                K=K%num;
            }
            if(K==0)
                break;
        }
        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
    }
}

import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st=new StringTokenizer(br.readLine());
        // 사람의 명수
        int N=Integer.parseInt(st.nextToken());
        // M번 뻗는 것
        int M=Integer.parseInt(st.nextToken());
        // 거리 K
        int K=Integer.parseInt(st.nextToken());
        int ar[][]=new int[N][M];
        int result[]=new int[N];
        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                ar[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        for(int i=0;i<M;i++){
            for(int j=0;j<N;j++){
                result[j]+=ar[j][i];
                if(result[j]>=K){
                    bw.write((j+1)+" "+(i+1));
                    bw.flush();
                    bw.close();
                    return;
                }
            }
        }
    }
}

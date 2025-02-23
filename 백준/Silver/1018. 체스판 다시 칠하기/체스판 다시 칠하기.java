
import java.io.*;
import java.util.*;


public class Main {
    static char ar[][];
    static int minCount=64;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N=Integer.parseInt(st.nextToken());
        int M=Integer.parseInt(st.nextToken());
        ar=new char[N][M];
        for(int i=0;i<N;i++){
            String str=br.readLine();
            for(int j=0;j<M;j++){
                ar[i][j]=str.charAt(j);
            }
        }
        for(int i=0;i+7<N;i++){
            for(int j=0;j+7<M;j++){
                checkBoard(i,j);
            }
        }
        System.out.println(minCount);
    }
    public static void checkBoard(int x,int y){
        int count1=0;
        int count2=0;

        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                char current=ar[x+i][y+j];
                if((i+j)%2==0){
                    if(current!='W') count1++;
                    if(current!='B') count2++;
                }else{
                    if(current!='B') count1++;
                    if(current!='W') count2++;
                }
            }
        }
        int min=Math.min(count1,count2);
        minCount=Math.min(minCount,min);
    }
}

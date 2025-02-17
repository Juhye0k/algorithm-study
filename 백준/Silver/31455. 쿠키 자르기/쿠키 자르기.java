import java.io.*;
import java.util.*;

class Node{
    int x;
    int y;
    public Node(int x, int y){
        this.x = x;
        this.y = y;
    }
}
public class Main {
    static int ar[][];
    static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        // 테스트 케이스의 수 T
        int T=Integer.parseInt(br.readLine());
        // N이 주어짐.
        for(int t=0;t<T;t++) {
            answer=0;
            int N = Integer.parseInt(br.readLine());
            ar = new int[N][N];
            for (int i = 0; i < N; i++) {              // N줄에 거쳐 배열의 정보들이 들어감
                String str = br.readLine();
                for (int j = 0; j < N; j++) {
                    ar[i][j] = Integer.parseInt(str.charAt(j) + "");
                    answer+=ar[i][j];
                }
            }
            divide(ar,0,0,N);
            bw.write(answer+"\n");
        }
        bw.flush();
        bw.close();
    }
    public static void divide(int ar[][],int rowStart,int colStart,int size){
        if(size==1)
            return;
        int sum=0;
        for(int i=rowStart;i<rowStart+size;i++){
            for(int j=colStart;j<colStart+size;j++){
                sum+=ar[i][j];
            }
        }
        int x=sum%4;
        List<Node> list=new ArrayList<>();
        list.add(new Node(rowStart,colStart));
        list.add(new Node(rowStart,colStart+size/2));
        list.add(new Node(rowStart+size/2,colStart));
        list.add(new Node(rowStart+size/2,colStart+size/2));
        for(int i=0;i<4;i++){
            if(i==x) {
                for(int j=list.get(i).x;j<list.get(i).x+size/2;j++){
                    for(int k=list.get(i).y;k<list.get(i).y+size/2;k++){
                        answer-=ar[j][k];
                    }
                }
            }
            else{
                divide(ar,list.get(i).x,list.get(i).y,size/2);
            }
        }
    }
}
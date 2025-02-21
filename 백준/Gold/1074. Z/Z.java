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
    static int r,c;
    static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st=new StringTokenizer(br.readLine());
        answer=0;
        int N=Integer.parseInt(st.nextToken());
        r=Integer.parseInt(st.nextToken());
        c=Integer.parseInt(st.nextToken());
        // 크기가 2^N * 2^N인 배열 선언
        int a=(int)Math.pow(2,N);
        divide(0,0,a);
        System.out.println(answer);

    }
    public static void divide(int rowStart, int colStart, int size){
        if(size==1) return;
        int quadSize=size/2*size/2;
        if(r>=rowStart && r<rowStart+size/2 && c>=colStart && c<colStart+size/2){
            divide(rowStart,colStart,size/2);
        }
        else if(r>=rowStart && r<rowStart+size/2 && c>=colStart+size/2 && c<colStart+size){
            answer+=quadSize;
            divide(rowStart,colStart+size/2,size/2);
        }
        else if(r>=rowStart+size/2 && r<rowStart+size && c>=colStart && c<colStart+size/2){
            answer +=2*quadSize;
            divide(rowStart+size/2,colStart,size/2);
        }
        else{
            answer+=3*quadSize;
            divide(rowStart+size/2,colStart+size/2,size/2);
        }
    }
}

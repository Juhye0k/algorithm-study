
import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Node{
    int x,y;
    int value;
    public Node(int x, int y, int value){
        this.x = x;
        this.y = y;
        this.value = value;
    }
}

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st =  new StringTokenizer(br.readLine());
        int result=0;
        int N = Integer.parseInt(st.nextToken());
        int A =  Integer.parseInt(st.nextToken());
        int B =  Integer.parseInt(st.nextToken());
        Node[] nodes = new Node[N];
        for(int i=0; i<N; i++) {
            st =   new StringTokenizer(br.readLine());
            // 세로
            int r = Integer.parseInt(st.nextToken());
            // 가로
            int c = Integer.parseInt(st.nextToken());
            //가중치
            int S = Integer.parseInt(st.nextToken());
            nodes[i] = new Node(r,c,S);
        }
        for(int i=0; i<N; i++) {
            for(int j=i+1; j<N; j++) {
                Node first = nodes[i];
                Node second = nodes[j];

                int width = Math.abs(first.y-second.y)+1;
                int height = Math.abs(first.x-second.x)+1;

                if(width>B || height>A) continue;
                result=Math.max(result, Math.abs(first.value-second.value));
            }
        }
        System.out.println(result);
    }
}
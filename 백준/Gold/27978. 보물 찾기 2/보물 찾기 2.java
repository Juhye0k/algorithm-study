



import javax.sound.sampled.Line;
import java.awt.*;
import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


class Node{
    int x,y;
    Node(int x,int y){
        this.x=x;
        this.y=y;
    }
}


public class Main {


    static char [][] ar;
    static int[] dx = {1,1,0,-1,-1,-1,0,1};
    static int[] dy = {0,1,1,1,0,-1,-1,-1};
    static int H,W;
    static int result=Integer.MAX_VALUE;
    static int [][] dist;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        W =  Integer.parseInt(st.nextToken());

        dist = new int[H][W];
        ar = new char[H][W];

        Node start = null;
        Node end = null;

        for(int i=0;i<H;i++){
            String str = br.readLine();
            for(int j=0;j<W;j++){
                ar[i][j] = str.charAt(j);
                dist[i][j] = Integer.MAX_VALUE;

                if(ar[i][j] == 'K') {
                    start = new Node(i, j);
                    dist[i][j] = 0; // 시작점 비용 0
                } else if(ar[i][j] == '*') {
                    end = new Node(i, j);
                }
            }
        }

        bfs(start);
        if(dist[end.x][end.y]==Integer.MAX_VALUE)
            System.out.println(-1);
        else
            System.out.println(dist[end.x][end.y]);
    }
    public static void bfs(Node node) {
        Deque<Node> q = new ArrayDeque<>();
        q.add(node);
        while(!q.isEmpty()) {
            Node temp = q.poll();

            for(int i=0; i<8; i++) {
                int x = temp.x + dx[i];
                int y = temp.y + dy[i];
                if(x>=0 && x<H && y>=0 && y<W &&ar[x][y]!='#') {
                    int weight = i==1||i==2||i==3 ? 0:1;

                    if(dist[x][y] > dist[temp.x][temp.y] + weight) {
                        dist[x][y] = dist[temp.x][temp.y] + weight;
                        if(weight == 0) {
                            q.addFirst(new Node(x,y));
                        }
                        else {
                            q.addLast(new Node(x,y));
                        }
                    }
                }
            }
        }

    }
}
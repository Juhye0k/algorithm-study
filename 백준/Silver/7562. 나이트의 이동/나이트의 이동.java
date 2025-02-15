
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


class Node{
    int x;
    int y;
    int distance;
    public Node(int x, int y,int distance){
        this.x = x;
        this.y = y;
        this.distance=distance;
    }
}

public class Main {
    static int dx[]={2,1,-2,-1,-2,-1,2,1};
    static int dy[]={1,2,1,2,-1,-2,-1,-2};
    static int N,M;
    static BufferedWriter bw;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        // 테스트 케이스
        int N=Integer.parseInt(br.readLine());
        for(int i=0;i<N;i++){
            // 체스판 한 변의 길이 l
            int l=Integer.parseInt(br.readLine());
            Queue<Node> q=new LinkedList<>();
            boolean visited[][]=new boolean[l][l];
            // 나이트가 현재 위치한 칸
            st=new StringTokenizer(br.readLine());
            int startX=Integer.parseInt(st.nextToken());
            int startY=Integer.parseInt(st.nextToken());
            // 나이트가 이동하려고 하는 칸
            st=new StringTokenizer(br.readLine());
            int endX=Integer.parseInt(st.nextToken());
            int endY=Integer.parseInt(st.nextToken());
            if(startX==endX&&startY==endY){
                bw.write(0+"\n");
                continue;
            }
            bfs(new Node(startX,startY,0),endX,endY,l,q,visited);
        }
        bw.flush();
        bw.close();


    }
    public static void bfs(Node start,int endX,int endY,int l,Queue<Node> q,boolean visited[][]) throws IOException {
        q.add(start);
        visited[start.x][start.y]=true;
        while(!q.isEmpty()){
            Node cur=q.poll();
            if(cur.x==endX && cur.y==endY){
                bw.write(cur.distance+"\n");
                return;
            }
            for(int i=0;i<8;i++){
                int x=cur.x+dx[i];
                int y=cur.y+dy[i];
                if(x>=0 && x<l && y>=0 && y<l && !visited[x][y]){
                    q.add(new Node(x,y,cur.distance+1));
                    visited[x][y]=true;
                }
            }
        }
    }



}


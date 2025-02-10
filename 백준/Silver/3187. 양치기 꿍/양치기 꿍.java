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
    static int dx[]={1,0,-1,0};
    static int dy[]={0,1,0,-1};
    static String str[][];
    static boolean visited[][];
    static int R,C;
    static int wolf,sheep;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 영역의 가로, 세로길이
        StringTokenizer st=new StringTokenizer(br.readLine());
        R=Integer.parseInt(st.nextToken());
        C=Integer.parseInt(st.nextToken());
        Queue<Node> queue;
        str=new String[R][C];  // 좌표를 담을 배열
        for(int i=0;i<R;i++){
            String line=br.readLine();
            for(int j=0;j<C;j++){
                str[i][j]= String.valueOf(line.charAt(j));
            }
        }
        visited=new boolean[R][C];
        int resultSheep=0, resultWolf=0;
        // BFS를 돌려서, 양과 늑대가 몇개씩 있는지 체크해야 할듯.
        // 체크 한 다음, 양이 늑대보다 많으면 늑대를 0, 양이 더 적다면 양을 0
        // 상하좌우로 옮기면서, 해당 정점이 .또는 v, k이면 이동 + 정점이 해당 범위에 포함되는지 체크해야함
        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                queue=new LinkedList<>();
                if(!visited[i][j]&&(str[i][j].equals(".")||str[i][j].equals("v")||str[i][j].equals("k"))){
                    sheep=0;
                    wolf=0;
                    bfs(queue,i,j);
                    if(sheep>wolf){  // 양이 늑대보다 많으면 늑대 다죽이기
                        wolf=0;
                    }
                    else{            // 아니면 양을 다 죽이기
                        sheep=0;
                    }
                    resultSheep+=sheep;
                    resultWolf+=wolf;
                }
            }
        }
        bw.write(resultSheep+" "+resultWolf);
        bw.flush();
        bw.close();
    }
    public static void bfs(Queue<Node> queue, int x, int y){
        visited[x][y]=true;                    // 정점 방문
        queue.add(new Node(x,y));              // 큐에 추가
        while(!queue.isEmpty()){               // 큐가 비지 않을때까지
            Node cur=queue.poll();            // 큐에서 꺼내기
            if(str[cur.x][cur.y].equals("v")){       // 해당 정점이 늑대이다 --> 늑대 추가
                wolf++;
            }
            else if(str[cur.x][cur.y].equals("k")){  // 해당 정점이 양이다 --> 양 추가
                sheep++;
            }
            for(int i=0;i<4;i++){                    // 해당 정점에서 상하좌우로 움직이기
                int moveX=cur.x+dx[i];
                int moveY=cur.y+dy[i];
                if(moveX>=0 && moveX<R && moveY>=0 && moveY<C && !visited[moveX][moveY] && !str[moveX][moveY].equals("#")){   // 범위를 만족하고, #이 아닐경우만 움직이기
                    queue.add(new Node(moveX,moveY));
                    visited[moveX][moveY]=true;
                }
            }
        }
    }
}


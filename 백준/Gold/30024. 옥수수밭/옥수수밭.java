


import java.awt.*;
import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


class Node implements Comparable<Node>{
    int x,y;
    int value;
    public Node(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }
    @Override
    public int compareTo(Node o) {
        return o.value - this.value;
    }

}


public class Main {


    static int[][] ar;
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        visited = new boolean[N+1][M+1];
        ar = new int[N+1][M+1];
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=M; j++) {
                ar[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int K = Integer.parseInt(br.readLine());
        // 1. 반복문 돌려서, 바깥쪽 탐색 후 가장 큰 값 제거 ? 복잡한데
        // 초기에, 가장 바깥쪽꺼를 우선순위 큐에 넣자
        for(int i=1; i<=N; i++) {
            for(int j=1; j<=M; j++) {
                if(i==1 || j==1 || i==N || j==M) {
                    pq.add(new Node(i,j,ar[i][j]));
                    visited[i][j] = true;
                }
            }
        }
        for(int i=0; i<K; i++) {
            // 큐에서 빼라
            Node temp = pq.poll();
            sb.append(temp.x).append(" ").append(temp.y).append("\n");

            for(int j=0; j<4; j++) {
                int x = temp.x + dx[j];
                int y = temp.y + dy[j];
                if(x>=1 && x<=N && y>=1 && y<=M && !visited[x][y]) {
                    pq.add(new Node(x,y,ar[x][y]));
                    visited[x][y] = true;
                }
            }
        }
        System.out.println(sb);

    }
}
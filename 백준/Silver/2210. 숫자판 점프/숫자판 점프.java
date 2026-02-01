

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node {
    int x,y;
    String value;
    public Node(int x, int y, String value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }
}

public class Main {

    static int [][] ar;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int result = 0;
    static Set<String> visited = new HashSet<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        ar = new int[5][5];
        for(int i=0; i<5; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<5; j++) {
                ar[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i=0; i<5; i++) {
            for(int j=0; j<5; j++) {
                bfs(new Node(i,j,String.valueOf(ar[i][j])));
            }
        }
        System.out.println(result);


    }
    public static void bfs(Node node) {
        Queue<Node> q = new LinkedList<>();
        q.add(node);
        while(!q.isEmpty()) {
            Node temp = q.poll();
            if(temp.value.length() == 6) {
                if(!visited.contains(temp.value)) {
                    visited.add(temp.value);
                    result++;
                }
                continue;
            }
            for(int i=0; i<4; i++) {
                int x = temp.x + dx[i];
                int y = temp.y + dy[i];
                if(x>=0 && x<5 && y>=0 && y<5) {
                    q.add(new Node(x, y, temp.value+ar[x][y]));
                }
            }
        }
    }
}
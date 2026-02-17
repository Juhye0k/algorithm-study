

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node{
    int x,y;
    int value;
    public Node(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }

}


public class Main {

    static int T;
    static List<List<Node>> graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        // 개수
        int n = Integer.parseInt(st.nextToken());
        // 정상
        T = Integer.parseInt(st.nextToken());
        Node [] ar = new Node[n];
        graph = new ArrayList<>();
        for(int i=0; i<=200000; i++) {
            graph.add(new ArrayList<>());
        }
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            graph.get(y).add(new Node(x,y,0));
        }
        bfs(new Node(0,0,0));

    }
    public static void bfs(Node start) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(start);
        while(!queue.isEmpty()) {
            Node temp = queue.poll();

            if(temp.y==T) {
                System.out.println(temp.value);
                return;
            }

            for(int y = temp.y-2; y<=temp.y+2; y++) {
                if(y<0 || y> 200000) continue;

                for(int i=0; i<graph.get(y).size(); i++) {
                    Node next =graph.get(y).get(i);

                    if(Math.abs(temp.x-next.x)<=2) {
                        queue.add(new Node(next.x,next.y,temp.value+1));
                        graph.get(y).remove(i);
                        i--;
                    }
                }
            }
        }
        System.out.println(-1);

    }

}
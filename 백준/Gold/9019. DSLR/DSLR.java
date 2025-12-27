
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


class Node {
    int register;
    String s;
    public Node(int register, String s) {
        this.register = register;
        this.s = s;
    }
}

public class Main {

    static int start,end;
    static boolean visited[];
    static char[] operator ={'D','S','L','R'};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        // 테스트 케이스 개수 T
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for(int i = 0; i < T; i++){
            st = new StringTokenizer(br.readLine());
            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());

            // 그래프 돌리기
            bfs(new Node(start,""));
        }
    }
    public static void bfs(Node start) {
        visited =new  boolean[10000];
        Queue<Node> q = new LinkedList<>();
        q.add(start);
        visited[start.register] = true;
        while(true) {
            Node tempNode = q.poll();
            int temp = tempNode.register;
            if(temp==end) {
                System.out.println(tempNode.s);
                return;
            }
            for(char c:operator) {
                int next;
                if(c=='D'){
                    next = temp*2 % 10000;
                }
                else if(c=='S'){
                    next = (temp == 0) ? 9999 : temp - 1;
                }
                else if(c=='L'){
                    next = (temp % 1000) * 10 + temp/1000;

                }
                else {
                    next = (temp %10)*1000 + temp/10;
                }
                if (!visited[next]) {
                    visited[next] = true;
                    q.add(new Node(next, tempNode.s + c));
                }
            }
        }
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


class Node{
    int num;
    int count;
    public Node(int num, int count){
        this.num = num;
        this.count = count;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int answer = bfs(A, K);
        System.out.println(answer);

    }
    public static int bfs(int start,int end) {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(start,0));
        boolean[] visited = new boolean[end+1];
        visited[start] = true;
        while(!q.isEmpty()) {
            Node temp = q.poll();
            if(temp.num==end) {
                return temp.count;
            }
            if(temp.num+1>=1 && temp.num+1<=end && !visited[temp.num+1]){
                q.add(new Node(temp.num+1, temp.count+1));
                visited[temp.num+1] = true;
            }
            if(temp.num*2>=1 && temp.num*2<=end && !visited[temp.num*2]) {
                q.add(new Node(temp.num*2, temp.count+1));
                visited[temp.num*2] = true;
            }
        }
        return -1;
    }
}


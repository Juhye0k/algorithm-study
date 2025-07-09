import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node{
    int index;
    int time;
    public Node(int index, int time){
        this.index=index;
        this.time=time;
    }
}

public class Main{

    static int visited[];
    static int dx[]={-1,1};
    static Queue<Node> queue;
    static int N,K;
    static int answer=Integer.MAX_VALUE;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        // 수빈이가 있는 위치 N
        N = Integer.parseInt(st.nextToken());
        // 동생이 있는 위치 K
        K = Integer.parseInt(st.nextToken());
        visited = new int[100001];
        for(int i=0; i<visited.length; i++)
            visited[i]=-1;
        queue = new LinkedList<>();
        bfs(N);
        System.out.println(answer);
    }
    public static int bfs(int start){
        visited[start]=1;
        queue.add(new Node(start, 0));
        while(!queue.isEmpty()){
            Node temp = queue.poll();
            if(temp.index == K){
                answer = Math.min(answer, temp.time);
            }
            int teleport = 2*temp.index;
            if(teleport>= 0 && teleport<=100000 && visited[teleport]== -1){
                visited[2*temp.index]=1;
                queue.add(new Node(2*temp.index,temp.time));
            }
            for(int i:dx){
                int next = temp.index+i;
                if(next>=0 && next<=100000 && visited[next]==-1){
                    visited[next]=1;
                    queue.add(new Node(next, temp.time+1));
                }
            }
            
        }
        return -1;
    }

}

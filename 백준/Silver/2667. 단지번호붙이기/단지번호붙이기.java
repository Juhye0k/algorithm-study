import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static Queue<Node> q;
    static List<List<Node>> graph;
    static boolean visited[][];
    static int N;
    static int count;
    static int[] dx={-1,1,0,0};
    static int[] dy={0,0,-1,1};
    public static void main(String[] args)  throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        graph = new ArrayList<>();
        visited=new boolean[N][N];
        for (int i = 0; i < N; i++)
            graph.add(new ArrayList<>());
        q=new LinkedList<>();
        // 수 입력

        for (int i = 0; i < N; i++) {
            String line=br.readLine();
            for (int j = 0; j < N; j++) {
                int x = line.charAt(j)-'0';
                Node node = new Node(i, x);
                graph.get(i).add(node);
            }
        }
        List<Integer> complexSizes = new ArrayList<>();
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(graph.get(i).get(j).y==1 && !visited[i][j]){
                    int size=BFS(i,j);
                    complexSizes.add(size);
                }
            }
        }
        Collections.sort(complexSizes);
        System.out.println(complexSizes.size());
        for(int size:complexSizes){
            System.out.println(size);
        }
    }
    static int BFS(int startX,int startY){
        q.add(new Node(startX,startY));
        visited[startX][startY] = true;
        int houseCount=1;
        while(!q.isEmpty()){
            Node node = q.poll();
            int x=node.x;
            int y=node.y;
            for(int i=0;i<4;i++){
                int nx=x+dx[i];
                int ny=y+dy[i];
                if(nx>=0 &&ny>=0 && nx<N && ny<N){
                    if(!visited[nx][ny]&&graph.get(nx).get(ny).y==1){
                        q.offer(new Node(nx,ny));
                        visited[nx][ny]=true;
                        houseCount++;
                    }
                }
            }
        }
        return houseCount;
    }

}

class Node{
    int x;
    int y;

    public Node(int x, int y) {
        this.x=x;
        this.y=y;
    }
}

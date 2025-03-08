import java.io.*;
import java.util.*;

class Node{
    int x;
    int count;
    public Node(int x,int count){
        this.x=x;
        this.count=count;
    }
}
public class Main {
    static Queue<Node> queue;
    static boolean visited[];
    static Set<Integer> ladder;
    static Set<Integer> snake;
    static Map<Integer, Integer> ladderMap;
    static Map<Integer, Integer> snakeMap;
    static int result;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st=new StringTokenizer(br.readLine());
        // 게임판에 있는 사다리의 수 N
        int N=Integer.parseInt(st.nextToken());
        // 뱀의 수 M
        int M=Integer.parseInt(st.nextToken());
        result=10000;
        queue=new LinkedList<>();
        snake=new HashSet<>();
        ladder=new HashSet<>();
        visited=new boolean[101];
        ladderMap =new HashMap<>();
        snakeMap=new HashMap<>();
        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());
            int x=Integer.parseInt(st.nextToken());
            int y=Integer.parseInt(st.nextToken());
            // 어느 자료구조에 저장할 것인가?
            ladder.add(x);
            ladderMap.put(x,y);
        }
        for(int i=0;i<M;i++){
            st=new StringTokenizer(br.readLine());
            int u=Integer.parseInt(st.nextToken());
            int v=Integer.parseInt(st.nextToken());
            snake.add(u);
            snakeMap.put(u,v);
        }
        // 주사위를 얼만큼 나오게 할 것인가?
        // 어느 경로가 가장 최소가 되게 할 것인가? --> BFS를 돌리면서, 횟수가 가장 작은 걸 COUNT하면 되지 않을까?
        // 뱀을 만나면 안된다
        bfs(new Node(1,0));
    }
    public static void bfs(Node node){
        queue.add(node);
        visited[1]=true;
        while(!queue.isEmpty()){
            Node temp=queue.poll();
            for(int i=1;i<=6;i++){
                int next=temp.x+i;
                // 100을 넘어가면 이미 성공한거임
                if(next==100){
                    System.out.println(temp.count+1);
                    return;
                }
                if(next>100){
                    continue;
                }
                if(snake.contains(next))  {
                    next=snakeMap.get(next);
                }

                if(ladder.contains(next)){  // 사다리를 만나면 이동하기
                    next= ladderMap.get(next);
                }
                if(!visited[next]){
                    visited[next]=true;
                    queue.add(new Node(next,temp.count+1));
                }
            }
        }
    }
}
/*
이 문제를 통해 배운 점
bfs는 레벨별로 탐색을 한다. 따라서 바로 return을 해도 count는 최소가 보장이 된다. 따로 result 배열을 선언해서
min처리하지 않아도 된다.
*/

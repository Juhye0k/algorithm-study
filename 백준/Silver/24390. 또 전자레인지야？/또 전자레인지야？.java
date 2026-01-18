import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node {
    int time;
    int count;
    boolean isActive;
    public Node(int time, int count, boolean isActive) {
        this.time = time;
        this.count = count;
        this.isActive = isActive;
    }
}

public class Main {
    static int cookTime;
    static int[] cook = {10, 60, 600, 30};
    static int result = 0;
    static boolean[] visited = new boolean[1000001];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String [] ar = br.readLine().split(":");
        cookTime = Integer.parseInt(ar[0])*60 + Integer.parseInt(ar[1]);
        bfs(new Node(0,0,false));
    }
    public static void bfs(Node start) {
        Queue<Node> q = new LinkedList<>();
        q.offer(start);
        visited[0] = true;
        while(!q.isEmpty()) {
            Node temp = q.poll();
            // 만약 현재 시간이 원하고자 하는 목표 요리 시간일 때
            if(temp.time == cookTime) {
                // 활성화 안되어있으면 시작 버튼을 한번 더 눌러야 하므로
                int result = temp.isActive ? temp.count : temp.count+1;
                System.out.println(result);
                return;
            }
            int nextTime = 0;
            for(int i=0; i<4; i++) {
                if(i==3) {
                    // 가동중이지 않으면, 시간은 올라가지 않음
                    if(temp.time>0 && !temp.isActive) {
                        nextTime = temp.time;
                    }
                    // 가동중이면, 시간이 올라감
                    else {
                        nextTime = temp.time + cook[i];
                    }
                    temp.isActive = true;
                }
                else {
                    nextTime = temp.time + cook[i];
                }
                if(nextTime <= cookTime && !visited[nextTime]) {
                    q.offer(new Node(nextTime, temp.count+1, temp.isActive));
                    visited[nextTime] = true;
                }
            }
        }
    }

}

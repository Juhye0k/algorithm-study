import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


class Node {
    int x, y;
    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {

    static Queue<Node> q;
    static boolean[][] visited;
    static int sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        sum = A + B + C;
        q = new LinkedList<>();
        visited = new boolean[1501][1501];

        int answer = bfs(new Node(A, B));
        if(answer == 0) System.out.println(0);
        else System.out.println(answer);
        // 1. 두 개의 그룹을 어떻게 선택을 할까?
        // 2. 같다는 판별을 어떻게 하지?
        // 3. 언제까지 반복을 해서 돌려야하지? -> a=b이고, 3a=sum일때
    }
    public static boolean check(int i, int j){
        if(i>=1 && i<=1500 && j>=1 && j<=1500)
            return true;
        else
            return false;
    }

    public static int bfs(Node node) {
        int a = node.x;
        int b = node.y;
        visited[a][b] = true;
        q.add(node);
        while (!q.isEmpty()) {
            Node temp = q.poll();
            // 현재 뽑은 상태
            int[] now = {temp.x, temp.y, sum - temp.x - temp.y};
            // 현재 상태가 모두 같다 -> 가능! 1출력하고 종료하기
            if (now[0] == now[1] && now[0] == now[2]) {
                return 1;
            }
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (i == j) continue;
                    // 현재 상태를 한번도 방문하지 않음
                    int x = now[i];
                    int y = now[j];
                    if(x<y){
                        int m = x+x;
                        int n = y-x;
                        if(check(m,n) && !visited[m][n]){
                            visited[m][n] = true;
                            q.add(new Node(m, n));
                        }
                    }
                }
            }
        }
        return 0;
    }
}

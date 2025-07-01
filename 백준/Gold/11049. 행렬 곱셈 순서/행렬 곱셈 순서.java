import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


class Node{
    int x;
    int y;
    public Node(int x, int y){
        this.x = x;
        this.y = y;
    }
}

public class Main {

    static long ar[][];
    static List<Node> list;
    static int N;
    // 묶음 단위
    public static long solve(int left, int right){
        if(left == right)
            return 0;
        // mid 값을 1부터 right-1까지 조절하면서 분할점 지정하기
        if(ar[left][right] != -1)
            return ar[left][right];
        ar[left][right] = Long.MAX_VALUE;
        for(int mid = left; mid< right; mid++){
            ar[left][right] = Math.min(ar[left][right],solve(left,mid)+solve(mid+1,right)+ (long) list.get(left-1).x *list.get(mid-1).y*list.get(right-1).y);
        }
        return ar[left][right];

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 행렬의 개수 N
        N = Integer.parseInt(br.readLine());
        ar = new long[N+1][N+1];
        list = new ArrayList<>();
        // left, right 설정하기
        for(int i = 1; i <= N; i++){
            // 행렬의 크기 r과 c
            st = new StringTokenizer(br.readLine());

            // 행렬 정보 저장하기
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            list.add(new Node(r,c));
        }
        for(int i = 1; i<= N; i++)
            for(int j = 1; j<= N; j++)
                ar[i][j] = -1;
        System.out.println(solve(1,N));
    }
}
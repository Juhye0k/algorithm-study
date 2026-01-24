import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static boolean[] visited;
    static int N,S;
    static int ar[];
    static int count;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 정수의 개수 N
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        visited = new boolean[N];
        st = new StringTokenizer(br.readLine());
        count = 0;
        ar = new int[N];
        for(int i=0; i<N; i++) {
            ar[i] = Integer.parseInt(st.nextToken());
        }
        dfs(0,0);
        if(S==0)
            count--;
        System.out.println(count);
    }
    public static void dfs(int start, int sum) {

        if(sum==S) {
            count++;
        }

        for(int i=start; i<N; i++) {
            if(!visited[i]) {
                visited[i] = true;
                dfs(i+1, sum+ar[i]);
                visited[i] = false;
            }
        }
    }
}
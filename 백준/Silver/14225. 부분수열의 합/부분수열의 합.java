import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;


public class Main {

    static int N;
    static int ar[];
    static boolean[] visited;
    static TreeMap<Integer, Integer> map = new TreeMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        visited = new boolean[2000010];
        ar = new int[N];
        for(int i=0; i<N; i++) {
            ar[i] = Integer.parseInt(st.nextToken());
        }
        dfs(0,0);
        int answer = 1;
        while(true) {
            if(!visited[answer]) {
                System.out.println(answer);
                break;
            }
            answer++;
        }

    }
    public static void dfs(int start,int sum) {

        visited[sum] = true;
        for(int i = start; i< N; i++) {
            dfs(i+1, sum+ar[i]);
        }
    }


}

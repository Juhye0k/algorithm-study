import java.util.*;
import java.io.*;
public class Main {
    static int N;
    static List<Integer> list = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();
    static boolean[] visited;
    public static void main(String[] args) throws IOException{
        // Please write your code here.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        visited = new boolean[N+1];
        choice(0);
        System.out.println(sb);
    }
    public static void choice(int curNum) {
        if(curNum>=N) {
            printAnswer();
            return;
        }
        for(int i=N; i>0; i--) {
            if(visited[i]) continue;
            visited[i] = true;
            list.add(i);
            choice(curNum+1);
            visited[i] = false;
            list.remove(list.size()-1);
        }
    }
    public static void printAnswer() {
        int size = list.size();
        for(int i=0; i<size; i++) {
            sb.append(list.get(i)+" ");
        }
        sb.append("\n");
    }
}
import java.util.Scanner;
import java.util.*;
public class Main {
    static List<Integer> list = new ArrayList<>();
    static int n;
    static StringBuilder sb = new StringBuilder();
    static boolean[] visited;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        visited = new boolean[n+1];
        // Please write your code here.
        choice(0);
        System.out.println(sb);
    }
    public static void choice(int curNum) {
        if(curNum >=n) {
            printAnswer();
            return;
        }
        for(int i=1; i<=n; i++) {
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


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {
    int num;
    int count;
    public Node(int num, int count) {
        this.num = num;
        this.count = count;
    }
}


public class Main {

    static boolean [] ar;
    static boolean[] visited;
    static int[] dx = {1,10,100,1000};
    static int after;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        ar = new boolean[10000];
        Arrays.fill(ar, true);

        isPrime(10000);
        for(int i=0; i<T; i++) {
            visited = new boolean[10000];
            st = new StringTokenizer(br.readLine());
            int before = Integer.parseInt(st.nextToken());
            after = Integer.parseInt(st.nextToken());
            bfs(new Node(before,0));
        }
        System.out.println(sb);
    }
    public static void isPrime(int n) {

        for(int i=2; i*i<n; i++) {
            if(ar[i]) {
                for(int j=i*i; j<n; j+=i)
                    ar[j] = false ;
            }
        }
    }
    public static void bfs(Node start) {
        visited[start.num] = true;
        Queue<Node> q = new LinkedList<>();
        q.add(start);
        while(!q.isEmpty()) {
            Node temp = q.poll();
            if(temp.num == after) {
                sb.append(temp.count).append("\n");
                return;
            }
            String str = String.valueOf(temp.num);
            char[] chars = str.toCharArray();
            for(int i=0; i<4; i++) {
                char original = chars[i];
                for(int j=0; j<=9; j++) {
                    char next = (char)(j+'0');
                    if(chars[i]==next) continue;
                    if(i==0 && j==0) continue;
                    // 바꾼 값이 소수인지, 맞으면 q에 넣고 방문처리
                    chars[i] = next;
                    int nextNum = Integer.parseInt(new String(chars));
                    if(ar[nextNum] && !visited[nextNum]) {
                        visited[nextNum] = true;
                        q.add(new Node(nextNum, temp.count+1));
                    }
                }
                chars[i] = original;
            }
        }
    }
}
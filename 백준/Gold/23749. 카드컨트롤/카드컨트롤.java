
import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;



public class Main {

    static int matchCount;
    static boolean isCan=false;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        int N =  Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new  StringBuilder();
        for(int i=0; i<2*N; i++){
            sb.append(st.nextToken());
        }
        String s = sb.toString();
        System.out.println(bfs(s,2*N));


    }
    public static boolean check(String s) {
        int index = 0;
        int aCount = 0;
        int bCount = 0;
        for(int i=0; i<s.length(); i+=2){
            char first = s.charAt(i);
            char second = s.charAt(i+1);
            if(first=='O' && second=='X') {
                aCount++;
            }
            else if(first=='X' && second =='O')
                bCount++;
        }

        return aCount > bCount;
    }
    public static int bfs(String start,int totalCards) {
        Queue<Node> queue = new LinkedList<>();
        HashSet<String> visited = new HashSet<>();
        queue.add(new Node(start,0));
        while(!queue.isEmpty()){
            Node current = queue.poll();
            String state = current.value;
            if(check(state)) {
                return current.count;
            }
            for(int i=1;i<totalCards; i++) {
                String nextState = state.charAt(i) + state.substring(0,i) + state.substring(i+1);
                if(!visited.contains(nextState)){
                    visited.add(nextState);
                    queue.add(new Node(nextState,current.count + 1));
                }
            }
        }
        return -1;
    }
}
class Node{
    String value;
    int count;
    public Node(String value, int count) {
        this.value = value;
        this.count = count;
    }
}
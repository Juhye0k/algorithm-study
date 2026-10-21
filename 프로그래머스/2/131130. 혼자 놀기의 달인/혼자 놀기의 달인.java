import java.util.*;
class Solution {
    static boolean[] visited;
    static int size;
    public int solution(int[] cards) {
        int answer = 0;
        
        size = cards.length;
        visited = new boolean[size+1];
        List<Integer> list = new LinkedList<>();
        for(int i=1; i<=size; i++) {
            if(!visited[i]) {
                int result = bfs(i,cards);
                list.add(result);
            }
        }
        if(list.size()<2) return 0;
        Collections.sort(list,Collections.reverseOrder());
        
        return list.get(0)*list.get(1);
    }
    public static int bfs(int start, int[] cards) {
        int a = 0;
        Queue<Integer> q = new ArrayDeque<>();
        q.add(start);
        visited[start] = true;
        while(!q.isEmpty()) {
            int temp = q.poll();
            a++;
            int next = cards[temp-1];
            
            if(!visited[next]) {
                visited[next] = true;
                q.add(next);
            }
        }
        return a;
    }
    public static boolean check(){
        for(int i=1; i<=size; i++) {
            if(!visited[i]) return false;
        }
        return true;
    }
}
import java.util.*;

class Solution {
    static boolean[] visited;
   
    static List<List<Integer>> graph;
    public int solution(int n, int[][] computers) {
        int answer = 0;
        visited = new boolean[n+1];

        graph = new ArrayList<>();
        for(int i=0; i<=n; i++) {
            graph.add(new ArrayList<>());
        }
        for(int i=1; i<=n; i++) {
            for(int j=1; j<=n; j++) {
                if(i==j) continue;
                if(computers[i-1][j-1]!=1) continue;
                graph.get(i).add(j);
                graph.get(j).add(i);
            }
        }
        for(int i=1; i<=n; i++) {
            if(!visited[i]) {
                bfs(i);
                answer++;
            }
        }
        
        return answer;
    }
    public static void bfs(int n) {
        Queue<Integer> q = new LinkedList<>();
        visited[n] = true;
        q.add(n);
        while(!q.isEmpty()) {
            int temp = q.poll();
            for(int number : graph.get(temp)) {
                if(!visited[number]) {
                    q.add(number);
                    visited[number] = true;
                }
            }
        }
    }
}
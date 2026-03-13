import java.util.*;
class Solution {
    static List<List<Integer>> graph;
    static boolean[] visited;
    public int solution(int n, int[][] computers) {
        int answer = 0;
        graph = new LinkedList<>();
        for(int i=0;  i<n; i++)
            graph.add(new LinkedList<>());
        visited = new boolean[n];
        for(int i=0; i<computers.length; i++) {
            for(int j=0; j<computers[i].length; j++) {
                if(i==j) continue;
                if(computers[i][j]==1){
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }
            }
        }
        for(int i=0; i<n; i++){
            if(!visited[i]) {
                bfs(i);
                answer++;
            }
        
        }
        return answer;
    }
    public static void bfs(int start) {
        visited[start] = true;
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        while(!q.isEmpty()) {
            int temp = q.poll();
            for(int num:graph.get(temp)) {
                if(!visited[num]){
                    q.add(num);
                    visited[num] = true;
                }
            }
        }
    }
}
import java.util.*;
class Solution {
    static List<List<Integer>> graph;
    public int solution(int n, int[][] wires) {
        int answer = n;
        graph = new LinkedList<>();
        for(int i=0; i<=n; i++) {
            graph.add(new ArrayList<>());
        }
        for(int i=0; i<wires.length; i++) {
            int start = wires[i][0];
            int end = wires[i][1];
            graph.get(start).add(end);
            graph.get(end).add(start);
        }
        for(int i=0; i<wires.length; i++) {
            boolean[] visited = new boolean[n+1];
            int start = wires[i][0];
            int end = wires[i][1];
            int count = dfs(start, start,end,visited);
            answer = Math.min(answer, Math.abs(count-(n-count)));
        }
        return answer;
    }
    public static int dfs(int current, int a, int b, boolean[] visited) {
        int value = 1;
        visited[current] = true;
        for(int cur : graph.get(current)) {
            if(current == a && cur == b || current == b && cur==a) continue;
            if(!visited[cur]) {
                value += dfs(cur, a,b, visited);
            }
        }
        return value;
    }
    
}
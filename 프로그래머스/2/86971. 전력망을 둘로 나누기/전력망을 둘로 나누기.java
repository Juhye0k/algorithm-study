import java.util.*;
class Solution {
    static List<List<Integer>> graph;
    public int solution(int n, int[][] wires) {
        int answer = 1000000;
        graph = new LinkedList<>();
        for(int i=0; i<=n; i++) {
            graph.add(new LinkedList<>());
        }
        for(int[] num:wires) {
            graph.get(num[0]).add(num[1]);
            graph.get(num[1]).add(num[0]);
        }
        for(int[] wire: wires) {
            int v1 = wire[0];
            int v2 = wire[1];
            
            graph.get(v1).remove(Integer.valueOf(v2));
            graph.get(v2).remove(Integer.valueOf(v1));
            int cnt = bfs(v1,n);
            int diff = Math.abs(cnt-(n-cnt));
            answer = Math.min(answer,diff);
            graph.get(v1).add(v2);
            graph.get(v2).add(v1);
        }
        return answer;
    }
    public static int bfs(int start,int n) {
        int count = 1;
        Queue<Integer> q= new LinkedList<>();
        boolean[] visited = new boolean[n+1];
        visited[start] = true;
        q.add(start);
        while(!q.isEmpty()) {
            int temp = q.poll();
            for(int num:graph.get(temp)) {
                     if(!visited[num]) {
                    visited[num] = true;
                    q.add(num);
                    count++;
                
                }
               
            }
        }
        return count;
    }
}
import java.util.*;
class Solution {
    static List<List<Integer>> graph;
    static int n;
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length];
        graph = new ArrayList<>();
        this.n = n;
        for(int i=0; i<=n; i++) 
            graph.add(new ArrayList<>());
        for(int i=0; i<roads.length; i++) {
            int a = roads[i][0];
            int b = roads[i][1];
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        for(int i=0; i< sources.length; i++) {
            int start = sources[i];
            int end = destination;
            int result = bfs(start,end);
            answer[i] = result;
        }
        
        return answer;
    }
    public static int bfs(int start, int end) {
        boolean[] visited = new boolean[n+1];
        visited[start] = true;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{start,0});
        while(!q.isEmpty()) {
            int[] temp = q.poll();
            int a = temp[0];
            int time = temp[1];
            if(a==end) {
                return time;
            }
            for(int num : graph.get(a)) {
                if(!visited[num]) {
                    q.add(new int[]{num,time+1});
                    visited[num] = true;
                }
            }
        }
        return -1;
    }
}
import java.util.*;

class Solution {
    static List<List<Integer>> graph;
    public boolean solution(int n, int[][] path, int[][] order) {
        boolean answer = true;
        /*
        모든 방을 적어도 한 번은 방문
        */
        graph = new ArrayList<>();
        for(int i=0; i<=n; i++) {
            graph.add(new ArrayList<>());
        }
        for(int i=0; i<path.length; i++) {
            int start = path[i][0];
            int end = path[i][1];
            graph.get(start).add(end);
            graph.get(end).add(start);
        }
        Map<Integer,Integer> map = new HashMap<>();
        for(int i=0; i<order.length; i++) {
            map.put(order[i][1],order[i][0]);
        }
        if(map.containsKey(0)) {
            return false;
        }
        int[] waiting = new int[n];
        Arrays.fill(waiting,-1);
        
        return bfs(0,n,map,waiting);
    }
    public static boolean bfs(int start, int n, Map<Integer,Integer> map, int[] wating) {
        boolean[] visited = new boolean[n+1];
        Queue<Integer> q = new LinkedList<>();
        
        visited[start] = true;
        q.add(start);
        int count = 1;
        
        while(!q.isEmpty()) {
            int temp = q.poll();
            
            int unlocked = wating[temp];
            
            if(unlocked != -1 && !visited[unlocked]) {
                visited[unlocked] = true;
                q.add(unlocked);
                count++;
            }
            
            for(int num : graph.get(temp)) {
                if(visited[num]) continue;
                
                int before = map.getOrDefault(num,-1);
                if(before!=-1 && !visited[before]) {
                    wating[before] = num;
                    continue;
                }
                visited[num] = true;
                count++;
                q.add(num);
            }
        }
        return count == n;
    }
}
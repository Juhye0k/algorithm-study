import java.util.*;

class Edge {
    int type;
    int num;
      Edge(int num, int type) {
        this.num = num;
        this.type = type;
    }
}

class Solution {
    static List<List<Edge>> graph;
    static int answer;
    static int nodeCount;
    static int limit;
    public int solution(int n, int infection, int[][] edges, int k) {
        answer = 0;
        nodeCount = n;
        limit = k;
        
        graph = new ArrayList<>();
        for(int i=0; i<=n; i++) 
            graph.add(new ArrayList<>());
        
        for(int i=0; i<edges.length; i++) {
            int x = edges[i][0];
            int y = edges[i][1];
            int type = edges[i][2];
            graph.get(x).add(new Edge(y, type));
            graph.get(y).add(new Edge(x, type));
        }
        boolean[] infected = new  boolean[n+1];
        infected[infection] = true;
        dfs(0, 0, infected);
        
        
        return answer;
    }
    public static void dfs(int depth, int lastType, boolean[] infected) {
        
        int count = 0;
        
        for (int i=1; i<=nodeCount; i++) {
            if(infected[i]) count++;
        }
        
        answer = Math.max(answer, count);
        
        if (depth == limit || answer == nodeCount) {
    return;
}
        for(int i=1; i<=3; i++) {
            
            if(i == lastType) continue;
            
            boolean[] next = infected.clone();
            spread(i, next);
            dfs(depth+1,i, next);
            
        }

    }
    static void spread(int type, boolean[] infected) {
        
        Queue<Integer> queue = new ArrayDeque<>();
        
        for(int i=1; i<= nodeCount; i++) {
            if(infected[i]) {
                queue.offer(i);
            }
        }
        
        while(!queue.isEmpty()) {
            int current = queue.poll();
            for(Edge edge : graph.get(current)) {
                if(edge.type != type || infected[edge.num]) continue;
                
                infected[edge.num] = true;
                queue.offer(edge.num);
            }
        }
    }
}
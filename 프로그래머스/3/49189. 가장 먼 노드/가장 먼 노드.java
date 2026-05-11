import java.util.*;
class Node {
    int num;
    int value;
    public Node(int num, int value) {
        this.num=num;
        this.value=value;
    }
}

class Solution {
    static List<List<Integer>> graph;
    public int solution(int n, int[][] edge) {
        int answer = 0;
        graph = new LinkedList<>();
        for(int i=0; i<=n; i++) {
            graph.add(new LinkedList<>());
        }
        for(int i=0; i<edge.length; i++) {
            int n1 = edge[i][0];
            int n2 = edge[i][1];
            graph.get(n1).add(n2);
            graph.get(n2).add(n1);
        }
        int max = 0;
        int[] dist = bfs(n);
        for(int i=2; i<dist.length; i++) {
            if(max<dist[i]) {
                answer=1;
                max=dist[i];
            }
            else if(max==dist[i]) {
                answer++;
            }
        }
        
        return answer;
    }
    public static int[] bfs(int n) {
        int[] dist = new int[n+1];
        Arrays.fill(dist,Integer.MAX_VALUE);
        Queue<Node> q = new LinkedList<>();
        boolean[] visited = new boolean[n+1];
        q.add(new Node(1,0));
        visited[1] = true;
        while(!q.isEmpty()) {
            Node temp = q.poll();
            for(int i:graph.get(temp.num)) {
                if(!visited[i]) {
                    q.add(new Node(i,temp.value+1));
                    dist[i]=temp.value+1;
                    visited[i] = true;
                }
            }
        }
        return dist;
    }
}
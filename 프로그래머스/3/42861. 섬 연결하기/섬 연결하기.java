import java.util.*;
class Edge implements Comparable<Edge>{
    int start;
    int end;
    int value;
    public Edge(int start, int end, int value) {
        this.start = start;
        this.end = end;
        this.value = value;
    }
    @Override
    public int compareTo(Edge o) {
            return this.value-o.value;
    }
}

class Solution {
    static int[] parent;
    public int solution(int n, int[][] costs) {
        int answer = 0;
        List<Edge> list = new LinkedList<>();
        for(int i=0; i<costs.length; i++) {
            list.add(new Edge(costs[i][0],costs[i][1],costs[i][2]));
        }
        Collections.sort(list);
        parent = new int[n];
        for(int i=0; i<n; i++) {
            parent[i] = i;
        }
        for(int i=0; i<costs.length; i++) {
            if(find(list.get(i).start) != find(list.get(i).end)) {
                union(list.get(i).start,list.get(i).end);
                answer+=list.get(i).value;
            }
        }
        
        return answer;
    }
    private static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if(a>b) {
            parent[a] = b;
        }
        else {
            parent[b] = a;
        }
    }
    private static int find(int x) {
        if(parent[x] == x) 
            return x;
        else
            return find(parent[x]);
    }
}
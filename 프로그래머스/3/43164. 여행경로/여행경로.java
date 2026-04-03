import java.util.*;
class Solution {
    static  Map<String,PriorityQueue<String>> map;
    static List<String> route = new ArrayList<>();
    public String[] solution(String[][] tickets) {
        String[] answer = {};
        map = new HashMap<>();
        for(String[] ticket:tickets) {
            map.putIfAbsent(ticket[0],new PriorityQueue<>());
            map.get(ticket[0]).offer(ticket[1]);
        }
        dfs("ICN");
        Collections.reverse(route);
        return route.toArray(new String[0]);
    }
    public static void dfs(String now) {
        PriorityQueue<String> pq = map.get(now);
        while(pq!=null && !pq.isEmpty()) {
            String next = pq.poll();
            dfs(next);
        }
        route.add(now);
    }
    
}
import java.util.*;
class Solution {
    static Map<String, List<String>> map;
    static List<String> answer;
    static Map<String, boolean[]> used;
    static int ticketCount;


    public List<String> solution(String[][] tickets) {
        answer = new LinkedList<>();
        map = new HashMap<>();
        used = new HashMap<>();
        ticketCount = tickets.length;

        for(int i=0; i< tickets.length; i++) {
            String from = tickets[i][0];
            String to = tickets[i][1];
            
            if(!map.containsKey(from)) {
                map.put(from, new ArrayList<>());
            }
            map.get(from).add(to);
        }
        for(List<String> destinations : map.values()) {
            Collections.sort(destinations);
        }
        for (String from : map.keySet()) {
    used.put(from, new boolean[map.get(from).size()]);
}
        String start = "ICN";
        
        answer.add(start);
        dfs(start,0);
        return answer;
    }
    public static boolean dfs(String start, int count) {
        if (count == ticketCount) {
            return true;
        }
        if (!map.containsKey(start)) {
              return false;
        }
        List<String> destinations = map.get(start);
        boolean[] visited = used.get(start);
        
        for(int i=0; i<destinations.size(); i++) {
            if(visited[i]) {
                continue;
            }
            
            String destination = destinations.get(i);
            
            visited[i] = true;
            answer.add(destination);
            if(dfs(destination, count+1)) {
                return true;
            }
            visited[i] = false;
            answer.remove(answer.size()-1);
        }
        return false;
    }
}
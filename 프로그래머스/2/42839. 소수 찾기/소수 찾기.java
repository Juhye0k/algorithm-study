import java.util.*;
class Solution {
    static boolean[] used;
    static Set<Integer> candidates;
    public int solution(String numbers) {
        int answer = 0;
        used = new boolean[numbers.length()];
        candidates = new HashSet<>();
        dfs(numbers,0);
        
        for(int num : candidates) {
            if(solve(num)) {
                answer++;
            }
        }
        return answer;
    }
    public static void dfs(String numbers, int value) {
        
        for(int i=0; i<numbers.length(); i++) {
            if(used[i]) continue;
            
            used[i] = true;
            int nextValue = value * 10 + (numbers.charAt(i)-'0');
            candidates.add(nextValue);
            dfs(numbers, nextValue);
            used[i] = false;
        }
    }
    public static boolean solve(int num) {
        if(num<2) return false;
        boolean[] visited = new boolean[num+1];
        
        for(int i=2; i*i<= num; i++) {
            if(visited[i]) continue;
            for(int j=i*i; j<=num; j+=i) {
                visited[j] = true;
            }
        }
    
        return !visited[num];
    }
}
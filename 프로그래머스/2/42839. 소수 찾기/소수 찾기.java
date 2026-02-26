import java.util.*;
class Solution {
    static Set<Integer> set;
    static boolean[] visited;
    static char[] str;
    public int solution(String numbers) {
        int answer = 0;
        boolean[] isPrime = tenes(7000001);

        str = new char[numbers.length()];
        visited = new boolean[numbers.length()];
        for(int i=0; i<numbers.length(); i++) {
            str[i] = numbers.charAt(i);
        }
        set = new HashSet<>();
        dfs(0);
        for(int num:set) {
            if(isPrime[num])
                answer++;
        }
        
        return answer;
    }
    public static void dfs(int num) {
        set.add(num);
        for(int i=0; i<str.length; i++) {
            if(!visited[i]){
                visited[i] = true;
                dfs(num*10+(str[i]-'0'));
                visited[i] = false;
            }
        }
    }
    
    public static boolean[] tenes(int n) {
        boolean[] isPrime = new boolean[n+1];
        Arrays.fill(isPrime,true);
        isPrime[0] = isPrime[1] = false;
        for(int i=2; i*i<=n; i++) {
            if(isPrime[i]) {
                for(int j=i*i; j<=n; j+=i ) {
                    isPrime[j]=false;
                }
            }
        }
        return isPrime;
    }
}
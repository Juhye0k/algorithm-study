import java.util.*;
class Solution {
    static Set<String> set;
    static int answer;
    public int solution(String begin, String target, String[] words) {
        answer = Integer.MAX_VALUE;;
        set = new HashSet<>();
        dfs(begin, target, words, 0);
        if(answer == Integer.MAX_VALUE) answer = 0;
        return answer;
    }
    public static int dfs(String start, String target, String[] words, int value) {
        if(start.equals(target)) {
            answer = Math.min(answer, value);
        }
        for(int i=0; i<words.length; i++) {
            if(!set.contains(words[i]) && canMove(start,words[i])) {
                set.add(words[i]);
                dfs(words[i], target, words, value+1);
                set.remove(words[i]);
            }
        }
        return answer;
    }
    public static boolean canMove(String w1, String w2) {
        int count = 0;
        for(int i=0; i<w1.length(); i++) {
            String s1 = w1.substring(i,i+1);
            String s2 = w2.substring(i,i+1);
            if(!s1.equals(s2)) count++;
        }
        return count==1;
    }
}
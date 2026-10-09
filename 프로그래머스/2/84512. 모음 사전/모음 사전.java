import java.util.*;
class Solution {
    static List<String> list;
    public int solution(String word) {
        int answer = 0;
        list = new ArrayList<>();
        dfs("");
        for(int i=0; i<list.size(); i++) {
            if(word.equals(list.get(i))) {
                answer = i+1;
            }
        }
        return answer;
    }
    void dfs(String current) {
        if(!current.isEmpty()) {
            list.add(current);
        }
        if(current.length()==5) {
            return;
        }
        for(String vowel : new String[] {"A","E","I","O","U"}) {
            dfs(current+vowel);
        }
    }
}
import java.util.*;
class Solution {
    public int solution(String[][] clothes) {
        int answer = 0;
        Map<String,Integer> map = new HashMap<>();
        for(int i=0; i<clothes.length; i++) {
            String[] str = clothes[i];
            map.put(str[1],map.getOrDefault(str[1],0)+1);
        }
        answer=1;
        for(int count:map.values()) {
            answer*=(count+1);
        }
        return answer-1;
    }
}
import java.util.*;

class Solution {
    public String[] solution(String[] players, String[] callings) {
        String[] answer = {};
        Map<String,Integer> map = new HashMap<>();
        
        for(int i=0; i<players.length; i++) {
            map.put(players[i],i);
        }
        
        for(int i=0; i<callings.length; i++) {
            
            // kai
            String a = callings[i];
            //4
            int num1 = map.get(a);
            
            // poe
            String temp = players[num1-1];
            players[num1-1] = a;
            players[num1] = temp;
            
            map.put(a,num1-1);
            map.put(temp,num1);
        }
        
        return players;
    }
}
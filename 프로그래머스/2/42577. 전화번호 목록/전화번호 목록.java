import java.util.*;
class Solution {
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        
        Set<String> set = new HashSet<>();
        for(int i=0; i<phone_book.length; i++) {
            set.add(phone_book[i]);
        }
        for(String s:phone_book) {
            for(int i=0; i<s.length()-1; i++) {
                String a = s.substring(0,i+1);
                if(set.contains(a)) answer = false;
            }
        }
        return answer;
    }
}
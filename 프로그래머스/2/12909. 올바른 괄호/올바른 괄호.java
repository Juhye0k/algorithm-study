import java.util.*;
class Solution {
    boolean solution(String s) {
        boolean answer = true;
        Stack<String> stack = new Stack<>();
        for(int i=0; i<s.length(); i++) {
            String str = s.substring(i,i+1);
            if(str.equals("(")) {
                stack.push(str);
            }
            else {
                if(!stack.isEmpty() && stack.peek().equals("(")) {
                    stack.pop();
                }
                else {
                    answer=false;
                    return answer;
                }
            }
        }
        if(!stack.isEmpty()) {
            answer=false;
        }

        return answer;
    }
}
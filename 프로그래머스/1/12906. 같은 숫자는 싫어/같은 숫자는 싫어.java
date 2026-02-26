import java.util.*;

public class Solution {
    public List<Integer> solution(int []arr) {
        List<Integer> answer = new LinkedList<>();
        Stack<Integer> stack = new Stack<>();
        for(int i=0; i<arr.length; i++) {
            if(!stack.isEmpty() && stack.peek()==arr[i]) continue;
            stack.add(arr[i]);
            answer.add(arr[i]);
        }
        return answer;
    }
}
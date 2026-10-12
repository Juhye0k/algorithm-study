import java.util.Stack;
class Solution {
    public int[] solution(int[] prices) {
        int n = prices.length;
        int[] answer = new int[prices.length];
        Stack<Integer> stack = new Stack<>();
        
        for(int i=0; i<n; i++) {
            while(!stack.isEmpty() && prices[stack.peek()]>prices[i]) {
                int previous = stack.pop();
                answer[previous] = i-previous;
            }
            stack.push(i);
        }
        while(!stack.isEmpty()) {
            int previous = stack.pop();
            answer[previous] = (n-1) - previous;
        }
        return answer;
    }
}
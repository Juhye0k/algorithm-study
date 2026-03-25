import java.util.*;
class Solution {
    public String solution(String number, int k) {
        String answer = "";
        
        Stack<Integer> stack = new Stack<>();
        int count = 0;
        int[] ar = new int[number.length()];
        for(int i=0; i<number.length(); i++) {
            ar[i] = Integer.parseInt(number.substring(i,i+1));
        }
        int index = 0;
        stack.push(ar[index++]);
        for(int i=1; i<number.length(); i++) {
            int num = ar[index++];
            while(!stack.isEmpty() && k>count && num>stack.peek()) {
                    stack.pop();
                    count++;
                }
                stack.push(num);
        }
        
        while(count < k) {
            stack.pop();
            count++;
        }
        StringBuilder sb = new StringBuilder();
        for(int i:stack) {
            sb.append(i);
        }
        
        return sb.toString();
    }
}
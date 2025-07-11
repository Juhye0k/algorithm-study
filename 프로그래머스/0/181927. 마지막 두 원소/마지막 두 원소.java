import java.util.*;
class Solution {
    public int[] solution(int[] num_list) {
        List<Integer> answer = new LinkedList<>();
        
        // 기존 배열 복사
        for(int num : num_list) {
            answer.add(num);
        }
        
        int num1 = num_list[num_list.length-2]; 
        int num2 = num_list[num_list.length-1]; 
        
        int num3 = num2 > num1 ? num2 - num1 : num2 * 2;
        answer.add(num3);
        
        return answer.stream().mapToInt(i -> i).toArray();
    }
}
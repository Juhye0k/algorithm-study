import java.util.*;
class Solution {
    public List<Integer> solution(int[] progresses, int[] speeds) {
        List<Integer> answer = new ArrayList<>();
        int index = 0;
      
        while(index<progresses.length) {
            int result = 0;
            for(int i=0; i<progresses.length; i++) {
                if(progresses[i]>=100) continue;
                progresses[i]+=speeds[i];
            }
            while(index <progresses.length&&progresses[index]>=100) {
                result+=1;
                index+=1;
            }
            if(result>=1) {
                answer.add(result);
            }
        }
        return answer;
    }
}
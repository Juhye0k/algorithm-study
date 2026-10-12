import java.util.*;
class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        int index = 0;
        for(int i=0; i<commands.length; i++) {
            
            // 시작
            int start = commands[i][0];
            // 끝
            int end = commands[i][1];
            // 몇번째
            int select = commands[i][2];
            
            int[] ar = new int[end-start+1];
            for(int j=start-1; j<end; j++) {
                ar[j-start+1] = array[j];
            }
            Arrays.sort(ar);
            answer[index++] = ar[select-1];
        }
        return answer;
    }
}
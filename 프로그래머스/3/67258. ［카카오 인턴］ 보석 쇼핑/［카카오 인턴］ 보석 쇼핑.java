import java.util.*;
class Solution {
    public int[] solution(String[] gems) {
        int answerLeft = 0;
        int answerRight = 0;
        int left = 0 ;
int answerLength = Integer.MAX_VALUE;        // 특정 범위의 물건을 모두 싹쓸이, 모든 종류의 보석을 적어도 1개 이상 포함하는 짧은 구간
        /*
        왼쪽부터 탐색 -> 길이가 보석 종류와 같으면 그게 답임
        종류가 3개, 길이가 4개 -> 4개인건 이제 안찾아도 됨. 길이를 3으로 줄여서 탐색
        */
        int size = 0;
        Set<String> set = new HashSet<>();
        for(int i=0; i<gems.length; i++) {
            set.add(gems[i]);
        }
        size = set.size();
        Map<String,Integer> map = new HashMap<>();
        for(int right=0; right<gems.length; right++) {
            map.put(gems[right],map.getOrDefault(gems[right],0)+1);
            
            while(map.size() == size) {
                int length = right-left + 1;
                
                if(length < answerLength) {
                    answerLength = length;
                    answerLeft = left;
                    answerRight = right;
                }
                String removed = gems[left];
                int count = map.get(removed)-1;
                if(count == 0) {
                    map.remove(removed);
                } else {
                    map.put(removed, count);
                }
                left++;
            }
        }
        int[] answer = new int[]{answerLeft+1, answerRight+1};
        return answer;
    }
}
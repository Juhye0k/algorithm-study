import java.util.*;
class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        int[] answer = {};
        Map<String,Integer> map = new HashMap<>();
        for(int i=0; i<terms.length; i++) {
            String[] str = terms[i].split(" ");
            map.put(str[0],Integer.parseInt(str[1]));
        }
        List<Integer> result = new ArrayList<>();

        for(int i=0; i<privacies.length; i++) {
            String[] str = privacies[i].split(" ");
            int num = map.get(str[1]);
            // 약관 동의날짜 변환
            int day = toDays(str[0]);
            int  todayDays = toDays(today);
            int expireDays = day + num * 28 -1;
            
            if(expireDays < todayDays) {
                result.add(i+1);
            }
        }
        answer = new int[result.size()];
        for(int i=0;i<result.size(); i++)
            answer[i] = result.get(i);
        return answer;
    }
    private int toDays(String date) {
        String[] arr = date.split("\\.");
        int year = Integer.parseInt(arr[0]);
        int month = Integer.parseInt(arr[1]);
        int day = Integer.parseInt(arr[2]);
        
        return year * 12 * 28 + month*28 + day;
    }
}
import java.util.*;
class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
        
        /* 각 유저는 한 번에 한 명의 유저를 신고
        신고 제한 X 동일 유저 신고 -> 1회 처리
        k번 이상 신고 -> 게시판 이용이 정지, 신고한 모든 유저에게 메일을 발송
        마지막 한꺼번에 정지 메일을 발송
        */
        
        // 유저별 신고당한 횟수
        // 누가 누구를 신고했는지
        Map<String, Integer> map = new HashMap<>();
        Map<String, Set<String>> list = new HashMap<>();
        
        for(int i=0; i<report.length; i++) {
            String str = report[i];
            
            String[] user = str.split(" ");
            String reporter = user[0];
            String bader = user[1];
            
            // 누가 누구를 신고했는지
            Set<String> targets = list.getOrDefault(reporter, new HashSet<>());
            // 처음 신고한 대상이면 true, 중복이면 false
            boolean isNew = targets.add(bader);
            list.put(reporter,targets);
            
            if(isNew) {
                map.put(bader, map.getOrDefault(bader,0)+1);
            }
        }
        for(Map.Entry<String, Integer> entry : map.entrySet()) {
            String userId =entry.getKey();
            int reportCount = entry.getValue();
            if(reportCount>=k) {
                for(int i=0; i<id_list.length; i++) {
                    Set<String> targets = list.get(id_list[i]);
                    if(targets!=null && targets.contains(userId)) answer[i]++;
                }
            }
        }
        return answer;
    }
}
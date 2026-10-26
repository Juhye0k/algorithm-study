import java.util.*;

class Node {
    String subject;
    int startTime;
    int remainTime;
    
    public Node(String subject, int startTime, int remainTime) {
        this.subject = subject;
        this.startTime = startTime;
        this.remainTime = remainTime;
    }
    
}
class Solution {
    public String[] solution(String[][] plans) {
        String[] answer = new String[plans.length];
        int index = 0;
        /*
        과제를 시작하기로 한 시각 -> 시작
        새로운 과제가 들어옴 -> 기존 과제 멈추고 새 과제 시작
        진행 중 과제 끝냈을 때 멈춘 과제가 있음 -> 과제 이어서 진행
        - 과거 과제 vs 새 과제 -> 새 과제
        - 멈춘 과제 여러 개 -> 가장 최근에 멈춘 과제부터
        */
        int time = -1;
        PriorityQueue<Node> wait = new PriorityQueue<>((a,b)->b.startTime-a.startTime);
        
        PriorityQueue<Node> next = new PriorityQueue<>((a,b)-> a.startTime-b.startTime);
        
        for(int i=0; i<plans.length; i++) {
            String subject = plans[i][0];
            int startTime = calculate(plans[i][1]);
            next.add(new Node(subject,startTime, Integer.parseInt(plans[i][2])));            
        }
        Node nowWork=null;
        while(index < plans.length) {
            time++;
            // 현재 작업이 있으면 차감시킨다. 
            if(nowWork!=null) nowWork.remainTime--;
            if(nowWork!=null && nowWork.remainTime<=0) {
                answer[index++] = nowWork.subject;
                nowWork =null;
            }
            // 새로운 작업을 들고 올 수 있으면
            if(!next.isEmpty() && next.peek().startTime <= time) {
                // 이전에 있던 작업이 있을 경우, 대기열에 넣고
                if(nowWork!=null)
                    wait.add(nowWork);
                // 새로운 작업을 들고온다.
                nowWork = next.poll();
            }
            else if(nowWork == null && !wait.isEmpty()){
                nowWork = wait.poll();
            }
        }
        
        
        return answer;
    }
    public static int calculate(String str) {
        String[] s = str.split(":");
        int hour = Integer.parseInt(s[0]) * 60;
        int minute = Integer.parseInt(s[1]);
        return hour + minute;
    }
}
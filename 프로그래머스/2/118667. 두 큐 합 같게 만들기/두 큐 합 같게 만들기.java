import java.util.*;
class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        Queue<Integer> q1 = new ArrayDeque<>();
        Queue<Integer> q2 = new ArrayDeque<>();
        
        long sum1 =0, sum2 =0;
        int subCount1 = 0, subCount2 = 0;
        for(int i=0; i<queue1.length; i++)  {
            sum1+=queue1[i];
            q1.add(queue1[i]);
        }
        for(int i=0; i<queue2.length; i++) {
            sum2+=queue2[i];
            q2.add(queue2[i]);
        }
        long oneSum = sum1;
        long twoSum = sum2;
        while(sum1 != sum2) {
            if(answer!=0 && oneSum==sum1 && twoSum==sum2&&subCount1==queue1.length || subCount2==queue2.length) {
                answer = -1;
                break;
            }
            answer++;
            if(sum1>sum2) {
                // 큐 1에서 원소 빼고 넣기
                int num = q1.poll();
                subCount1++;
                q2.add(num);
                sum1-=num;
                sum2+=num;
                if(sum1==sum2) break;
            }
            else if(sum1<sum2) {
                // 큐 2에서 원소 빼고 넣기
                int num = q2.poll();
                q1.add(num);
                subCount2++;
                sum2-=num;
                sum1+=num;
                if(sum1==sum2) break;
            }
        }
        return answer;
    }
}
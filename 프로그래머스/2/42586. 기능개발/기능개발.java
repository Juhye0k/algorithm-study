import java.util.*;

class Solution {

    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> answer = new ArrayList<>();

        // 첫 번째 기능이 완료되는 날짜
        int releaseDay = getRequiredDays(progresses[0], speeds[0]);

        // 현재 배포에 포함되는 기능 수
        int count = 1;

        for (int i = 1; i < progresses.length; i++) {
            int requiredDays = getRequiredDays(progresses[i], speeds[i]);

            // 앞 기능의 배포일까지 완료된다면 같이 배포
            if (requiredDays <= releaseDay) {
                count++;
            } else {
                // 더 늦게 완료되므로 이전 배포를 확정
                answer.add(count);

                releaseDay = requiredDays;
                count = 1;
            }
        }

        // 마지막 배포 추가
        answer.add(count);

        return answer.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }

    private int getRequiredDays(int progress, int speed) {
        return (100 - progress + speed - 1) / speed;
    }
}
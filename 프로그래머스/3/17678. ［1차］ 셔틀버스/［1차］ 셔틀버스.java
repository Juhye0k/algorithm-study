import java.util.*;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        int[] time = new int[timetable.length];

        for (int i = 0; i < timetable.length; i++) {
            time[i] = calculate(timetable[i]);
        }

        Arrays.sort(time);

        int left = 0;
        int right = 9 * 60 + (n - 1) * t;
        int answer = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (canBoard(mid, n, t, m, time)) {
                // 탈 수 있다면 더 늦게 도착해 보기
                answer = mid;
                left = mid + 1;
            } else {
                // 탈 수 없다면 더 일찍 도착하기
                right = mid - 1;
            }
        }

        return String.format(
            "%02d:%02d",
            answer / 60,
            answer % 60
        );
    }

    // 콘이 arrive분에 도착했을 때 탑승 가능한가?
    private boolean canBoard(
            int arrive, int n, int t, int m, int[] time
    ) {
        int index = 0; // 아직 타지 않은 첫 번째 크루

        for (int bus = 0; bus < n; bus++) {
            int busTime = 9 * 60 + bus * t;
            int boarded = 0;

            while (boarded < m) {
                // 버스 시각까지 도착했고,
                // 콘보다 먼저 서는 크루가 있다면 먼저 태움
                if (index < time.length
                        && time[index] <= busTime
                        && time[index] <= arrive) {
                    index++;
                    boarded++;
                } else if (arrive <= busTime) {
                    // 콘보다 앞선 크루가 없고 콘도 도착했으므로 탑승
                    return true;
                } else {
                    // 콘은 아직 도착하지 않았고,
                    // 지금 더 태울 수 있는 크루도 없음
                    break;
                }
            }
        }

        return false;
    }

    public static int calculate(String str) {
        String[] parts = str.split(":");

        int hour = Integer.parseInt(parts[0]);
        int minute = Integer.parseInt(parts[1]);

        return hour * 60 + minute;
    }
}
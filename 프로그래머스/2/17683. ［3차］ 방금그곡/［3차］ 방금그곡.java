class Solution {
    public String solution(String m, String[] musicinfos) {
        String answer = "(None)";
        int maxTime = -1;

        // 기억한 멜로디도 한 음당 한 글자로 변환
        m = convert(m);

        for (String info : musicinfos) {
            String[] q = info.split(",");

            int start = calculate(q[0]);
            int end = calculate(q[1]);
            int time = end - start;

            String title = q[2];
            String melody = convert(q[3]);

            // 실제로 재생된 음악을 처음부터 생성
            StringBuilder played = new StringBuilder();

            for (int i = 0; i < time; i++) {
                played.append(
                    melody.charAt(i % melody.length())
                );
            }

            // 일치하고, 기존 후보보다 재생 시간이 길면 교체
            if (played.toString().contains(m)
                    && time > maxTime) {
                answer = title;
                maxTime = time;
            }
        }

        return answer;
    }

    // 샵이 붙은 음을 소문자 하나로 변환
    public static String convert(String str) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            if (i + 1 < str.length()
                    && str.charAt(i + 1) == '#') {
                result.append(Character.toLowerCase(ch));
                i++; // #은 이미 처리했으므로 건너뜀
            } else {
                result.append(ch);
            }
        }

        return result.toString();
    }

    public static int calculate(String str) {
        String[] s = str.split(":");

        int hour = Integer.parseInt(s[0]);
        int minute = Integer.parseInt(s[1]);

        return hour * 60 + minute;
    }
}
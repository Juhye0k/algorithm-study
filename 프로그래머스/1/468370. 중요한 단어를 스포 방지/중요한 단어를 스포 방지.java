import java.util.*;

class Solution {
    public int solution(String message, int[][] spoiler_ranges) {
        int length = message.length();
        boolean[] hidden = new boolean[length];

        // 1. 가려진 문자 위치 표시
        for (int[] range : spoiler_ranges) {
            for (int i = range[0]; i <= range[1]; i++) {
                hidden[i] = true;
            }
        }

        Set<String> normalWords = new HashSet<>();
        Set<String> spoilerWords = new HashSet<>();

        // 2. 원래 메시지를 단어 단위로 읽기
        int index = 0;

        while (index < length) {
            if (message.charAt(index) == ' ') {
                index++;
                continue;
            }

            int start = index;
            boolean isSpoiler = false;

            while (index < length && message.charAt(index) != ' ') {
                if (hidden[index]) {
                    isSpoiler = true;
                }
                index++;
            }

            String word = message.substring(start, index);

            if (isSpoiler) {
                spoilerWords.add(word);
            } else {
                normalWords.add(word);
            }
        }

        // 3. 일반 영역에도 등장한 단어 제외
        spoilerWords.removeAll(normalWords);

        return spoilerWords.size();
    }
}
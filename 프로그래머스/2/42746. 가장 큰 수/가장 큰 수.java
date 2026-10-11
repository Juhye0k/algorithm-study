import java.util.Arrays;

class Solution {
    public String solution(int[] numbers) {
        String[] strings = new String[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            strings[i] = String.valueOf(numbers[i]);
        }

        Arrays.sort(strings, (a, b) -> (b + a).compareTo(a + b));

        // 정렬 후 첫 숫자가 0이면 모두 0
        if (strings[0].equals("0")) {
            return "0";
        }

        StringBuilder answer = new StringBuilder();
        for (String s : strings) {
            answer.append(s);
        }

        return answer.toString();
    }
}
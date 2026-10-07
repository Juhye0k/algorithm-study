import java.util.*;

class Solution {
    public int solution(String[] arr) {
        int n = (arr.length + 1) / 2;

        int[][] max = new int[n][n];
        int[][] min = new int[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(max[i], Integer.MIN_VALUE);
            Arrays.fill(min[i], Integer.MAX_VALUE);

            int number = Integer.parseInt(arr[i * 2]);
            max[i][i] = number;
            min[i][i] = number;
        }

        // 짧은 구간부터 계산
        for (int length = 2; length <= n; length++) {
            for (int i = 0; i <= n - length; i++) {
                int j = i + length - 1;

                // k번째 숫자 뒤의 연산자를 마지막에 계산
                // 왼쪽 구간: [i, k], 오른쪽 구간: [k + 1, j]
                for (int k = i; k < j; k++) {
                    String operator = arr[k * 2 + 1];

                    if (operator.equals("+")) {
                        max[i][j] = Math.max(
                            max[i][j],
                            max[i][k] + max[k + 1][j]
                        );
                        min[i][j] = Math.min(
                            min[i][j],
                            min[i][k] + min[k + 1][j]
                        );
                    } else {
                        max[i][j] = Math.max(
                            max[i][j],
                            max[i][k] - min[k + 1][j]
                        );
                        min[i][j] = Math.min(
                            min[i][j],
                            min[i][k] - max[k + 1][j]
                        );
                    }
                }
            }
        }

        return max[0][n - 1];
    }
}
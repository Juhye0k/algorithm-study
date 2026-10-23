class Solution {
    public int solution(int m, int n, String[] board) {
        int answer = 0;
        String[][] graph = new String[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = board[i].substring(j, j + 1);
            }
        }

        while (true) {
            boolean[][] remove = new boolean[m][n];

            // 1. 삭제 대상만 표시
            // 위·왼쪽을 확인하므로 1부터 시작
            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    if (check(graph, i, j)) {
                        remove[i - 1][j - 1] = true;
                        remove[i - 1][j] = true;
                        remove[i][j - 1] = true;
                        remove[i][j] = true;
                    }
                }
            }

            // 2. 표시된 칸을 한꺼번에 삭제
            int removed = 0;

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (remove[i][j]) {
                        graph[i][j] = null;
                        removed++;
                    }
                }
            }

            // 지울 블록이 없으면 종료
            if (removed == 0) {
                break;
            }

            answer += removed;

            // 3. 열마다 남은 블록을 아래로 모으기
            for (int col = 0; col < n; col++) {
                int write = m - 1;

                for (int row = m - 1; row >= 0; row--) {
                    if (graph[row][col] != null) {
                        graph[write][col] = graph[row][col];
                        write--;
                    }
                }

                // 위쪽의 남은 공간 비우기
                while (write >= 0) {
                    graph[write][col] = null;
                    write--;
                }
            }
        }

        return answer;
    }

    public static boolean check(String[][] graph, int i, int j) {
        String current = graph[i][j];

        if (current == null) {
            return false;
        }

        return current.equals(graph[i - 1][j - 1])
            && current.equals(graph[i - 1][j])
            && current.equals(graph[i][j - 1]);
    }
}
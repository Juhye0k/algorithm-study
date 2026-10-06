import java.util.*;

class Node {
    int row, col, size;

    Node(int row, int col, int size) {
        this.row = row;
        this.col = col;
        this.size = size;
    }
}

class Solution {
    private int n, m;
    private Map<Integer, Node> apps;
    private Deque<Integer> wrapQueue;
    private Set<Integer> waiting;

    public int[][] solution(int[][] board, int[][] commands) {
        n = board.length;
        m = board[0].length;
        apps = new LinkedHashMap<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int id = board[i][j];
                if (id == 0 || apps.containsKey(id)) continue;

                int size = 0;
                while (i + size < n && board[i + size][j] == id) {
                    size++;
                }
                apps.put(id, new Node(i, j, size));
            }
        }

        for (int[] command : commands) {
            int id = command[0];
            int direction = command[1];

            wrapQueue = new ArrayDeque<>();
            waiting = new HashSet<>();

            // 먼저 명령받은 앱을 한 칸 민다.
            moveOne(id, direction, false);

            // 그 결과 격자 밖으로 나간 앱들을 처리한다.
            while (!wrapQueue.isEmpty()) {
                int wrappedId = wrapQueue.peek();
                Node app = apps.get(wrappedId);

                switch (direction) {
                    case 1: app.col = -app.size; break;
                    case 2: app.row = -app.size; break;
                    case 3: app.col = m; break;
                    case 4: app.row = n; break;
                }

                for (int step = 0; step < app.size; step++) {
                    moveOne(wrappedId, direction, true);
                }

                wrapQueue.poll();
                waiting.remove(wrappedId);
            }
        }

        int[][] answer = new int[n][m];
        for (Map.Entry<Integer, Node> entry : apps.entrySet()) {
            int id = entry.getKey();
            Node app = entry.getValue();

            for (int r = app.row; r < app.row + app.size; r++) {
                for (int c = app.col; c < app.col + app.size; c++) {
                    answer[r][c] = id;
                }
            }
        }
        return answer;
    }

    private void moveOne(int id, int direction, boolean reentering) {
        Node app = apps.get(id);

        switch (direction) {
            case 1: app.col++; break;
            case 2: app.row++; break;
            case 3: app.col--; break;
            case 4: app.row--; break;
        }

        if (!reentering && isOutside(app)) {
            wrapQueue.add(id);
            waiting.add(id);
            return;
        }

        for (Map.Entry<Integer, Node> entry : apps.entrySet()) {
            int otherId = entry.getKey();

            if (otherId == id || waiting.contains(otherId)) continue;

            if (overlaps(app, entry.getValue())) {
                moveOne(otherId, direction, false);
            }
        }
    }

    private boolean isOutside(Node app) {
        return app.row < 0 || app.col < 0
            || app.row + app.size > n
            || app.col + app.size > m;
    }

    private boolean overlaps(Node a, Node b) {
        return a.row < b.row + b.size
            && b.row < a.row + a.size
            && a.col < b.col + b.size
            && b.col < a.col + a.size;
    }
}
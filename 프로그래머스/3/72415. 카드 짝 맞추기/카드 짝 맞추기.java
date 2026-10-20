import java.util.*;

class Node {
    int x, y;
    int count;
    int num;
    int mask;

    public Node(int x, int y, int count, int num, int mask) {
        this.x = x;
        this.y = y;
        this.count = count;
        this.num = num;
        this.mask = mask;
    }
}

class Solution {
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public int solution(int[][] board, int r, int c) {
        int mask = 0;

        // 카드가 있는 위치를 기록
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                if (board[x][y] != 0) {
                    mask |= 1 << (x * 4 + y);
                }
            }
        }

        Queue<Node> q = new ArrayDeque<>();
        Set<Long> visited = new HashSet<>();

        add(q, visited, new Node(r, c, 0, 0, mask));

        while (!q.isEmpty()) {
            Node temp = q.poll();

            int x = temp.x;
            int y = temp.y;
            int pos = x * 4 + y;

            // 남아 있는 카드가 없으면 종료
            if (temp.mask == 0) {
                return temp.count;
            }

            // 1. Enter
            if (hasCard(temp.mask, x, y)) {
                if (temp.num == 0) {
                    // 첫 번째 카드 선택
                    // 아직 카드를 제거하지 않음
                    add(q, visited, new Node(
                        x, y,
                        temp.count + 1,
                        pos + 1,
                        temp.mask
                    ));
                } else {
                    int firstPos = temp.num - 1;
                    int firstX = firstPos / 4;
                    int firstY = firstPos % 4;

                    // 다른 위치의 같은 번호 카드라면 짝 완성
                    if (firstPos != pos
                            && board[firstX][firstY] == board[x][y]) {

                        int nextMask = temp.mask;

                        // 두 카드 제거
                        nextMask &= ~(1 << firstPos);
                        nextMask &= ~(1 << pos);

                        add(q, visited, new Node(
                            x, y,
                            temp.count + 1,
                            0,
                            nextMask
                        ));
                    }
                }
            }

            // 2. 상하좌우 일반 이동
            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (!check(nx, ny)) {
                    continue;
                }

                add(q, visited, new Node(
                    nx, ny,
                    temp.count + 1,
                    temp.num,
                    temp.mask
                ));
            }

            // 3. Ctrl + 방향키 이동
            for (int d = 0; d < 4; d++) {
                int nx = x;
                int ny = y;

                while (true) {
                    int nextX = nx + dx[d];
                    int nextY = ny + dy[d];

                    // 더 갈 수 없으면 끝 칸에서 멈춤
                    if (!check(nextX, nextY)) {
                        break;
                    }

                    nx = nextX;
                    ny = nextY;

                    // 가장 먼저 만난 남아 있는 카드에서 멈춤
                    if (hasCard(temp.mask, nx, ny)) {
                        break;
                    }
                }

                add(q, visited, new Node(
                    nx, ny,
                    temp.count + 1,
                    temp.num,
                    temp.mask
                ));
            }
        }

        return -1;
    }

    // 보드 안에 있는가?
    public static boolean check(int x, int y) {
        return x >= 0 && x < 4 && y >= 0 && y < 4;
    }

    // 현재 상태에서 이 위치에 카드가 남아 있는가?
    public static boolean hasCard(int mask, int x, int y) {
        int pos = x * 4 + y;
        return (mask & (1 << pos)) != 0;
    }

    // 같은 상태는 한 번만 큐에 추가
    private static void add(
            Queue<Node> q,
            Set<Long> visited,
            Node next
    ) {
        int pos = next.x * 4 + next.y;

        // 남은 카드 + 커서 위치 + 첫 선택 위치를 하나의 값으로 묶음
        long key = ((long) next.mask * 16 + pos) * 17 + next.num;

        if (visited.add(key)) {
            q.add(next);
        }
    }
}
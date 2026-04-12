import java.util.*;
import java.io.*;

class Node{
    int x,y;
    int value;
    Node(int x, int y, int value) {
        this.x=x;
        this.y=y;
        this.value=value;
    }
}

public class Main {
    static int[][] ar;
    static int[] dx={1,0,-1,0};
    static int[] dy={0,1,0,-1};
    static int index;
    static int[] wall;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        index = 0;

        // 반복 횟수 K
        int K = Integer.parseInt(st.nextToken());
        // 벽면에 적힌 유물 조각 M
        int M = Integer.parseInt(st.nextToken());

        ar = new int[5][5];
        // 유물 조각에 적혀 있는 숫자들
        for(int i=0; i<5; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<5; j++) {
                ar[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 유물 조각 번호
        st = new StringTokenizer(br.readLine());
        wall = new int[M];
        for(int i=0; i<M; i++) {
            wall[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0; i<K; i++) {
            // 회전 횟수
            int rotateMinCount = 4;
            // 최대값
            int maxValue = 0;
            // 열
            int bestCol = 10;
            // 행
            int bestRow = 10;
            int[][] tempArr = null;

            // 문제 조건: 열이 작은 중심 우선, 같으면 행이 작은 중심 우선
            for(int j = 1; j <= 3; j++) {      // j = 행
                for(int k = 1; k <= 3; k++) {  // k = 열
                    int tempRotateMinCount = 4;
                    int tempMaxValue = 0;
                    int[][] tempAr = null;

                    // 회전할 때 잠깐 쓸 배열
                    int[][] cur = copyArr(ar);

                    // 90, 180, 270도 누적 회전
                    for(int l = 0; l < 3; l++) {
                        cur = rotate(cur, j, k);
                        // 회전 결과의 크기
                        int listResult = calculateResult(cur);

                        if(listResult > tempMaxValue) {
                            tempMaxValue = listResult;
                            tempRotateMinCount = l;
                            tempAr = copyArr(cur);
                        }
                    }

                    // 후보 비교
                    if(tempMaxValue > maxValue) {
                        maxValue = tempMaxValue;
                        rotateMinCount = tempRotateMinCount;
                        bestCol = k;
                        bestRow = j;
                        tempArr = tempAr;
                    }
                    else if(tempMaxValue == maxValue) {
                        if(tempRotateMinCount < rotateMinCount) {
                            rotateMinCount = tempRotateMinCount;
                            bestCol = k;
                            bestRow = j;
                            tempArr = tempAr;
                        }
                        else if(tempRotateMinCount == rotateMinCount) {
                            // 문제 조건: 열이 작은 중심, 같으면 행이 작은 중심
                            if(k < bestCol || (k == bestCol && j < bestRow)) {
                                bestCol = k;
                                bestRow = j;
                                tempArr = tempAr;
                            }
                        }
                    }
                }
            }

            if(maxValue == 0) break;

            ar = tempArr;
            int turnScore = 0;

            while(true) {
                int gained = removeAll(ar);
                if(gained == 0) break;
                turnScore += gained;
                add(ar);
            }

            if(sb.length() > 0) sb.append(" ");
            sb.append(turnScore);
        }

        System.out.println(sb);
    }

    // (x, y)를 중심으로 하는 3x3 시계 방향 90도 회전
    public static int[][] rotate(int[][] ar, int x, int y) {
        int[][] rotateArr = copyArr(ar);

        int sx = x - 1;
        int sy = y - 1;

        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                rotateArr[sx + j][sy + 2 - i] = ar[sx + i][sy + j];
            }
        }

        return rotateArr;
    }

    public static int calculate(int i, int j, boolean[][] visited, int[][] list) {
        int result = 1;
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(i, j, list[i][j]));
        visited[i][j] = true;

        while(!q.isEmpty()) {
            Node temp = q.poll();

            for(int m = 0; m < 4; m++) {
                int nx = temp.x + dx[m];
                int ny = temp.y + dy[m];

                if(isOut(nx, ny)) continue;
                if(visited[nx][ny]) continue;
                if(list[nx][ny] != temp.value) continue;

                q.add(new Node(nx, ny, temp.value));
                visited[nx][ny] = true;
                result++;
            }
        }

        return result;
    }

    // 현재 보드에서 3개 이상 연결된 그룹들의 총 크기
    public static int calculateResult(int[][] list) {
        boolean[][] visited = new boolean[5][5];
        int answer = 0;

        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                if(visited[i][j]) continue;

                int result = calculate(i, j, visited, list);
                if(result >= 3) answer += result;
            }
        }
        return answer;
    }

    // 3개 이상 연결된 모든 그룹을 동시에 제거
    public static int removeAll(int[][] board) {
        boolean[][] visited = new boolean[5][5];
        boolean[][] erase = new boolean[5][5];
        int total = 0;

        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                if(visited[i][j]) continue;

                List<Node> list = new ArrayList<>();
                Queue<Node> q = new LinkedList<>();

                q.add(new Node(i, j, board[i][j]));
                visited[i][j] = true;
                list.add(new Node(i, j, board[i][j]));

                while(!q.isEmpty()) {
                    Node temp = q.poll();

                    for(int d = 0; d < 4; d++) {
                        int nx = temp.x + dx[d];
                        int ny = temp.y + dy[d];

                        if(isOut(nx, ny)) continue;
                        if(visited[nx][ny]) continue;
                        if(board[nx][ny] != temp.value) continue;

                        q.add(new Node(nx, ny, temp.value));
                        visited[nx][ny] = true;
                        list.add(new Node(nx, ny, temp.value));
                    }
                }

                if(list.size() >= 3) {
                    total += list.size();
                    for(Node p : list) {
                        erase[p.x][p.y] = true;
                    }
                }
            }
        }

        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                if(erase[i][j]) board[i][j] = 0;
            }
        }

        return total;
    }

    public static boolean isOut(int x, int y) {
        return x < 0 || x >= 5 || y < 0 || y >= 5;
    }

    // 열 번호가 작은 순, 같으면 행 번호가 큰 순
    public static void add(int[][] arr) {
        for(int j = 0; j < 5; j++) {
            for(int i = 4; i >= 0; i--) {
                if(arr[i][j] == 0) {
                    arr[i][j] = wall[index++];
                }
            }
        }
    }

    public static int[][] copyArr(int[][] src) {
        int[][] dest = new int[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                dest[i][j] = src[i][j];
            }
        }
        return dest;
    }
}
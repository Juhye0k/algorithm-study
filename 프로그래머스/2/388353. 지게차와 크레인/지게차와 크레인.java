import java.util.*;
class Solution {
    static int n;
    static int m;
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};
    static String[][] arr;
    public int solution(String[] storage, String[] requests) {
        int answer = 0;
        
        n = storage.length;
        m = storage[0].length();
        
        arr = new String[n+2][m+2];
        for (int i = 0; i < n + 2; i++) {
            Arrays.fill(arr[i], " ");
        }
        for(int i=1; i<=n; i++) {
            String str = storage[i-1];
            for(int j=1; j<=m; j++) {
                arr[i][j] = str.substring(j-1,j);
            }
        }
        
        for(int i=0; i<requests.length; i++) {
            int length = requests[i].length();
            String target = requests[i].substring(0,1);
            if(length == 1) {
                bfs(target);
            }
            else {
                for(int j=0; j<n+2; j++) {
                    for(int k=0; k<m+2; k++) {
                        if(arr[j][k].equals(requests[i].substring(0,1))) {
                            arr[j][k] = " ";
                        }
                    }
                }
            }
        }
        for(int i=0; i<n+2; i++) {
            for(int j=0; j<m+2; j++) {
                if(!arr[i][j].equals(" "))
                    answer++;
            }
        }
        
        return answer;
    }
    public static void bfs(String target) {
        Queue<int[]> q = new ArrayDeque<>();
            List<int[]> remove = new ArrayList<>();

        boolean[][] visited = new boolean[n+2][m+2];
        visited[0][0] = true;
        q.add(new int[]{0,0});
        while(!q.isEmpty()) {
            int[] temp = q.poll();
            for(int i=0; i<4; i++) {
                int nx = temp[0] + dx[i];
                int ny = temp[1] + dy[i];
                if(nx<0 || nx>=n+2 || ny<0 || ny>=m+2) continue;
                if(visited[nx][ny]) continue;
                visited[nx][ny] = true;
                    if (arr[nx][ny].equals(" ")) {
                // 빈칸만 통과
                q.offer(new int[]{nx, ny});
            } else if (arr[nx][ny].equals(target)) {
                // 대상 컨테이너는 기록만 하고 통과하지 않음
                remove.add(new int[]{nx, ny});
            }
            
        }
        }
         // 요청 당시 접근 가능했던 컨테이너만 한꺼번에 제거
    for (int[] position : remove) {
        arr[position[0]][position[1]] = " ";
    }
    }
}
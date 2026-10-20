import java.util.*;
class Node implements Comparable<Node>{
    int x,y;
    int value;
    
    public Node(int x, int y, int value) {
        this.x=x;
        this.y=y;
        this.value=value;
    }
    @Override
    public int compareTo(Node o) {
        if(this.x==o.x) {
            return this.y-o.y;
        }
        return this.x-o.x;
    }
}
class Solution {
    static int[][][] ar;
    public int[][] solution(int n, int[][] build_frame) {
        int[][] answer = {};
        // 배열 선언
        ar = new int[n+1][n+1][2];
        // frame 순회
        for(int i=0; i<build_frame.length; i++) {
            // x,y 좌표
            int x = build_frame[i][0];
            int y = build_frame[i][1];
            // 0: 기둥, 1:보
            int a = build_frame[i][2];
            // 0: 삭제, 1:설치
            int b = build_frame[i][3];
            
            // 보 삭제 -> 양쪽에 보를 확인해서, 해당 보가 살아남을 수 있는지 확인하기
            // 기둥 삭제 -> 기둥을 삭제했을 때 보가 살아남을 수 있는지 확인하기
            if(b == 0) {
                sub(a,x,y);
            }
            else if (b == 1) {
                add(a,x,y);
            }
        }
        List<int[]> list = new LinkedList<>();
        for(int i=0; i<=n; i++) {
            for(int j=0; j<=n; j++) {
                if(ar[i][j][0] == 1) 
                    list.add(new int[]{i,j,0});
                if(ar[i][j][1] == 1) 
                    list.add(new int[]{i,j,1});
            }
        }
        return list.toArray(new int[list.size()][3]);
    }
   public static void add(int product, int x, int y) {
    // 기둥 설치 요청이고, 설치 가능하면
    if (product == 0 && gicheck(x, y)) {
        ar[x][y][0] = 1;
    }

    // 보 설치 요청이고, 설치 가능하면
    if (product == 1 && bocheck(x, y)) {
        ar[x][y][1] = 1;
    }
}
    public static void sub(int product, int x, int y) {
        ar[x][y][product] = 0;
        if(!allValid()) {
            ar[x][y][product] = 1;
        }
    }
    public static boolean allValid() {
        for(int x=0; x<ar.length; x++) {
            for(int y=0; y<ar[0].length; y++) {
                if(has(x,y,0) && !gicheck(x,y)) {
                    return false;
                }
                 if (has(x, y, 1) && !bocheck(x, y)) {
                return false;
            }
            }
        }
        return true;
    }
        
   
    // 범위 밖이면 해당 구조물이 없다고 판단
    public static boolean has(int x, int y, int type) {
        if (x < 0 || x >= ar.length
                || y < 0 || y >= ar[0].length) {
            return false;
        }

        return ar[x][y][type] == 1;
    }

    // 기둥이 서 있을 수 있는지
    public static boolean gicheck(int x, int y) {
        return y == 0                 // 바닥
            || has(x, y - 1, 0)       // 바로 아래 기둥
            || has(x - 1, y, 1)       // 왼쪽에서 끝나는 보
            || has(x, y, 1);          // 현재 위치에서 시작하는 보
    }

    // 보가 설치될 수 있는지
    public static boolean bocheck(int x, int y) {
        return has(x, y - 1, 0)           // 왼쪽 끝 아래 기둥
            || has(x + 1, y - 1, 0)       // 오른쪽 끝 아래 기둥
            || (has(x - 1, y, 1)          // 양쪽 보가 모두 존재
                && has(x + 1, y, 1));
    }
}
import java.util.*;
class Node {
    int x,y;
    public Node(int x, int y) {
        this.x=x;
        this.y=y;
    }
}
class Solution {
    static boolean[][] visited;
    static int size;
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};
    public int solution(int[][] game_board, int[][] table) {
        int answer = 0;
        size = game_board.length;
        visited = new boolean[game_board.length][game_board.length];
        List<List<int[]>> blanks = new ArrayList<>();
        for(int i=0; i<game_board.length; i++) {
            for(int j=0; j<game_board[0].length; j++) {
                if(game_board[i][j]==0 && !visited[i][j]) {
                    List<int[]> shape = bfs(game_board,new Node(i,j),0);
                    blanks.add(normalize(shape));
                }
            }
        }
        visited = new boolean[size][size];
        List<List<int[]>> pieces = new ArrayList<>();
         for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (table[i][j] == 1 && !visited[i][j]) {
                    List<int[]> shape = bfs(table, new Node(i, j), 1);
                    pieces.add(normalize(shape));
                }
            }
        }
        boolean[] used = new boolean[pieces.size()];
        for(List<int[]> blank: blanks) {
            for(int i=0; i<pieces.size(); i++) {
                if(used[i]) continue;
                if(blank.size()!=pieces.get(i).size()) continue;
                List<int[]> piece = pieces.get(i);
                if(canMatch(blank,piece)) {
                    used[i]=true;
                    answer+=blank.size();
                    break;
                }
            }
        }
        
        return answer;
    }
    public static List<int[]> bfs(int[][] board, Node start,int target) {
        Queue<Node> q = new LinkedList<>();
        List<int[]> shape = new ArrayList<>();
        q.add(new Node(start.x,start.y));
        visited[start.x][start.y] = true;
        while(!q.isEmpty()) {
            Node temp = q.poll();
            shape.add(new int[]{temp.x,temp.y});
            for(int i=0; i<4; i++) {
                int x = temp.x+dx[i];
                int y = temp.y+dy[i];
                if(x>=0 && x<size && y>=0 && y<size && !visited[x][y] && board[x][y] == target) {
                    visited[x][y] = true;
                    q.add(new Node(x,y));
                }
            }
        }
        return shape;
    }
    public static List<int[]> normalize(List<int[]> shape) {
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        
        for(int[] s:shape) {
            minX = Math.min(s[0],minX);
            minY = Math.min(s[1],minY);
        }
        List<int[]> normalized = new ArrayList<>();
        for(int[] s:shape) {
            normalized.add(new int[]{s[0]-minX,s[1]-minY});
        }
        normalized.sort((a,b) -> {
            if(a[0]==b[0]) return a[1]-b[1];
            return a[0]-b[0];
        });
        return normalized;
        
    }
    public static List<int[]> rotate(List<int[]> shape) {
        List<int[]> rotated = new ArrayList<>();
        
        for(int[] cell:shape) {
            int x = cell[0];
            int y = cell[1];
            rotated.add(new int[]{y,-x});
        }
        return normalize(rotated);
    }
    public static boolean isSame(List<int[]> a, List<int[]> b) {
        if(a.size()!=b.size()) return false;
        
        for(int i=0; i<a.size(); i++) {
            if(a.get(i)[0]!=b.get(i)[0] || a.get(i)[1]!=b.get(i)[1]) {
                return false;
            }
        }
        return true;
    }
    public static boolean canMatch(List<int[]> blank, List<int[]> piece) {
    List<int[]> rotated = piece;

    for (int r = 0; r < 4; r++) {
        if (isSame(blank, rotated)) return true;
        rotated = rotate(rotated);
    }
    return false;
}
}
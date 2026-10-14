import java.util.*;
class Node {
    int x,y;
    public Node(int x, int y) {
        this.x = x ;
        this.y = y;
    }
}
class Solution {
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};
    static int size;

    static List<List<Node>> board_list = new ArrayList<>();
    static List<List<Node>> table_list = new ArrayList<>();
    public int solution(int[][] game_board, int[][] table) {
        int answer = 0;
        size = game_board.length;
        board_list.clear();
        table_list.clear();
        boolean[][] boardVisited = new boolean[size][size];
        boolean[][] tableVisited = new boolean[size][size];
        
        for(int i=0; i<size; i++) {
            for(int j=0; j<size; j++) {
                if(game_board[i][j] == 0 && !boardVisited[i][j]) {
                    List<Node> space = bfs(new Node(i,j), game_board, boardVisited,0);
                    board_list.add(normalize(space));
                    
                    
                }
                if(table[i][j] == 1 && !tableVisited[i][j]) {
                        List<Node> place = bfs(new Node(i,j), table, tableVisited, 1);
                        table_list.add(normalize(place));
                    }
            }
        }
        boolean[] used = new boolean[table_list.size()];
        
        for(List<Node> space : board_list) {
            for(int i=0; i<table_list.size(); i++) {
                if(used[i]) continue;
                
                List<Node> piece =table_list.get(i);
                if(space.size()!=piece.size()) continue;
                
                boolean matched = false;
                
                for(int r=0; r<4; r++) {
                    if(sameShape(space, piece)) {
                        matched = true;
                        break;
                    }
                    piece = rotate(piece);
                }
                if(matched) {
                    used[i] = true;
                    answer+=space.size();
                    break;
                }
            }
        }
        /*
        game_board에 먼저 블록 1을 다 채우고, 빈 공간을 확보한다.
        
        이후 빈 공을 dfs 또는 bfs로 탐색하면서 빈 공간의 절대적인 위치를 기록한다.
        이후 table도 그래프 탐색을 하면서 절대적인 위치가 같은 블록이 있으면  해당 블록을 사용한다.
        */
        return answer;
    }
    public static List<Node> bfs(Node start, int[][] game_board, boolean[][] visited, int target) {
        List<Node> list = new LinkedList<>();
        Queue<Node> q = new LinkedList<>();
        visited[start.x][start.y] = true;
        q.add(start);
        while(!q.isEmpty()) {
            Node temp = q.poll();
            list.add(temp);
            for(int i=0; i<4; i++) {
                int x = temp.x+dx[i];
                int y = temp.y+dy[i];
                if(x<0 || x>=size || y<0 || y>=size) continue;
                if(visited[x][y] || game_board[x][y] !=target) continue;
                visited[x][y] = true;
                q.add(new Node(x,y));
            }
        }
        
        return list;
        
    }
    public static List<Node> normalize(List<Node> list) {
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        
        for(Node node : list) {
            minX = Math.min(minX, node.x);
            minY = Math.min(minY, node.y);
        }
        
        List<Node> result = new ArrayList<>();
        
        for(Node node : list) {
            result.add(new Node(node.x-minX, node.y-minY));
        }
        
        result.sort((a,b) -> {
            if(a.x!=b.x) {
                return Integer.compare(a.x, b.x);
            }
            return Integer.compare(a.y, b.y);
        });
        return result;
    }
    public static List<Node> rotate(List<Node> list) {
        List<Node> result = new ArrayList<>();
        
        for(Node node : list) {
            result.add(new Node(node.y, -node.x));
        }
        return normalize(result);
    }
    public static boolean sameShape(List<Node> a, List<Node> b) {
        for(int i=0; i<a.size(); i++) {
            if(a.get(i).x!=b.get(i).x
               || a.get(i).y != b.get(i).y) {
                return false;
            }
        }
        return true;
    }
}

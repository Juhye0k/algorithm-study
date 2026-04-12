import java.io.*;
import java.util.*;
class Node implements Comparable<Node>{
    int k;
    int h;
    int w;
    int row,col;
    boolean removed;
    public Node(int k, int h, int w, int row, int col, boolean removed) {
        this.k = k;
        this.h = h;
        this.w = w;
        this.row = row;
        this.col = col;
        this.removed = removed;
    }
    @Override
    public int compareTo(Node o) {

        return this.row-o.row;
    }
}
public class Main {
    public static void main(String[] args) throws IOException{
        // Please write your code here.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        // 격자 크기
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] ar = new int[N+1][N+1];
        int [] height = new int[N+1];
        List<Node> boxes = new LinkedList<>();
        for(int q=0; q<M; q++) {
            st = new StringTokenizer(br.readLine());

            // 택배 번호
            int k = Integer.parseInt(st.nextToken());
            // 세로 크기
            int h = Integer.parseInt(st.nextToken());
            // 가로 크기
            int w = Integer.parseInt(st.nextToken());
            // 좌측 좌표
            int c = Integer.parseInt(st.nextToken());


            // 처음 위치에서 내려가다가 블록 만나면 멈추기
            int base = 0;
            for(int i=c; i<c+w; i++ ) {
                base = Math.max(base, height[i]);
            }
            for(int i=c; i<c+w; i++) {
                height[i] = base+h;
            }
            for(int i=base; i<base+h; i++) {
                for(int j=c; j<c+w; j++) {
                    ar[i][j] = k;
                }
            }
            boxes.add(new Node(k,h,w, base, c,false));
        }
        int removedCount = 0;
        while(removedCount<M) {
            // 택배 하차 좌측
            boolean isRemovedThisCycle = false;
            PriorityQueue<Node> leftpq = new PriorityQueue<>((a, b) -> a.k - b.k);
            for(Node box:boxes) {
                // 해당 박스의 상단 왼쪽 좌표, 세로 크기만큼 왼쪽을 탐색
                if (box.removed) continue;
                int firstLeftX = box.row;
                int firstLeftY = box.col;
                boolean isCan = true;
                for(int i=firstLeftX; i<firstLeftX+box.h; i++) {
                    for(int j=firstLeftY-1; j>=1; j--) {
                        if(ar[i][j]!=0) {
                            isCan = false;
                            break;
                        }
                    }
                    if(!isCan) break;
                }
                if(isCan) {
                    leftpq.add(box);
                }
            }
            // 후보군에 넣어서 번호가 가장 작은 거빼기, 해당 좌표 0으로 만들고 삭제
            if(!leftpq.isEmpty()) {
                Node leftnode = leftpq.poll();
                sb.append(leftnode.k).append("\n");
                for(Node box:boxes) {
                    if(box.k==leftnode.k) {
                        for(int i=box.row; i<box.row+box.h; i++) {
                            for(int j=box.col; j<box.col+box.w; j++) {
                                ar[i][j] = 0;
                            }
                        }
                        box.removed = true;
                        removedCount++;
                        isRemovedThisCycle = true;
                        break;
                    }
                }
                ar = new int[N + 1][N + 1];
                height = new int[N + 1];
                // 낙하
                List<Node> newBox = new LinkedList<>();
                for(Node box:boxes) {
                    if(box.removed) continue;
                    newBox.add(box);
                }
                Collections.sort(newBox);
                for(Node box:newBox) {
                    int base = 0;
                    for(int i=box.col; i<box.col+box.w; i++ ) {
                        base = Math.max(base, height[i]);
                    }
                    box.row = base;
                    for(int i=box.col; i<box.col+box.w; i++) {
                        height[i] = base+box.h;
                    }
                    for(int i=base; i<base+box.h; i++) {
                        for(int j=box.col; j<box.col+box.w; j++) {
                            ar[i][j] = box.k;
                        }
                    }
                }
            }


            // 택배 하차 우측
            // 낙하
            // 택배 하차 좌측
            if (removedCount == M) break;
            PriorityQueue<Node> rightpq = new PriorityQueue<>((a, b) -> a.k - b.k);        for(Node box:boxes) {
                // 해당 박스의 상단 왼쪽 좌표, 세로 크기만큼 왼쪽을 탐색
                if (box.removed) continue;
                int firstRightX = box.row;
                int firstRightY = box.col+box.w;
                boolean isCan = true;
                for(int i=firstRightX; i<firstRightX+box.h; i++) {
                    for(int j=firstRightY; j<=N; j++) {
                        if(ar[i][j]!=0) {
                            isCan = false;
                            break;
                        }
                    }
                    if(!isCan) break;
                }
                if(isCan) {
                    rightpq.add(box);
                }
            }
            if (!rightpq.isEmpty()) {
                // 후보군에 넣어서 번호가 가장 작은 거빼기, 해당 좌표 0으로 만들고 삭제
                Node node = rightpq.poll();


                sb.append(node.k).append("\n");
                for(Node box:boxes) {
                    if(box.k==node.k) {
                        box.removed = true;
                        removedCount++;
                        isRemovedThisCycle = true;
                        for(int i=box.row; i<box.row+box.h; i++) {
                            for(int j=box.col; j<box.col+box.w; j++) {
                                ar[i][j] = 0;

                            }
                        }
                    }
                }
                ar = new int[N + 1][N + 1];
                height = new int[N + 1];
                // 낙하
                List<Node> newBox1 = new LinkedList<>();
                for(Node box:boxes) {
                    if(box.removed) continue;
                    newBox1.add(box);
                }
                Collections.sort(newBox1);
                for(Node box:newBox1) {
                    int base = 0;
                    for(int i=box.col; i<box.col+box.w; i++ ) {
                        base = Math.max(base, height[i]);
                    }
                    box.row = base;
                    for(int i=box.col; i<box.col+box.w; i++) {
                        height[i] = base+box.h;
                    }
                    for(int i=base; i<base+box.h; i++) {
                        for(int j=box.col; j<box.col+box.w; j++) {
                            ar[i][j] = box.k;
                        }
                    }

                }
            }
            if(!isRemovedThisCycle)
                break;
        }
        System.out.println(sb);
    }
}
import java.io.*;
import java.util.*;

class Player {
    int index;
    int power;
    int hp;
    int contribution;
    public Player(int index) {
        this.index = index;
        this.contribution=0;
    }
}

public class Main {
        static int N;
        static List<int[]> snake;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        // 보드의 크기 N 입력
        N=Integer.parseInt(br.readLine());
        // 사과의 개수 K 입력
        int K=Integer.parseInt(br.readLine());
        snake=new ArrayList<>();
        // 보드를 정의할 배열
        int board[][]=new int[N+1][N+1];
        // 사과의 위치 배열
        int apple[][]=new int[N+1][N+1];
        HashMap<Integer,String> snakeDirection=new HashMap<>();
        // 뱀의 방향 변환 배열
        int dx[]={0,1,0,-1};
        int dy[]={1,0,-1,0};
        int time=0;
        // 사과의 위치
        for(int i=0;i<K;i++){
            st=new StringTokenizer(br.readLine());
            int x=Integer.parseInt(st.nextToken());
            int y=Integer.parseInt(st.nextToken());
            apple[x][y]=1;
        }

        // 뱀의 방향 변환 횟수 L 입력
        int L=Integer.parseInt(br.readLine());

        // 뱀의 방향 변환 정보
        for(int i=0;i<L;i++){
            st=new StringTokenizer(br.readLine());
            int X=Integer.parseInt(st.nextToken());
            String C=st.nextToken();
            snakeDirection.put(X,C);
        }
        int cx =1, cy=1;
        int d=0;
        snake.add(new int[]{cx,cy});

        // while문으로 시작
        while(true){
            // 매초 이동을 한다
            time++;
            // 몸길이를 늘려 머리를 다음칸에 위치시킨다
            int nx=cx+dx[d];
            int ny=cy+dy[d];
            // 만약 다음칸에 갔는데 벽이나 자기 자신의 몸이면 끝남
            if(isFinish(nx,ny)){
                break;
            }
            // 사과 있으면
            if(apple[nx][ny]==1){
                // 사과가 없어지고
                apple[nx][ny]=0;
                // 꼬리는 그대로
                snake.add(new int[]{nx,ny});
            }
            // 사과 없으면
            else{
                snake.add(new int[]{nx,ny});
                // 꼬리 움직임
                snake.remove(0);
            }
            if(snakeDirection.containsKey(time)){
                if(snakeDirection.get(time).equals("D")){
                    d+=1;
                    if(d==4)
                        d=0;
                }
                else{
                    d-=1;
                    if(d==-1)
                        d=3;
                }
            }
            cx= nx;
            cy= ny;

        }
        System.out.println(time);
    }
    public static boolean isFinish(int nx, int ny){
        if(nx<1 || ny<1 || nx>N || ny>N){
            return true;
        }
        for(int i=0;i<snake.size();i++){
            if(snake.get(i)[0]==nx && snake.get(i)[1]==ny){
                return true;
            }
        }
        return false;
    }
}


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;



class Node {
    int x,y;
    int value;
    public Node(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }
}

public class Main {


    static int[][] ar;
    static int[] dx={1,0,-1,0};
    static int[] dy={0,1,0,-1};
    static boolean[][] visited;
    static int R,C;
    static int air1X,air1Y;
    static int air2X,air2Y;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 행
        R = Integer.parseInt(st.nextToken());
        // 열
        C = Integer.parseInt(st.nextToken());
        // 초
        int T = Integer.parseInt(st.nextToken());
        // 초기 배열
        ar=new int[R][C];
        for(int i=0; i<R; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<C; j++) {
                ar[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        // 공기청정기 위치 찾기
        boolean check = false;
        for(int i=0; i<R; i++) {
            for(int j=0; j<C; j++) {
                if(ar[i][j]==-1) {
                    if(!check) {
                        check = true;
                        air1X=i;
                        air1Y=j;
                    }
                    else{
                        air2X=i;
                        air2Y=j;
                    }

                }
            }
        }


        // T초동안 미세먼지 굴리기
        for(int i=0; i<T; i++) {
            // 해당 초에서 먼지가 발산한 것을 저장하기 위한 배열
            mungi();
            cleanUp();
            cleanDown();
        }
        int ans =0;
        for(int i=0; i<R; i++) {
            for(int j=0; j<C; j++) {
                if(ar[i][j]>0){
                    ans += ar[i][j];
                }
            }
        }
        System.out.println(ans);




    }
    public static void mungi() {
       int[][] next = new int[R][C];

       for(int i=0; i<R; i++) {
           for(int j=0; j<C; j++) {
               if(ar[i][j]>0) {
                   int spreadAmount = ar[i][j]/5;
                   int cnt =0;
                   for(int k=0; k<4; k++) {
                       int nx = i+dx[k];
                       int ny = j+dy[k];
                       if(nx>=0 && nx<R && ny>=0 && ny<C && ar[nx][ny]!=-1) {
                           next[nx][ny] += spreadAmount;
                           cnt++;
                       }
                   }
                   next[i][j] +=ar[i][j]- spreadAmount*cnt;
               }
           }
       }
       next[air1X][0] = -1;
       next[air2X][0] = -1;
       ar=next;
    }
    static void cleanUp() {
        for(int i=air1X-1; i>0; i--)
            ar[i][0] = ar[i-1][0];
        for(int i=0; i<C-1; i++)
            ar[0][i] =ar[0][i+1];
        for(int i=0; i<air1X;i++ )
            ar[i][C-1] = ar[i+1][C-1];
        for(int i=C-1; i>1; i--) {
            ar[air1X][i] = ar[air1X][i-1];
        }
        ar[air1X][0] = -1;
        ar[air1X][1] = 0;
    }
    static void cleanDown() {
        for(int i=air2X+1; i<R-1; i++)
            ar[i][0] = ar[i+1][0];
        for(int i=0; i<C-1; i++)
            ar[R-1][i] =ar[R-1][i+1];
        for(int i=R-1;i>air2X;i-- )
            ar[i][C-1] = ar[i-1][C-1];
        for(int i=C-1; i>1; i--) {
            ar[air2X][i] = ar[air2X][i-1];
        }
        ar[air2X][0] = -1;
        ar[air2X][1] = 0;
    }
}

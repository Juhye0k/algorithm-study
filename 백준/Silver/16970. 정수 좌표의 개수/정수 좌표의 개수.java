
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


class Node {
    int x,y;
    Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {

   static int N,M,K;
   static Node[] selected;
   static int result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // 범위가 작다 -> 브루트포스로 해보자

        for(int i=0;i<=N;i++) {
            for(int j=0;j<=M;j++) {
                for(int k=0;k<=N;k++) {
                    for(int l=0;l<=M;l++) {
                        if(i==k && j==l)
                            continue;
                        int dx = Math.abs(k-i);
                        int dy = Math.abs(l-j);
                        int points =gcd(dx, dy) + 1;
                        if(points == K ) {
                            result++;
                        }
                    }
                }
            }
        }
        System.out.println(result/2);


    }
    public static int gcd(int a, int b) {
        if(b==0) return a;
        return gcd(b, a%b);
    }





}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Node {
    int x, y;
    Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static int N, P, E;
    static int[] selected;
    static Node[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        
        selected = new int[N];
        list = new Node[N];
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            list[i] = new Node(x1, x2);
        }
        
        choice(0, 0);
        
        System.out.println("-1");
    }

    public static void choice(int start, int count) {
        if (count == P) {
            int minSum = 0;
            int maxSum = 0;
            for (int i = 0; i < N; i++) {
                if (selected[i] == 1) {
                    minSum += list[i].x;
                    maxSum += list[i].y;
                }
            }
            
            if (E >= minSum && E <= maxSum) {
                int remain = E - minSum;
                StringBuilder sb = new StringBuilder();
                
                for (int i = 0; i < N; i++) {
                    if (selected[i] == 1) {
                        int give = list[i].x;
                        int canGive = list[i].y - list[i].x;

                        if (remain > 0) {
                            if (remain >= canGive) {
                                give += canGive;
                                remain -= canGive;
                            } else {
                                give += remain;
                                remain = 0;
                            }
                        }
                        sb.append(give).append(" ");
                    } else {
                        sb.append("0 ");
                    }
                }
                System.out.println(sb.toString());
                System.exit(0);
            }
            return;
        }

        for (int i = start; i < N; i++) {
            selected[i] = 1;
            choice(i + 1, count + 1);
            selected[i] = 0;
        }
    }
}
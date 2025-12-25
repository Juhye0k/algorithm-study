
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


class Node {
    int x;
    int y;
    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}


public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        // 입력 T
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            TreeMap<Integer, Integer> map = new TreeMap<>();

            // 연산의 개수 k
            int k = Integer.parseInt(br.readLine());
            for(int j = 0; j < k; j++) {
                st = new StringTokenizer(br.readLine());

                char s = st.nextToken().charAt(0);
                int num = Integer.parseInt(st.nextToken());
                if(s == 'I') {
                    map.put(num,map.getOrDefault(num,0)+1);
                }
                else {
                    if(map.isEmpty()) continue;
                    if(num==1) {
                        int temp = map.lastKey();
                        int cnt = map.get(temp);
                        if(cnt==1) map.remove(temp);
                        else map.put(temp,cnt-1);
                    }
                    else {
                        int temp = map.firstKey();
                        int cnt = map.get(temp);
                        if(cnt==1) map.remove(temp);
                        else map.put(temp,cnt-1);
                    }
                }
            }
            if(map.isEmpty())
                sb.append("EMPTY\n");
            else {
                sb.append(map.lastKey()+" "+ map.firstKey()+"\n");
            }
        }
        System.out.println(sb);

    }
}
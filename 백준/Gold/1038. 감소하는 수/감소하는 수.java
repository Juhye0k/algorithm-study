import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


public class Main {

    static boolean[] visited;
    static int N;
    static List<Long> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        if(N>1022){
            System.out.println(-1);
            return;
        }
        list = new LinkedList<>();
        for(int i=0; i<=9; i++)
            dfs(i);
        Collections.sort(list);
        System.out.println(list.get(N));
    }
    public static void dfs(long num) {
        list.add(num);


        long lastNumber = num%10;
        for(int i=0; i<lastNumber; i++) {
            dfs(num*10+i);
        }
    }
}

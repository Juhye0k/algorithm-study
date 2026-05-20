import java.util.*;
import java.io.*;
public class Main {
    static int N,K;
    static int[] ar;
    static ArrayList<Integer> answer = new ArrayList<>();
    public static void main(String[] args) throws IOException{
        // Please write your code here.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        choose(0);
    }
    public static void choose(int depth) {
        if(depth == N) {
            printAnswer();
            return;
        }
        for(int i=1; i<=K; i++) {
            answer.add(i);
            choose(depth+1);
            answer.remove(answer.size()-1);
        }
    }
    public static void printAnswer() {
        for(int i=0; i<answer.size(); i++) 
            System.out.print(answer.get(i) + " ");
        System.out.println();
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;





public class Main {

    static boolean [] ar;
    static boolean isSort;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Deque<Character> deque = new ArrayDeque<>();
        // 정수 N
        int N = Integer.parseInt(br.readLine());
        ar = new boolean[50000001];
        isSort = true;
        isPrime(50000000);
        for(int i=1; i<=N; i++) {
            // 현재 차례가 소수 차례인 경우
            if(ar[i]){
                // 마지막 문자가 B인경우 마지막 문자를 알파벳 S로 교체하고, 끝에 S 추가
                // 아니라면 끝에 S 추가하고 A를 뒤집음
                // 올바르게 정렬되어 있으면 맨 뒤에 추가, 역순이면 앞에 추가
                if(isSort) {
                    if(deque.peekLast() == 'B') {
                        deque.removeLast();
                        deque.addLast('S');
                        deque.addLast('S');
                    }
                    else {
                        deque.addLast('S');
                        isSort = false;
                    }
                }
                else {
                    if(deque.peekFirst() == 'B') {
                        deque.removeFirst();
                        deque.addFirst('S');
                        deque.addFirst('S');
                    }
                    else {
                        deque.addFirst('S');
                        isSort = true;
                    }
                }

            }
            else {
                if(isSort) deque.addLast('B');
                else deque.addFirst('B');
            }

        }
        int bCount = 0, sCount = 0;
        int size = deque.size();
        for(int i=0; i<size; i++) {
            char num = deque.removeFirst();
            if(num=='B') bCount++;
            else if(num=='S') sCount++;
        }
        System.out.println(bCount + " " + sCount);
    }
    public static void isPrime(int n) {
        // n == 1, 2면 바로 리턴
        Arrays.fill(ar, true);
        ar[0] = ar[1] = false;
        for(int i=2; i*i<=n; i++) {
            if(ar[i]) {
                for(int j=i*i; j<=n; j+=i)
                    ar[j] = false;
            }
        }
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Deque<Integer> deque = new ArrayDeque<>();
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            String ar[] = br.readLine().split(" ");
            String command = ar[0];
            switch (command) {
                case "push":
                    int n = Integer.parseInt(ar[1]);
                    deque.addLast(n);
                    break;
                case "pop":
                    if (deque.isEmpty())
                        System.out.println(-1);
                    else
                        System.out.println(deque.pollFirst());
                    break;
                case "size":
                    System.out.println(deque.size());
                    break;
                case "empty":
                    if (deque.isEmpty())
                        System.out.println(1);
                    else
                        System.out.println(0);
                    break;
                case "front":
                    if (deque.isEmpty())
                        System.out.println(-1);
                    else
                        System.out.println(deque.peekFirst());
                    break;
                case "back":
                    if (deque.isEmpty())
                        System.out.println(-1);
                    else
                        System.out.println(deque.peekLast());
                    break;


            }
        }


    }
}
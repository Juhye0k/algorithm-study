


import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;


public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        Queue<Integer> queue = new LinkedList<>();
        int answer = 0;
        for(int i=0; i<s.length(); i++) {
            if(s.charAt(i)=='B') queue.add(i);
            else if(s.charAt(i)=='C' && !queue.isEmpty()) {
                queue.poll();
                answer++;
            }
        }
        for(int i=0; i<s.length(); i++) {
            if(s.charAt(i)=='A'){
                while(!queue.isEmpty() && queue.peek() < i) {
                    queue.poll();
                }
                if(queue.isEmpty()){
                    continue;
                }
                answer++;
                queue.poll();
            }
        }
        System.out.println(answer);
    }
}
import java.io.*;
import java.util.*;


public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));

        // 다솜이의 방 번호
        String str = br.readLine();


        Queue<Integer> queue = new LinkedList<>();

        for(int i=0; i<str.length(); i++) {
            int num = str.charAt(i) - '0';
            queue.add(num);
        }

        int answer = 0;
        while(!queue.isEmpty()) {
            answer++;
            boolean[] visited = new boolean[10];
            int size = queue.size();

            for(int i=0; i<size; i++) {
                int temp = queue.poll();

                if(temp == 6 || temp == 9) {
                    if(!visited[6]) {
                        visited[6] = true;
                    }
                    else if(!visited[9]) {
                        visited[9] = true;
                    }
                    else {
                        queue.add(temp);
                    }
                }
                else {
                    if(!visited[temp]) {
                        visited[temp] = true;
                    }
                    else {
                        queue.add(temp);
                    }
                }
            }
        }
        System.out.println(answer);

    }


}
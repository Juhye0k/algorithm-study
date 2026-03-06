
import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node implements Comparable<Node>{
    int value;
    int index;
    public Node(int value, int index) {
        this.value = value;
        this.index = index;
    }
    @Override
    public int compareTo(Node o) {
        return this.value - o.value;
    }
}

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        Integer[] ar = new Integer[n];
        for(int i=0; i<n; i++) {
            ar[i] = Integer.parseInt(st.nextToken());
        }
        int index = n-1;
        int maxResult = ar[index];
        int result = 0;
        while(index>0) {
            if(maxResult<ar[index-1]) {
                maxResult = ar[index-1];
            }
            else {
                result+=maxResult-ar[index-1];
            }
            index--;
        }
        System.out.println(result);
    }
}

import java.io.*;
import java.sql.SQLOutput;
import java.util.*;





public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));

       int n = Integer.parseInt(br.readLine());

       long[] prefix = new long[n+1];
       Map<Integer, Integer> firstIndex = new HashMap<>();
       long maxSum = 0;
       int count = 0;
       StringTokenizer st = new StringTokenizer(br.readLine());
       for(int i = 0; i < n; i++) {
           int value = Integer.parseInt(st.nextToken());
           prefix[i+1] = prefix[i] + value;
           firstIndex.putIfAbsent(value, i);

           int j = firstIndex.get(value);
           long rangeSum = prefix[i+1] - prefix[j];
           if(rangeSum > maxSum) {
               maxSum = rangeSum;
               count = 1;
           } else if(rangeSum == maxSum) {
               count++;
           }

       }
        System.out.println(maxSum + " " + count);

    }
}
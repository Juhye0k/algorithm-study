

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        List<Integer> list = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 파의 개수
        int S = Integer.parseInt(st.nextToken());
        // 파닭의 수
        int C = Integer.parseInt(st.nextToken());
        for(int i=0; i<S; i++) {
            list.add(Integer.parseInt(br.readLine()));
        }
        long sum =0;
        for(int i=0; i<list.size(); i++) {
            sum+=list.get(i);
        }
        long max = 0;
        long left = 1;
        long right = 1000000000;
        long mid =0;
        while (left <= right) {
            // 잘라야 하는 파의 길이
            mid = (left+right)/2;
            // 자른 파의 수
            long result = 0;
            long temp = 0;
            for(int i:list) {
                // 파 하나당 몇개가 나오는지
                long count = i/mid;
                // 이때까지 나온 파의 수
                temp += count;
                // 각 파에서 나올 수 있는 cm
                result += mid*count;
            }
            // 이때까지 나온 파의 수로 충분히 만들 수 있다
            if(temp<C ) {
                right = mid-1;
            }
            else {
                max = mid;
                left = mid+1;
            }
        }
        System.out.println(sum-max*C);


    }
}

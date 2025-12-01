import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        List<Integer> list = new ArrayList<>();
        int sum =0;
        // 벚꽃나무 개수 N개
        int N = Integer.parseInt(br.readLine());
        // N개의 나무에 피어난 벚꽃의 개수
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            list.add(Integer.parseInt(st.nextToken()));
        }
        for(int i=0; i<N-3; i++) {
            for(int j=i+1; j<N-2; j++) {
                for(int k=j+1; k<N-1; k++) {
                    int first = 1,second=1,third=1,fourth=1;
                    for(int l=0; l<=i; l++){
                        first*=list.get(l);
                    }
                    for(int l=i+1; l<=j; l++){
                        second*=list.get(l);
                    }
                    for(int l=j+1; l<=k; l++){
                        third*=list.get(l);
                    }
                    for(int l=k+1; l<N; l++){
                        fourth*=list.get(l);
                    }
                    int lios = first+second+third+fourth;
                    sum = Math.max(sum,lios);
                }
            }
        }
        System.out.println(sum);
    }
}

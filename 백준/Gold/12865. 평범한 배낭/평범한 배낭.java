import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Product{
    int weight;
    int value;
    public Product(int weight, int value){
        this.weight = weight;
        this.value = value;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        // 물품의 수 N
        int N = Integer.parseInt(st.nextToken());
        // 버틸 수 있는 무게 K
        int K = Integer.parseInt(st.nextToken());

        int ar[][] = new int[N+1][K+1];
        // 물품의 수가 0일 때 초기화
        List<Product> list = new ArrayList<>();

        for(int i = 0; i<K+1; i++)
            ar[0][i] = 0;
        // 버틸 수 있는 무게가 0일 때 초기화
        for(int i = 0; i<N+1; i++)
            ar[i][0] = 0;

        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());  // 물건의 무게
            int v = Integer.parseInt(st.nextToken());  // 물건의 가치
            list.add(new Product(w,v));
        }
        for(int i = 1; i<K+1; i++){
            for(int j = 1; j<N+1; j++){
                Product p = list.get(j-1);
                // 만약 현재 물품의 무게가 버틸 수 있는 무게보다 크면 이전 정보
                if(p.weight>i)
                    ar[j][i] = ar[j-1][i];
                // 그게 아니면 max 값으로 이전 값, 해당 무게 넣을 때 값 이용
                else{
                    ar[j][i] = Math.max(ar[j-1][i],ar[j-1][i-p.weight]+p.value);
                }
            }
        }
        bw.write(String.valueOf(ar[N][K]));
        bw.flush();
        bw.close();
    }
}
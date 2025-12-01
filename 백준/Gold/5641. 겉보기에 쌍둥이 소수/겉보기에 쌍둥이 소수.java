import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {

    static BigInteger[] primeProducts = new BigInteger[8001];
    static boolean[] isPrime;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder(); // 출력 속도/메모리 효율을 위해 사용
        StringTokenizer st;

        initPrimeProducts(8000);

        while(true) {
            String line = br.readLine();
            if(line == null) break;
            
            st = new StringTokenizer(line);
            if(!st.hasMoreTokens()) break;

            int n = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            if(n==0 && t==0) break;

            BigInteger k = primeProducts[t];

            String kStr = k.toString();
            int len = kStr.length();
            
            if(len < n) {
                k = k.multiply(BigInteger.TEN.pow(n - len));
            }
            
            sb.append(k.subtract(BigInteger.ONE)).append('\n');
        }
        System.out.print(sb);
    }

    public static void initPrimeProducts(int max) {
        isPrime = new boolean[max + 1];
        
        // 에라토스테네스의 체
        for(int i=2; i<=max; i++) isPrime[i] = true;
        for(int i=2; i*i<=max; i++) {
            if(isPrime[i]) {
                for(int j=i*i; j<=max; j+=i) isPrime[j] = false;
            }
        }

        // 1부터 8000까지 t에 대한 소수 곱을 미리 계산해서 저장
        BigInteger currentVal = BigInteger.ONE;
        primeProducts[0] = BigInteger.ONE;
        primeProducts[1] = BigInteger.ONE;

        for(int i=2; i<=max; i++) {
            if(isPrime[i]) {
                // i가 소수면 곱해줌
                currentVal = currentVal.multiply(BigInteger.valueOf(i));
            }
            // 소수가 아니면 이전 값 그대로 유지
            primeProducts[i] = currentVal;
        }
    }
}
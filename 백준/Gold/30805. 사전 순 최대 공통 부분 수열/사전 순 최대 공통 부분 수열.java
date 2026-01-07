
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;




public class Main {



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        List<Integer> A = new ArrayList<>();
        List<Integer> B = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            A.add(Integer.parseInt(st.nextToken()));
        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++)
            B.add(Integer.parseInt(st.nextToken()));

        List<Integer> ans = new ArrayList<>();
        int i = 0, j = 0;
        while(true) {
            boolean found = false;
            for(int k=100; k>0; k--) {
                int ai = findNext(A,i,k);
                int bi = findNext(B,j,k);
                if(ai != -1 && bi!=-1){
                    ans.add(k);
                    i = ai+1;
                    j = bi+1;
                    found = true;
                    break;
                }
            }
            if(!found) break;
        }
        sb.append(ans.size()).append("\n");
        for(int k : ans)
            sb.append(k).append(" ");
        System.out.println(sb);


    }
    static int findNext(List<Integer> arr, int start, int value) {
        for(int i = start; i<arr.toArray().length; i++) {
            if(arr.get(i) == value)
                return i;

        }
        return -1;
    }
}

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String ar[]=br.readLine().split("\\-");
        int result=0;
        for(int i=0;i<ar.length;i++){
            String ar2[]= ar[i].split("\\+");
            int temp=0;
            for(int j=0;j<ar2.length;j++){
                temp+=Integer.parseInt(ar2[j]);
            }
            if(i==0)
                result+=temp;
            else
                result-=temp;
        }
        System.out.println(result);
    }
}

/*
괄호를 적절히 쳐서 최소로 하기 위해서는 어떻게 해야할까?
--> 플러스끼리 먼저 더한 다음, 마지막에 뺄셈을 해야함
1. -를 기준으로 split하여 플러스끼리 모아둔다.
2. 플러스끼리 모이면, 다시 +를 기준으로 split한다.
3. 해당 배열을 순차적으로 방문하면서 플러스를 계산한다.
4. 이후 뺄셈을 차례대로 계산한다.
 */

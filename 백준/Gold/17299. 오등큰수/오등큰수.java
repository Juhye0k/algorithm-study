import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N=Integer.parseInt(br.readLine());
        StringTokenizer st=new StringTokenizer(br.readLine());
        Map<Integer,Integer> map=new HashMap<>();
        int ar[]=new int[N];
        int result[]=new int[N];
        Arrays.fill(result,-1);
        Stack<Integer> stack=new Stack<>();
        // 스택에 넣고자 하는 숫자 배열에 넣어두기
        for(int i=0;i<N;i++)
        {
            ar[i]=Integer.parseInt(st.nextToken());
            if(!map.containsKey(ar[i])){
                map.put(ar[i],1);
            }
            else{
                map.put(ar[i],map.get(ar[i])+1);
            }
        }
        for(int i=0;i<N;i++){
            int num=ar[i];
            while(!stack.isEmpty()&&map.get(ar[stack.peek()])<map.get(num)){
                result[stack.pop()]=num;
            }
            stack.push(i);
        }
        for(int i:result)
            bw.append(i+" ");
        bw.flush();
        bw.close();
    }

}

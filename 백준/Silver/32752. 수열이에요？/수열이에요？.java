import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    public static void main(String[] args)  throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int isTrue=1;

        int N=Integer.parseInt(st.nextToken());
        int L=Integer.parseInt(st.nextToken());
        int R=Integer.parseInt(st.nextToken());
        List<Integer> oldArr=new ArrayList<>();
        List<Integer> newArr=new ArrayList<>();
        st=new StringTokenizer(br.readLine());


        for(int i=0;i<N;i++){
            oldArr.add(Integer.parseInt(st.nextToken()));
        }

        for(int i=0;i<R-L+1;i++){
            newArr.add(oldArr.get(i+L-1));
        }
        Collections.sort(newArr);

        for(int i=0;i<newArr.size();i++){
            oldArr.set(L-1+i,newArr.get(i));
        }


        for(int i=0;i<oldArr.size()-1;i++){
            if(oldArr.get(i)<=oldArr.get(i+1))
                continue;
            else
                isTrue=0;
        }
        System.out.println(isTrue);



    }
}

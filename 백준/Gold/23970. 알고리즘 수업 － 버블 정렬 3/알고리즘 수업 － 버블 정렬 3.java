
import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;



public class Main {

    static int matchCount;
    static boolean isCan=false;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        int[] brr = new int[N];
        for(int i=0;i<N;i++)
            arr[i] = Integer.parseInt(st.nextToken());
        st =  new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++)
            brr[i] = Integer.parseInt(st.nextToken());
        matchCount = 0;
        for(int i = 0; i < N; i++){
            if(arr[i]==brr[i])
                matchCount++;
        }
        if(matchCount==N) {
            System.out.println(1);
            return;
        }
        bubble_sort(arr,brr);
        if(matchCount==N)
            System.out.println(1);
        else
            System.out.println(0);


    }
    public static void bubble_sort(int[] ar, int[] br) {
        for(int i=0; i<ar.length-1; i++) {
            for(int j=0; j<ar.length-1-i; j++) {
                if(ar[j]>ar[j+1]) {
                    if(ar[j]==br[j])
                        matchCount--;
                    if(ar[j+1]==br[j+1])
                        matchCount--;
                    int temp = ar[j];
                    ar[j] = ar[j+1];
                    ar[j+1] = temp;
                    if(ar[j]==br[j])
                        matchCount++;
                    if(ar[j+1]==br[j+1])
                        matchCount++;
                    if(matchCount==N){
                        isCan=true;
                        return;
                    }
                }
            }
        }
    }
}
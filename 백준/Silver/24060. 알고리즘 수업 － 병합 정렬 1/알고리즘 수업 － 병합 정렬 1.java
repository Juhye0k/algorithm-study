import java.io.*;
import java.util.*;

public class Main {
    static int result;
    static int K;
    static int cnt=0;
    public static void merge(int ar[],int left,int mid, int right){
        int n1=mid-left+1,n2=right-mid;            // 배열의 크기를 설정
        int [] L=new int[n1], R=new int[n2];       // 배열 선언
        System.arraycopy(ar,left,L,0,n1);    // 배열 값 복사
        System.arraycopy(ar,mid+1,R,0,n2);
        int i=0, j=0, k=left;                      // 초기 인덱스 설정
        while(i<n1 && j<n2){
            if (L[i] <= R[j]) {
                ar[k] = L[i++];
            } else {
                ar[k] = R[j++];
            }
            cnt++;
            if (cnt == K) result = ar[k]; // K번째 저장된 값 찾기
            k++;
        }
        while(i<n1){
            ar[k] = L[i++];
            cnt++;
            if (cnt == K) result = ar[k];
            k++;
        }
        while(j<n2){
            ar[k] = R[j++];
            cnt++;
            if (cnt == K) result = ar[k];
            k++;
        }
    }
    public static void merge_sort(int ar[],int left,int right){
        if(left<right){
            int mid=(left+right)/2;               // 배열을 절반으로 나눔
            merge_sort(ar,left,mid);              // 왼쪽 부분 merge_sort 정렬
            merge_sort(ar,mid+1,right);      // 오른쪽 부분 merge_sort 정렬
            merge(ar,left,mid,right);            // 정렬된 두 부분을 merge 함수로 합침
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        result=-1;
        // 배열 A의 크기 N
        int N=Integer.parseInt(st.nextToken());
        // 저장 횟수 K
        K=Integer.parseInt(st.nextToken());
        int ar[]=new int[N];
        // 서로 다른 배열 A의 원소들
        st=new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            ar[i]=Integer.parseInt(st.nextToken());
        }
        merge_sort(ar,0,N-1);
        System.out.println(result);
    }
}

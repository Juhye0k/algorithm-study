import java.util.*;
class Solution {
    static List<Integer> list;
    public int solution(int n, int k) {
        int answer = 0;
        // 1. n을 k 진수로 변환한다.
        // 2. 조건에 맞는 소수가 몇 개인지 알아본다.
        list = new ArrayList<>();
        while(n!=0) {
            list.add(n % k);
            n = n / k;
        }
        Collections.reverse(list);
    
        List<Long> arr = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<list.size(); i++) {
            if(list.get(i)==0){
                if(sb.length()>0){
                    arr.add(Long.parseLong(sb.toString()));
                    sb.setLength(0);
                }
            } 
            else {sb.append(list.get(i));}
        }       
        if(sb.length()>0) {
            arr.add(Long.parseLong(sb.toString()));
        }
        for(long number : arr) {
            if(isPrime(number)) answer++;
        }
        return answer;
    }
  
    public static boolean isPrime(long number) {
        if(number<2) 
            return false;
        for(long i=2; i*i<=number; i++) {
            if(number%i==0) return false;
        }
        return true;
    }
}
import java.util.*;
class Solution {
    public int solution(String arr[]) {
        int answer = -1;
        int n = arr.length;
        int[] nums = new int[n]; // 숫자를 저장하기 위한 배열
        char[] ops = new char[n-1]; // 연산자를 저장하기 위한 배열
        
        int ni = 0, oi = 0;
        for(int i=0; i<arr.length; i++) {
            if(i%2==0) {
                nums[ni++] = Integer.parseInt(arr[i]);
            }
            else {
                ops[oi++] = arr[i].charAt(0);
            }
        }
        int[][] dpMax = new int[n][n];
        int[][] dpMin = new int[n][n];
        
        for(int i=0; i<n; i++) {
            Arrays.fill(dpMax[i], Integer.MIN_VALUE);
            Arrays.fill(dpMin[i], Integer.MAX_VALUE);
            dpMax[i][i] = nums[i];
            dpMin[i][i] = nums[i];
        }
        for(int len=1; len<n; len++) {
            for(int i=0; i+len<n; i++) {
                int j=i+len;
                
                for(int k=i; k<j; k++) {
                    char op = ops[k];
                    
                    if(op=='+') {
                        dpMax[i][j] = Math.max(dpMax[i][j], dpMax[i][k] +dpMax[k+1][j]);
                        dpMin[i][j] = Math.min(dpMin[i][j],dpMin[i][k]+dpMin[k+1][j]);
                    }
                    else {
                            dpMax[i][j] = Math.max(dpMax[i][j], dpMax[i][k]-dpMin[k+1][j]);
                            dpMin[i][j] = Math.min(dpMin[i][j],dpMin[i][k]-dpMax[k+1][j]);
                    }
                }
            }
        }
        return dpMax[0][n - 1];
    }
}
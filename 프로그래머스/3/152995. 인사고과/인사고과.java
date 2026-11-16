import java.util.*;

class Solution {
    public int solution(int[][] scores) {
        int answer = 0;
        
        int wonhoA = scores[0][0];
        int wonhoB = scores[0][1];
        int wonhoSum = wonhoA + wonhoB;
        
        for(int[] score : scores) {
            if(score[0] > wonhoA && score[1]> wonhoB) 
                return -1;
        }
        Arrays.sort(scores,(a,b)->{
            if(a[0] == b[0]) {
                return Integer.compare(a[1],b[1]);
            }
            return Integer.compare(b[0],a[0]);
        });
        
        int maxB = -1;
        int rank = 1;
        
        for(int[] score : scores) {
            if(score[1] < maxB)
                continue;
            maxB = Math.max(maxB, score[1]);
            
            if(score[0] + score[1] > wonhoSum)
                rank++;
        }
        
        
        return rank;
    }
}
class Solution {
    
    public int[] solution(int[] arr, int[][] queries) {
        int[] answer = {};
        answer = new int[arr.length];
        for(int i=0; i<queries.length;i++){
            int j = queries[i][0];
            int k = queries[i][1];
            
            int temp = arr[j];
            arr[j] = arr[k];
            arr[k] = temp;
        }
        for(int i=0; i<arr.length; i++)
            answer[i]=arr[i];
        return answer;
        
    }
}
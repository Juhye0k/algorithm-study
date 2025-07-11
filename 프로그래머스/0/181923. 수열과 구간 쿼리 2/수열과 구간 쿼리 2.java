class Solution {
    public int[] solution(int[] arr, int[][] queries) {
        int[] answer = {};
        answer = new int[queries.length];
        for(int i=0; i<queries.length; i++){
            int start = queries[i][0];
            int end = queries[i][1];
            int num = queries[i][2];
            int result=1000000;
            boolean istrue=false;
            for(int j=start; j<=end; j++){
                if(arr[j]>num){
                    istrue=true;
                    result=Math.min(result,arr[j]);
                }
            }
            if(!istrue)
                    result=-1;
            answer[i]=result;
        }
        return answer;
    }
}
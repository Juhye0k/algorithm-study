class Solution {
    public String solution(String str1, String str2) {
        String answer = "";
        int b=0;
        
        for(int i=0;i<str1.length();i++)
        {
            answer+=str1.substring(i,i+1);
            answer+=str2.substring(i,i+1);
        }
        return answer;
    }
}
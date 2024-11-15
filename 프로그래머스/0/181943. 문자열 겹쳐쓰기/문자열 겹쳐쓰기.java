class Solution {
    public String solution(String my_string, String overwrite_string, int s) {
        String answer = "";
        StringBuilder sb=new StringBuilder(my_string);
        for(int i=0;i<overwrite_string.length();i++)
        {
            sb.setCharAt(s,overwrite_string.charAt(i));
            s++;
        }
        answer=sb.toString();
        return answer;
    }
}
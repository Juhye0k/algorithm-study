import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String str = br.readLine();
        String[] arr = str.split("\\+", -1);
        
        if (arr.length != 2) {
            bw.write("No Money");
        } else {
            if (arr[0].isEmpty() || arr[1].isEmpty()) {
                bw.write("No Money");
            } else if (isValidPositiveInteger(arr[0]) && isValidPositiveInteger(arr[1]) && arr[0].equals(arr[1])) {
                bw.write("CUTE");
            } else {
                bw.write("No Money");
            }
        }
        
        bw.flush();
        bw.close();
        br.close();
    }
    
    // 0으로 시작하지 않는 양의 정수인지 확인하는 함수
    private static boolean isValidPositiveInteger(String s) {
        // 빈 문자열이거나 0으로 시작하는 경우 제외
        if (s.isEmpty() || s.charAt(0) == '0') {
            return false;
        }
        
        // 모든 문자가 숫자인지 확인
        for (char c : s.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        
        return true;
    }
}
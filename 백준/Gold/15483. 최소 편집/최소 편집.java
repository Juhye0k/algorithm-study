import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        // 문자열 A
        String str1 = br.readLine();
        // 문자열 B
        String str2 = br.readLine();

        int ar[][] = new int[str1.length()+1][str2.length()+1]; // 정보를 담을 배열 생성

        for(int i = 0; i<str2.length()+1; i++){  // S 가 0일때 --> 삽입만
            ar[0][i] = i;
        }
        for(int i = 0; i<str1.length()+1; i++){  // 삭제
            ar[i][0] = i;
        }

        for(int i = 1; i<= str1.length(); i++){
            for(int j = 1; j<= str2.length(); j++){
                ar[i][j] = Math.min(Math.min(ar[i-1][j]+1, ar[i][j-1]+1),ar[i-1][j-1]+(str1.charAt(i-1)==str2.charAt(j-1)?0:1));
            }
        }
        bw.write(ar[str1.length()][str2.length()]+"");
        bw.flush();
        bw.close();
        br.close();
    }
}
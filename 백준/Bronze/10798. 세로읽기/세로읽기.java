import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[][] board = new char[5][15];

        for (int i = 0; i < 5; i++) {
            String s = br.readLine();
            for (int j = 0; j < s.length(); j++) {
                board[i][j] = s.charAt(j);
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int j = 0; j < 15; j++) { 
            for (int i = 0; i < 5; i++) { 

                if (board[i][j] != '\0') {
                    sb.append(board[i][j]);
                }
            }
        }

        System.out.println(sb.toString());
    }
}
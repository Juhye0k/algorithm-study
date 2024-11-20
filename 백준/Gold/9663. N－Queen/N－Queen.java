
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {

    public static int count;
    public static void main(String[] args) throws IOException {
       BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
       int N=Integer.parseInt(br.readLine());
       int ar[][]=new int[N][N];
       count=0;
       placeQueen(ar,0);
       System.out.println(count);
    }
    public static void placeQueen(int ar[][],int row)
    {
        if(row==ar.length)
        {
            count++;
            return;
        }
        for(int col=0;col<ar.length;col++)
        {
            if(canPlace(ar,row,col)){
                ar[row][col]=1;
                placeQueen(ar,row+1);
                ar[row][col]=0;
            }
        }

    }

    public static boolean canPlace(int ar[][],int row,int col) {
        for(int i=0;i<row;i++)
        {
            if(ar[i][col]==1){
                return false;
            }
        }
        for (int i=row,j=col; i >= 0 && j >= 0; i--, j--) {
            if (ar[i][j] == 1)
                return false;
        }
        for (int i=row,j=col; i >= 0 && j < ar.length; i--, j++) {
            if (ar[i][j] == 1)
                return false;
        }
        return true;
    }

}

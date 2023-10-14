
import java.util.Scanner;



class Main {
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String word=sc.nextLine();
		int wordCount;
		
		if(word.charAt(0)==' ')  // 첫 문자 공백일 때 예외 처리
			wordCount=0;
		else
			wordCount=1;
		
		for(int i=0;i<word.length()-1;i++) {     
			
			if(word.charAt(i)==' '&&word.charAt(i+1)!=' ')    // 단어 구별 방법
				wordCount+=1;
		}
		System.out.println(wordCount);
		
		
	}
}

// 
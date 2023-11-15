import java.util.Scanner;
class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String word=sc.nextLine();
		int ar[]= new int[26];
		for(int i=0;i<word.length();i++)
		{
			if(word.charAt(i)>=65&&word.charAt(i)<=90 )
				ar[word.charAt(i)-65]++;
			else{
				ar[word.charAt(i)-97]++;
			}
		}
		int max=-1;
		char ch='?';
		for(int i=0;i<26;i++)
		{
			if(ar[i]>max)
			{
				max=ar[i];
				ch=(char)(i+65);
			}
			else if(ar[i]==max)
				ch='?';
		}
		System.out.print(ch);
		
		
		
		
	}
	
}
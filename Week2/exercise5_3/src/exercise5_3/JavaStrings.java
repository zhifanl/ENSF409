package exercise5_3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.StringTokenizer;

public class JavaStrings {
	public static int count(String myString) {
	Pattern myPattern=Pattern.compile("e",Pattern.CASE_INSENSITIVE);
	Matcher myMatcher=myPattern.matcher(myString);
	int counter=0;
	while(myMatcher.find()) {
		counter++;
	}
	return counter;
	}
	
	public static   void findTokens(String myString) {
		StringTokenizer tokens=new StringTokenizer(myString);
		int counter=tokens.countTokens();
		String[]a = new String[100];
		int i=0;
		while(tokens.hasMoreTokens()) {
			a[i]=tokens.nextToken();
			System.out.println(a[i]);
			i++;

		}
		System.out.println(counter);
	}
	
	public static void main(String[] args) {
		String myString="ENSF 409 Principles of Software Development.";
		int a=count(myString);
		findTokens(myString);
		System.out.println(a);
		StringBuilder myString1=new StringBuilder(myString);
		myString1.insert(8, ":");
		System.out.println(myString1);
	}
}
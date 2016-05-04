//Idea driven from http://www.personal.kent.edu/~rmuhamma/Algorithms/algorithm.html

package stringmatchingalgos;

public class KMP {

	public static int kmpMatch(String text,	String pattern){
	    int n = text.length();
		int m = pattern.length();
	    int fail[] = computeFail(pattern);
		int i=0;
		int j=0;
		while (i < n){
			if (pattern.charAt(j) == text.charAt(i)){
				if (j == m - 1){
					return i - m + 1; // match
				}
				i++;
				j++;
			}else if (j > 0){
				j = fail[j-1];
			}
			else
				i++;
	    } 
	    return -1; // no match
	} // end of kmpMatch
		
	public static int[] computeFail(String pattern){
	    int fail[] = new int[pattern.length()]; 
		fail[0] = 0;
		int m = pattern.length();
	    int j = 0;
		int i = 1;
		while (i < m) {
			if(pattern.charAt(j) == pattern.charAt(i)){   //j+1 chars match
				fail[i] = j + 1;
				i++;
				j++;
			}else if (j > 0){ // j follows matching prefix
				j = fail[j-1];
			}
			else {     // no match
				fail[i] = 0;
				i++;
			}
		}
	    return fail;
	} // end of computeFail

	public static void main(String args[]){
		if (args.length != 2){
			System.out.println("Usage: java KMP <text> <pattern>");
			return;
		}
		System.out.println("Text: " + args[0]);
		System.out.println("Pattern: " + args[1]);
		int posn = kmpMatch(args[0], args[1]);
		
		if (posn == -1)
			System.out.println("Pattern not found");
		else 
			System.out.println("Pattern starts at position: "+ posn);
	}
}

//Idea driven from http://www.personal.kent.edu/~rmuhamma/Algorithms/algorithm.html

package stringmatchingalgos;

public class BruteForce {

	public static int brute(String text, String pattern) { 
		int n = text.length();    // n is length of text
		int m = pattern.length(); // m is length of pattern
		int j;
		for(int i=0; i <= (n-m); i++){
			j = 0;
			while ((j < m) && (text.charAt(i+j) == pattern.charAt(j)))
				j++;
			if (j == m)
			return i;   // match at i
		}
		return -1;   // no match
	} // end of brute

	public static void main(String args[]){
		if (args.length != 2) {
			System.out.println("Usage: java BruteForce  <text> <pattern>");
			System.exit(0);
		}
		System.out.println("Text: " + args[0]);
		System.out.println("Pattern: " + args[1]);
		
		int posn = brute(args[0], args[1]);
		if (posn == -1)
			System.out.println("Pattern not found");
		else 
			System.out.println("Pattern starts at position: "  + posn);
	}
}

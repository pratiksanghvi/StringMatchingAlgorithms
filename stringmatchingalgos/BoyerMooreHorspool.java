//Idea driven from http://www.personal.kent.edu/~rmuhamma/Algorithms/algorithm.html

package stringmatchingalgos;

public class BoyerMooreHorspool {

	public static int bmMatch(String text, String pattern) {
	    int last[] = buildLast(pattern);
		int n = text.length();
	    int m = pattern.length();   
		int i = m-1;
	    if (i > n-1)
			return -1; 
		int j = m-1;
	    do {
			if (pattern.charAt(j) == text.charAt(i)){
				if (j == 0)
					return i; 
				else { // looking-glass technique
					i--;
					j--;
				}
			}
			else { // character jump technique
				int lo = last[text.charAt(i-(m-1))];  //Change from BM algo
				i = i + (m-1) - Math.min(j, 1+lo);
				j = m - 1;
			}
		} while (i <= n-1);
		return -1; // no match
	} // end of bmMatch()
		

	public static int[] buildLast(String pattern){/* Return array storing index of last occurrence of each ASCII char in pattern. */
		int last[] = new int[128]; // ASCII char set
		for(int i=0; i < 128; i++){
			last[i] = -1; // initialize array
		}
		//Change from BM algo in next line
		for (int i = 0; i < pattern.length()-1; i++){
			last[pattern.charAt(i)] = i; 
		}
		return last;
	  } // end of buildLast()

	public static void main(String args[]){
		if (args.length != 2) {
			System.out.println("Usage: java BoyerMooreHorspool  <text> <pattern>");
			System.exit(0);
		}
		System.out.println("Text: " + args[0]);
		System.out.println("Pattern: " + args[1]);
		int posn = bmMatch(args[0], args[1]);
		
		if (posn == -1)
			System.out.println("Pattern not found");
		else 
			System.out.println("Pattern starts at position: " + posn);
	}
	
}

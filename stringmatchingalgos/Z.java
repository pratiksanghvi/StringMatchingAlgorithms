package stringmatchingalgos;

public class Z {

	static void search(String text, String pattern)
	{
	    
	    String concat = pattern + "$" + text;
	    int l = concat.length();
	 
	    // Construct Z array
	    int Z[] = new int[l];
	    getZarr(concat, Z);
	 
	    //  now looping through Z array for matching condition
	    for (int i = 0; i < l; ++i)
	    {
	        // if Z[i] (matched region) is equal to pattern
	        // length  we got the pattern
	        if (Z[i] == pattern.length())
	            System.out.println("Pattern found at index: " + (i - pattern.length() -1) );
	    }
	}
	 
	//  Fills Z array for given string str[]
	static void getZarr(String str, int Z[])
	{
	    int n = str.length();
	    int L, R, k;
	 
	    // [L,R] make a window which matches with prefix of s
	    L = R = 0;
	    for (int i = 1; i < n; ++i)
	    {
	        // if i>R nothing matches so we will calculate.
	        // Z[i] using naive way.
	        if (i > R)
	        {
	            L = R = i;
	 
	            while (R<n && str.charAt(R-L) == str.charAt(R))
	                R++;
	            Z[i] = R-L;
	            R--;
	        }
	        else
	        {
	            // k = i-L so k corresponds to number which
	            // matches in [L,R] interval.
	            k = i-L;
	 
	            if (Z[k] < R-i+1){
	                 Z[i] = Z[k];
	            }
	 	        else
	            {
	                //  else start from R  and check manually
	                L = i;
	                while (R<n && str.charAt(R-L) == str.charAt(R))
	                    R++;
	                Z[i] = R-L;
	                R--;
	            }
	        }
	    }
	}
	
	public static void main(String[] args) {
		
		if (args.length != 2){
			System.out.println("Usage: java Z <text> <pattern>");
			return;
		}
		System.out.println("Text: " + args[0]);
		System.out.println("Pattern: " + args[1]);
		search(args[0], args[1]);

	}

}
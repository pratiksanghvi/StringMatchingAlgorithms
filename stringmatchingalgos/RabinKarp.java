//Idea driven from http://www.sanfoundry.com/java-program-rabin-karp-algorithm/

package stringmatchingalgos;

import java.io.IOException;
import java.util.Random;
import java.math.BigInteger;
 
public class RabinKarp 
{
    /** String Pattern **/
    private String pattern; 
    /** pattern hash value **/    
    private long patternHash;    
    /** pattern length **/
    private int M;  
    /** Large prime **/         
    private long Q; 
    /** radix **/         
    private int R;   
    /** R^(M-1) % Q **/        
    private long RM;          
 
    
    public RabinKarp(String txt, String pattern) 
    {
        this.pattern = pattern;      
        R = 256;
        M = pattern.length();
        Q = longRandomPrime();
        
        RM = 1;
        for (int i = 1; i <= M-1; i++)
           RM = (R * RM) % Q;
        patternHash = hash(pattern, M);
        int position = search(txt);
        if (position == -1)
            System.out.println("\nNo Match\n");
        else
            System.out.println("Pattern found at position : "+ position);
    } 
    /** Compute hash **/
    private long hash(String key, int M)
    { 
        long h = 0; 
        for (int j = 0; j < M; j++) 
            h = (R * h + key.charAt(j)) % Q; 
        return h; 
    } 
    /** Funtion check **/
    private boolean check(String txt, int i) 
    {
        for (int j = 0; j < M; j++) 
            if (pattern.charAt(j) != txt.charAt(i + j)) 
                return false; 
        return true;
    }
    /** Funtion to check for exact match**/
    private int search(String text) 
    {
        int N = text.length(); 
        if (N < M) return N;
        long textHash = hash(text, M); 
        /** check for match at start **/
        if ((patternHash == textHash) && check(text, 0))
            return 0;
        /** check for hash match. if hash match then check for exact match**/
        for (int i = M; i < N; i++) 
        {
            // Remove leading digit, add trailing digit, check for match. 
            textHash = (textHash + Q - RM * text.charAt(i - M) % Q) % Q; 
            textHash = (textHash * R + text.charAt(i)) % Q; 
            // match
            int offset = i - M + 1;
            if ((patternHash == textHash) && check(text, offset))
                return offset;
        }
        /** no match **/
        return -1;
    }
    /** generate a random 31 bit prime **/
    private static long longRandomPrime() 
    {
        BigInteger prime = BigInteger.probablePrime(31, new Random());
        return prime.longValue();
    }
    /** Main Function **/
    public static void main(String[] args) throws IOException
    {    
    	if (args.length != 2){
			System.out.println("Usage: java RabinKarp <text> <pattern>");
			return;
		}
		System.out.println("Text: " + args[0]);
		System.out.println("Pattern: " + args[1]);
		new RabinKarp(args[0], args[1]);        
    }
}
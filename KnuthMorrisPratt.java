

    public class KnuthMorrisPratt {
        
         public static int[] createLps(char [] pat){
     
            int[] lps = new int[pat.length];
            lps[0] = 0;
            int len = 0;
            int i=1;
            int m = pat.length;
            //System.out.print(pat);
     
            while(i < m){
     
                if (pat[i] == pat[len]){
                    len++;
                    lps[i] = len;
                   // System.out.println(" i = "+i+ " and len = "+len+" and lps contains "+lps[i]);
                    i++;
                }else{
     
                    if(len != 0){
                        len = lps[len-1];
                    }else{
                        lps[i] = len;
                        //System.out.println("Else i = "+i+ " and len = "+len+" and lps contains "+lps[i]);
                        i++;
                    }
     
                }
            }
     
            return lps;
        }
    	public int strStr(final String haystack, final String needle) {
    	    
    	   if(haystack.length() == 0 || needle.length() == 0)
                return -1;
     
            char[] str = haystack.toCharArray();
            char[] pat = needle.toCharArray();
            int [] lps = createLps(pat);
     
            int n = haystack.length();
            int m = needle.length();
            int ind=0;
     
            for(int i=0;i<n;i++){
     
                if(str[i] == pat[ind]){
                    ind++;
                }else{
     
                    if(ind == 0)
                        ind = 0;
                    else{
                        ind = lps[ind-1];
                        i--;
                    }
     
                }
     
                if(ind == m)
                    return i-m + 1;
            }
     
            return -1;
    	        
    	   
    	}
    }



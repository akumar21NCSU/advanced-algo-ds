class TrieNode{
	public int wordNumber;
	public int startIndex;
	public int endIndex;
	
	int markEnd;
	TrieNode children[];

	public TrieNode(){
		markEnd=0;
		children = new TrieNode[26];
		for(int i=0;i<26;i++)
			children[i] = null;
	}

}

class Driver{

	public static String[] words;	

	public static void insert(TrieNode cur, String word,int wnum,int sind, int eind){

		char c = word.charAt(sind);
		if(cur.children[c-'a'] == null){
			// empty children array spot
			TrieNode newNode = new TrieNode();
			newNode.markEnd=1;
			newNode.wordNumber=wnum;
			newNode.startIndex = sind;		
			newNode.endIndex = eind;		

			cur.children[c-'a']  = newNode;
			return;
		}else{
			int curStart = cur.children[c-'a'].startIndex;
			int curEnd =  cur.children[c - 'a'].endIndex;
			int curWord = cur.children[c-'a'].wordNumber;		

			int i=curStart;
			for(i=curStart;i<=curEnd;i++){
				if(word.charAt(i) != words[curWord].charAt(i))
					break;	
			}
			if(i>curEnd){
				insert(cur.children[c-'a'],word,wnum,i,eind);
			}else{
				// do not match				
				TrieNode node1 = new TrieNode();
				TrieNode node2 = new TrieNode();

				node1.wordNumber = cur.children[c-'a'].wordNumber;
				node1.startIndex = i;
				node1.endIndex= curEnd;
				node1.markEnd = cur.children[c-'a'].markEnd;

				node2.wordNumber = wnum;
				node2.startIndex = i;
				node2.endIndex = eind;
				node2.markEnd = 1;

				cur.children[c-'a'].markEnd=0;
				cur.children[c-'a'].endIndex =i-1;

				char c1 = words[curWord].charAt(i);
				char c2 = word.charAt(i);
				//System.out.println("original mark = "+ cur.children[c-'a'].markEnd);
				//System.out.println("char1 "+c1+" and mark = "+node1.markEnd+" char2 = "+c2+" and mark= "+node2.markEnd);

				cur.children[c-'a'].children[c1-'a'] = node1;
				cur.children[c-'a'].children[c2-'a'] = node2;

				return;
			}

		}

	}


	public static boolean searchUtil(TrieNode cur, String word, int sind, int eind){
		
		char c = word.charAt(sind);
		//System.out.println("Searching char = "+c);
		if(cur.children[c-'a'] == null){
			return false;
		}else{
			int curStart = cur.children[c-'a'].startIndex;
			int curEnd =  cur.children[c - 'a'].endIndex;
			int curWord = cur.children[c-'a'].wordNumber;		

			int i=curStart;
			for(i=curStart;i<=curEnd;i++){
				if(word.charAt(i) != words[curWord].charAt(i))
					break;	
			}
			if(i <= curEnd)
				return false;

			if(i > eind){
				if(cur.children[c-'a'].markEnd == 1){
					return true;
				}else{
					return false;
				}
			}else{
				System.out.println("recurring for "+ word.charAt(i)+" where i="+i);
				return searchUtil(cur.children[c-'a'],word,i,eind);
			}

		}

	}

	public static boolean search(TrieNode root, String word){

		return searchUtil(root,word,0,word.length()-1);
	}


	public static void main(String ...args){
		words = new String[]{"see","bark","seal","stop","seem","barn","barf"};

		TrieNode root = new TrieNode();
		for(int i=0;i< words.length;i++)
			insert(root,words[i],i,0,(words[i].length() -1));
 
		System.out.println(search(root,"barf"));

	}

}

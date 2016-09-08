class TrieNode{
	public int value;
	public TrieNode[] children;

	public TrieNode(){
		value=0;
		children = new TrieNode[26];
		for(int i=0;i<26;i++)
			children[i] = null;
	}

	public void insert(String word){
		word = word.toLowerCase();
		char carr[] = word.toCharArray();
		TrieNode cur = this;
		for(int i=0;i<carr.length;i++){
			if(cur.children[carr[i]-'a'] != null){
				cur = cur.children[carr[i]-'a'];
			}else{
				TrieNode newNode = new TrieNode();
				cur.children[carr[i] -'a'] = newNode;
				cur = newNode;
			}
			if(i == carr.length-1){
				cur.value=1;
			}
		}

	}

	public boolean search(String word){
		word = word.toLowerCase();
		char carr[] = word.toCharArray();		
		
		TrieNode cur = this;
		int i=0;
		for(i=0;i<carr.length;i++){
		
			if(cur.children[carr[i]-'a'] != null){
				cur = cur.children[carr[i]-'a'];
			}else{
				break;
			}			
		}	
		if(i == carr.length && cur.value==1)
			return true;

		return false;		

	}
	
	public boolean containsChild(){
		
		for(int i=0;i<26;i++)
			if(children[i] != null)
				return true;

		return false;

	}	

	public boolean hasAnotherChild(){

		int count=0;

		for(int i=0;i<26;i++)
			if(children[i] != null)
				count++;


		if(count>1)
			return true;
		
		return false;

	}

	public boolean recurDel(TrieNode cur,String word,int index){

		if(index == word.length()-1){
			return true;
		}

		boolean del=false;
		if(index!= word.length()-1){
			del = recurDel(cur.children[word.charAt(index) - 'a'],word,index+1);
			//System.out.println("For char - "+word.charAt(index) +" cur ="+cur);
		}

		if(del){

			cur.children[word.charAt(index) - 'a'].children[word.charAt(index+1) -'a'] = null;

			if(cur.children[word.charAt(index) - 'a'].value == 1)			
				return false;

			if(cur.children[word.charAt(index) - 'a'].hasAnotherChild())
				return false;			

			return true;
		}

		return false;

	}

	public int delete(String word){

		word = word.toLowerCase();
		char carr[] = word.toCharArray();		
		
		TrieNode cur = this;
		int i=0;
		for(i=0;i<carr.length;i++){
		
			if(cur.children[carr[i]-'a'] != null){
				cur = cur.children[carr[i]-'a'];
			}else{
				break;
			}			
		}	
		if(i < carr.length) 
			return -1;

		if(i == carr.length && cur.value!=1)
			return -1;

		boolean hasChild = cur.containsChild();
		if(hasChild){
			cur.value =0;
			return 0;
		}
		
		recurDel(this,word,0);
		return 0;
	}

}



class Driver{
	
	public static void main(String ...args){
		
		TrieNode root = new TrieNode();
		root.insert("they");
		root.insert("the");

		System.out.println(root.search("they"));
		System.out.println(root.search("the"));
		root.delete("they");
		System.out.println(root.search("they"));
		System.out.println(root.search("the"));
		
		/*root.insert("apple");
		root.insert("their");
		root.insert("app");
		root.insert("the");
		System.out.println(root.search("appll"));
		System.out.println(root.search("the"));*/
		

	}

}

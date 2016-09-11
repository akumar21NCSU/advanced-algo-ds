package mst;

public class UnionWithRankAndCompression {
	
	public static int find(int parent[], int v){
		
		int i=v;
		while(parent[i] != i)
			i=parent[i];
		
		parent[v] =i;
		return i;
	}
	
	public static boolean cyclic(int edges[],int rank[],int parent[],int n){
		
		
		for(int i=0;i<edges.length;i=i+2){
			int x = find(parent,edges[i]);
			int y = find(parent,edges[i+1]);
			
			System.out.println("For vertex "+edges[i]+" parent-"+x+" and vertex "+edges[i+1]+ " parent-"+ y);
			if(x == y)
				return true;
			
			if(rank[x] > rank[y])
				parent[y] = x;
			else if(rank[x] < rank[y])
				parent[x] = y;
			else{
				parent[y] = x;
				rank[x] = rank[x]+1;
			}
			
		}
		
		return false;
	}

	public static void main(String[] args) {
		
		int n=6;
		int rank[] = new int[n];
		int parent[] = new int[n];
		
		for(int i=0;i<n;i++){
			rank[i]= 0;
			parent[i]= i;
		}
		
		int arr[] = {0,1,1,2,2,3,3,4,4,5};
		
		System.out.println(cyclic(arr,rank,parent,n));
		

	}

}

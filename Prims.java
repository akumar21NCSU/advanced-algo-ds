package mst;
import graph.Edge;
import java.util.*;

public class Prims {

	public static int findMin(int key[], boolean inMst[], int parent[], int V){
		int min = 99999;
		int index = -1;
		for(int i=0;i<V;i++){
			if(inMst[i] == false && key[i] < min){
				min = key[i];
				index = i;				
			}			
		}
		
		if(index ==0)
			parent[index] =-1;
		
		return index;
	}
	
	public static void updateKeys(int[][] graph, boolean inMst[], int key[],int parent[],int current,int V){
		
		for(int i=0;i<V;i++){
			int temp = graph[current][i];
			if(current != i){
				if(key[i] > graph[current][i] && graph[current][i] != 0){
					key[i] = graph[current][i];
					parent[i] = current;
				}
			}			
		}		
	}
	
	public static void main(String[] args) {
		
		int V=5;
		int graph[][] = new int[][]{
				{0, 2, 0, 6, 0},
                {2, 0, 3, 8, 5},
                {0, 3, 0, 0, 7},
                {6, 8, 0, 0, 9},
                {0, 5, 7, 9, 0}
               };		
		int key[] = new int[V];
		boolean inMst[] = new boolean[V];
		int parent[] = new int[V];
		
		for(int i=0;i<V;i++){
			key[i]=99999;
			inMst[i] = false;
			parent[i] = -1;
		}
		key[0] = 0;
		//inMst[0] = true;
		int count =0;
		int prev =-1;
		while(count != V){		
			
			int current = findMin(key,inMst,parent,V);			
			//parent[current] = prev;
			inMst[current]= true;
			updateKeys(graph,inMst,key,parent,current,V);
			count++;
			prev=current;
			
		}
		
		for(int i=1;i<V;i++){
			System.out.println("For vertex "+i+" - parent ="+parent[i]+ " and w-"+key[i]);
		}
			
		
	}

}

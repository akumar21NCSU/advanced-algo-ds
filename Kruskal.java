package mst;
import java.util.ArrayList;
import java.util.Collections;

import graph.Edge;


public class Kruskal {
	
public static int find(int parent[], int v){
		
		int i=v;
		while(parent[i] != i)
			i=parent[i];
		
		parent[v] =i;
		return i;
	}

	public static void main(String[] args) {
		
		int n=5;	// no of edges in graph
		int v=4; // no of vertices in graph
		
		ArrayList<Edge> edges = new ArrayList<Edge>();
		
		edges.add(new Edge(0,1,10));
		edges.add(new Edge(0,2,6));
		edges.add(new Edge(0,3,5));
		edges.add(new Edge(1,3,15));
		edges.add(new Edge(2,3,4));
		
		/*edges.add(new Edge(2,5,4));
		edges.add(new Edge(2,8,2));
		edges.add(new Edge(3,4,9));
		edges.add(new Edge(3,5,14));
		edges.add(new Edge(4,5,10));
		
		edges.add(new Edge(5,6,2));
		edges.add(new Edge(6,7,1));
		edges.add(new Edge(6,8,6));
		edges.add(new Edge(7,8,7));*/

		Collections.sort(edges);
		int parent[] = new int[v];
		int rank[] = new int[v];
		
		for(int i=0;i<v;i++){
			parent[i] = i;
			rank[i] = 0;
		}
		
		/*for(Edge e: edges){
			parent[e.getDest()] = e.getSrc();
			rank[e.getSrc()] = 1;
		}*/
		int count=0;
		
		ArrayList<Edge> result = new ArrayList<Edge>();
		
		for(Edge e: edges){
			if(count== v-1)
				break;
			
			int x = find(parent, e.getSrc());
			int y = find(parent, e.getDest());
			
			if(x==y)
				continue;
			
			else if(rank[x] > rank[y]){
				parent[y]=x;
			}
			else if(rank[y] > rank[x]){
				parent[x]= y;
			}else{
				parent[y] =x;
				rank[x] = rank[x]+1;
			}
			result.add(e);
		}
		for(Edge e: result){
			System.out.println("Edge  "+e.getSrc() +"-"+e.getDest()+" , w= "+e.getWeight());
		}
	}

}

package maxflow;

import java.util.LinkedList;

public class FordFulkerson {

	public static final int V = 6;
	
	public static boolean augPathBFS(int[][] rgraph, int[]parent, int s, int t){
		
		LinkedList<Integer> queue = new LinkedList<Integer>();
		queue.add(s);
		
		boolean visited[] = new boolean[V];
		parent[s] = -1;
		visited[s] = true;
		
		while(!queue.isEmpty()){
			
			int u = queue.poll();
			
			for(int i=0;i<V;i++){
				
				if(visited[i]  == false && rgraph[u][i] > 0){
					visited[i] = true;
					queue.add(i);
					parent[i] = u;
				}				
			}			
		}
		return visited[t]==true;
		
	}
	
	public static int ford(int[][] graph, int s, int t){
		
		int[][] rgraph = new int[6][6];
		
		for(int i=0;i<V;i++)
			for(int j=0;j<V;j++)
				rgraph[i][j] = graph[i][j];
		
		int parent[] = new int[V];
		int max_flow=0;
		
		while(augPathBFS(rgraph,parent,s,t)){
			
			int path_cap = Integer.MAX_VALUE;
			int u = t;
			while(parent[u] > -1){
				int prev = parent[u];
				path_cap = Math.min(path_cap,rgraph[prev][u]);
				u = prev;
			}
			u=t;
			
			while(parent[u] > -1){
				int prev = parent[u];
				rgraph[prev][u] = rgraph[prev][u] - path_cap;
				rgraph[u][prev] = rgraph[u][prev] + path_cap;
				u=prev;
			}
			max_flow+= path_cap;
		}
		return max_flow;	
	}
	
	public static void main(String[] args) {

		int graph[][] =new int[][] { {0, 16, 13, 0, 0, 0},
            {0, 0, 10, 12, 0, 0},
            {0, 4, 0, 0, 14, 0},
            {0, 0, 9, 0, 0, 20},
            {0, 0, 0, 7, 0, 4},
            {0, 0, 0, 0, 0, 0}
          };
          
          System.out.println(ford(graph,0,5));		
	}

}

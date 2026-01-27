package Graph;

import java.util.ArrayList;

public class TopologicalSort {
      static class Edge{
        int src;
        int dest;

        public Edge(int s,int d){
            this.src = s;
            this.dest = d;
        }
    }

    public static void createGraph(ArrayList<Edge> graph[]){

        for(int i = 0;i<graph.length;i++){
            graph[i] = new ArrayList<Edge>();
        }

        graph[0].add(new Edge(0,2)); 

        graph[1].add(new Edge(1,0));

        graph[2].add(new Edge(2,3));  
        
        graph[3].add(new Edge(3,0));  //when you comment out this line means this is not direct any one this last;

    }

    public static void main(String[] args) {
        int V = 6;
     
        ArrayList<Edge> graph[]  = new ArrayList[V];
        createGraph(graph);
    }
}

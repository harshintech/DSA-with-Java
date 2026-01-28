package Graph;

import java.util.ArrayList;

public class BellmanFord {

    
    static class Edge{
        int src;
        int dest;
        int wt;

        public Edge(int s,int d,int wt){
            this.src = s;
            this.dest = d;
            this.wt = wt;
        }
    }

       public static void createGraph(ArrayList<Edge> graph[]){

        for(int i = 0;i<graph.length;i++){
            graph[i] = new ArrayList<Edge>();
        }

        graph[0].add(new Edge(0,1,2)); 
        graph[0].add(new Edge(0,2,4)); 


        graph[1].add(new Edge(1,2,-4));

        graph[2].add(new Edge(2,3,2));

        graph[3].add(new Edge(3,4,4));

        graph[4].add(new Edge(4,1,-1));
    }

    public static void bellmanFord(ArrayList<Edge> graph[],int src,int V){
        int dist[] = new int[V];
        for(int i = 0;i<V;i++){
            if(i != src){
                dist[i] = Integer.MAX_VALUE;
            }
        }

        /*
        Repeat V-1 times:
            For every edge:
                Relax it 
        */

        for(int k = 0;k<V-1;k++){//O(V)    // 1st loop: repeat V-1 times
            //O(E)
            for(int i=0;i<V;i++){  // 2nd loop: go through every vertex
                for(int j = 0;j<graph[i].size();j++){  // 3rd loop: go through all edges of i
                    Edge e = graph[i].get(j);
                    int u = e.src;
                    int v = e.dest;
                    if(dist[u] != Integer.MAX_VALUE && dist[u] + e.wt < dist[v]){
                        dist[v] = dist[u] + e.wt;
                    }
                }
            }
        }

        for(int i = 0;i<dist.length;i++){
            System.out.print(dist[i] + " ");
        }

        System.out.println();
    }

    public static void main(String[] args) {

        int V = 5;
        ArrayList<Edge> graph[] = new ArrayList[V];
        createGraph(graph);
        bellmanFord(graph, 0, V);
        
    }
    
}

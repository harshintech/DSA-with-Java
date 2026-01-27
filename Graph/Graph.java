package Graph;

import java.util.ArrayList;

public class Graph {

    static class Edge{
        int src;
        int dest;

        public Edge(int s,int d){
            this.src = s;
            this.dest = d;
        }
    }

    public static void createGraph(ArrayList<Edge> graph[]){

        //first time graph look like this
        //graph -> [ null, null, null, null ] so change this.
        //all is fill with null when you put new ArrayList<Edge> here so crash; 
        for(int i = 0;i<graph.length;i++){
            graph[i] = new ArrayList<Edge>();
        }

        graph[0].add(new Edge(0,2));

        graph[1].add(new Edge(1,2));
        graph[1].add(new Edge(1,3));

        graph[2].add(new Edge(2,0));
        graph[2].add(new Edge(2,1));
        graph[2].add(new Edge(2,3));

        graph[3].add(new Edge(3,1));
        graph[3].add(new Edge(3,2));



    }
    public static void main(String[] args) {
        int V = 4;

        ArrayList<Edge> graph[]  = new ArrayList[V];
        createGraph(graph);

        //print 2's neighbours
        for(int i = 0;i<graph[2].size();i++){
            Edge e = graph[2].get(i);
            System.out.print(e.dest + " ");
        }
    }
}

/* 
Yes, they are different:

```java
ArrayList<Edge> graph = new ArrayList<>();
```

vs

```java
ArrayList<Edge> graph[] = new ArrayList[V];
```

### 1️⃣ `ArrayList<Edge> graph = new ArrayList<>();`

* `graph` is **one single ArrayList**
* It can store many `Edge` objects in one list
* Used when you just need a simple list

Example:

```java
ArrayList<Edge> graph = new ArrayList<>();
graph.add(new Edge(0, 1));
graph.add(new Edge(1, 2));
```

This is like:

```
graph -> [Edge, Edge, Edge]
```

---

### 2️⃣ `ArrayList<Edge> graph[] = new ArrayList[V];`

* `graph` is an **array of ArrayLists**
* Size is `V`
* Each index holds its own `ArrayList<Edge>`
* Used for **graph (adjacency list)** representation

You must initialize each index:

```java
ArrayList<Edge>[] graph = new ArrayList[V];
for (int i = 0; i < V; i++) {
    graph[i] = new ArrayList<>();
}
```

Now:

```java
graph[0].add(new Edge(0, 1));
graph[1].add(new Edge(1, 2));
```

This looks like:

```
graph[0] -> [Edge, Edge]
graph[1] -> [Edge]
graph[2] -> []
...
```

So:

* First = **one list**
* Second = **many lists (one per vertex)**

*/
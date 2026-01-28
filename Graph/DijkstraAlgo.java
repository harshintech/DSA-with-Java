package Graph;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class DijkstraAlgo {

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


        graph[1].add(new Edge(1,3,7));
        graph[1].add(new Edge(1,2,1));  

        graph[2].add(new Edge(2,4,3));

        graph[3].add(new Edge(3,5,1));

        graph[4].add(new Edge(4,3,2));
        graph[4].add(new Edge(4,5,5));
    }

    public static class Pair implements Comparable<Pair>{
        int node;
        int dist;

        public Pair(int n ,int d){
            this.node = n;
            this.dist = d;
        }


        //This is func automatic run in priority queue and this.dist - p2 possitive means this distance is big and sort it.
        //Explanation At Bottom
        @Override
        public int compareTo(Pair p2){ 
            return this.dist - p2.dist; //ascending sorting
        }
    }

    //O(E + ElogV)
    public static void dijkstra(ArrayList<Edge> graph[],int src,int V){
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        int dist[] = new int[V];
        for(int i = 0;i<V;i++){
            if(i!=src){
                dist[i] = Integer.MAX_VALUE;
            }
        }
        boolean vis[] = new boolean[V];

        pq.add(new Pair(0, 0));

        //bfs
        while(!pq.isEmpty()){
            Pair curr = pq.remove();

            if(!vis[curr.node]){
                vis[curr.node] = true;

                for(int i = 0;i<graph[curr.node].size();i++){
                    Edge e = graph[curr.node].get(i);
                    int u = e.src;
                    int v = e.dest;
                    if(dist[u] + e.wt < dist[v]){ //relaxation
                        dist[v] = dist[u] + e.wt;
                        pq.add(new Pair(v, dist[v]));
                    }
                }
            }
        }

        for(int i = 0;i<V;i++){
            System.out.print(dist[i]+" ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        int V = 6;

        ArrayList<Edge> graph[]  = new ArrayList[V];
        createGraph(graph);

        dijkstra(graph, 0, V);
    }
}


/*

I totally get why this is confusing. It feels like "magic" because you never actually type `p1.compareTo(p2)` in your code, yet the sorting happens anyway.

Here is the breakdown of the "How" and the "Why."

### 1. The "Why": Why do we need this?

You are likely using this `Pair` class inside a **PriorityQueue** (common for Dijkstra's Algorithm).

* A `PriorityQueue` needs to know which `Pair` is "smaller" so it can keep the one with the shortest distance at the top.
* Without the `compareTo` method, Java would look at two `Pair` objects and say, "I don't know how to compare these. Should I look at the `node` or the `dist`?"

### 2. The "How": Who calls it?

You don't call it, **Java's internal sorting logic calls it.**

When you do `pq.add(new Pair(1, 10))`, the `PriorityQueue` internally does something like this:

```java
if (currentPair.compareTo(newPair) > 0) {
    // move things around to keep it sorted
}

```

It’s like a contract. By saying `implements Comparable`, you are promising Java: "If you ever need to compare two of my Pairs, use this specific logic."

---

### 3. Logic Breakdown: `this.dist - p2.dist`

This is a classic shortcut in Java for sorting. The function must return an **integer**:

| Result | Meaning |
| --- | --- |
| **Negative Value** | `this` is smaller than `p2` (Shortest distance gets priority). |
| **Zero** | Both are equal. |
| **Positive Value** | `this` is larger than `p2`. |

---

### 4. Simple Example (Dry Run)

Imagine you have two Pairs in your PriorityQueue:

* **Pair A:** `node: 3, dist: 5`
* **Pair B:** `node: 1, dist: 2`

When the PriorityQueue compares them:

1. It calls `A.compareTo(B)`.
2. It calculates `A.dist (5) - B.dist (2) = 3`.
3. Since **3** is positive, Java knows **A** is "heavier" (larger) than **B**.
4. Result: **B** (distance 2) stays at the top of the queue because it is the "shorter" path.

### Summary

* **`implements Comparable`**: Tells Java this class *can* be sorted.
* **`@Override compareTo`**: Defines *the rule* for sorting (by distance).
* **The "Magic"**: Data structures like `PriorityQueue` or `Arrays.sort()` call this function automatically behind the scenes.

**Would you like to see how this `Pair` class is used inside a Dijkstra's Algorithm function?**


-------------------------------------------------------------------------------------------------------------------------


I understand. You mean what if you are **only** storing a single `Integer` (like just the distance or just the node) in your code instead of a `Pair` object.

If you only have an `Integer`, you don't need the `Pair` class at all because Java already knows how to compare numbers.

### 1. How Java handles Integers

Java’s `Integer` class already has a `compareTo` function built inside it.

* If you have `5` and `10`, Java automatically knows .
* If you use a `PriorityQueue<Integer>`, it will automatically put the smallest number at the top without you writing any extra code.

### 2. The Problem with "Integer Only" in Graphs

If you are doing a graph algorithm (like Dijkstra), using **only** an `Integer` is like having a key but not knowing which door it opens.

* You store `5` in the queue (the distance).
* The queue gives you `5` back.
* **Now you are stuck:** You know the shortest distance is `5`, but you have no idea **which node** is 5 units away.

---

### 3. What to do now?

If you don't want to use a `Pair` class but you need to store both the **Node** and the **Distance**, you have to use an **Adjacency Matrix** or a **Distance Array** to keep track.

Here is the "Straightforward" way to fix this without a `Pair` class:

**Use a 2D Array (The "Quick" way):**
Instead of `Pair`, use `int[]`.

```java
// Tell the queue to look at index [1] (the distance) to sort
PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);

// Add [node, distance]
pq.add(new int[]{0, 0}); 

```

**Why this works:**

1. You didn't create a `Pair` class.
2. You used a "Lambda" `(a, b) -> a[1] - b[1]` which acts like the `compareTo` function you showed me.
3. It keeps the node and distance "glued" together in a simple array.

---

### Summary

If you only have one `Integer`, Java handles the sorting for you. But if you have **two** pieces of data (Node and Distance) and you refuse to use `Pair`, the **`int[]` array** is your best friend.

**Would you like me to show you how to write the Dijkstra loop using this `int[]` method so you can delete the `Pair` class entirely?**
 */

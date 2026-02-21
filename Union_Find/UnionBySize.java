package Union_Find;
import java.util.*;


//Union By Size (More Intuitive)
 class DisjointSet2{
        List<Integer> rank = new ArrayList<>();
        List<Integer> parent = new ArrayList<>();
        List<Integer> size = new ArrayList<>();

        public DisjointSet2(int n){
            for(int i = 0;i<=n;i++){
                rank.add(0);
                parent.add(i);
                size.add(1);
            }
        }

        public int findUPar(int node){
            if(node == parent.get(node)){
                return node;
            }

            int ulp = findUPar(parent.get(node));
            // parent.set(index, value);
            parent.set(node,ulp);
            return parent.get(node);
        }

        //ulp -> Ultimate Parent
        public void unionBySize(int u,int v){
            int ulp_u = findUPar(u);
            int ulp_v = findUPar(v);
            if(ulp_u == ulp_v) return;
            if(size.get(ulp_u) < size.get(ulp_v)){
                parent.set(ulp_u,ulp_v);
                size.set(ulp_v,size.get(ulp_v) + size.get(ulp_u));

            }else{
                parent.set(ulp_v,ulp_u);
                size.set(ulp_u,size.get(ulp_u) + size.get(ulp_v));
            }
        }
    }


public class UnionBySize {
    public static void main(String[] args) {
        DisjointSet2 ds = new DisjointSet2(7);
        ds.unionBySize(1,2);
        ds.unionBySize(2,3);
        ds.unionBySize(4,5);
        ds.unionBySize(6,7);
        ds.unionBySize(5,6);

        if(ds.findUPar(3) == ds.findUPar(7)){
            System.out.println("Same");
        }else{
            System.out.println("Not Same");
        }

        ds.unionBySize(3,7);
        if(ds.findUPar(3) == ds.findUPar(7)){
            System.out.println("Same");
        }else{
            System.out.println("Not Same");
        }
        

    }
}

/*
Here are the two states of the `parent` and `rank` lists for your code execution.

### 1. Initial State

This is immediately after `DisjointSet ds = new DisjointSet(7);` is called. Every node is its own parent (Ultimate Parent) and all ranks are 0.

| Node (Index) | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 |
|      ---     | - | - | - | - | - | - | - | - |
| **Parent**   | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 |
| **Rank**     | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |

---

### 2. Final State (After all Unions and Finds)

This is the state after the final `findUPar(3) == findUPar(7)` check. Note that **Path Compression** has updated several parents to point directly to the ultimate boss (**4**).

| Node (Index) | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 |
|      ---     | - | - | - | - | - | - | - | - |
| **Parent**   | 0 | 4 | 1 | 4 | 4 | 4 | 4 | 4 |
| **Rank**     | 0 | 1 | 0 | 0 | 2 | 0 | 1 | 0 |

**Final Console Output:**

```text
Not Same
Same

```

Would you like me to show you how to implement **Union by Size** next, which is often considered even more intuitive than Rank? */
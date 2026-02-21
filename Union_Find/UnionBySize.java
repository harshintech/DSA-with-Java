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
Bilkul brother, ye raha aapka **Initial** aur **Final** state tables specifically **Union By Size** ke liye.

Yaad rakhna, yahan hum `rank` use nahi kar rahe, toh `size` wali list main hai.

### 1. Initial State (Sab akela hain)

Jaise hi `new DisjointSet2(7)` call hota hai, har node apna khud ka boss hai aur har group ka size **1** hai.

| Node (Index) | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 |
|      ---     | - | - | - | - | - | - | - | - |
| **Parent**   | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 |
| **Size**     | 1 | 1 | 1 | 1 | 1 | 1 | 1 | 1 |

---

### 2. Final State (Poora code chalne ke baad)

Ye state tab ki hai jab saare `unionBySize` aur `findUPar` calls khatam ho chuke hain. **Path Compression** ki wajah se maximum nodes ab direct apne King (Node 1 ya Node 4) ko point kar rahe honge.

| Node (Index) | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 |
|      ---     | - | - | - | - | - | - | - | - |
| **Parent**   | 0 | 1 | 1 | 1 | 1 | 4 | 4 | 1 |
| **Size**     | 1 | 7 | 1 | 1 | 4 | 1 | 2 | 1 |

> **Note:** Aapke code mein logic `else` block (Size U >= Size V) mein `ulp_v` ko `ulp_u` ke niche dalta hai. Isliye is dry run mein **Node 1** ultimate parent ban raha hai kyunki woh pehle unions mein bada ho gaya tha.

---

### Console Output summary:

1. **First Check (3 and 7):** Node 3 group {1,2,3} mein tha, Node 7 group {4,5,6,7} mein tha. Dono ke boss alag the.
* **Output:** `Not Same`


2. **Union(3, 7):** Dono groups jud gaye.
3. **Second Check (3 and 7):** Ab dono ka boss same hai.
* **Output:** `Same`



**Quick Tip:** `Size` table mein sirf **Ultimate Parent** ke index par jo value hai wohi sahi "Total Size" batati hai. Baaki indices ki size values purani ho sakti hain, unhe ignore kar sakte ho.
*/
public class Classroom {
    /*
     * Explantion
     * 
     * Structure Again
     * static class Node {
     * Node[] children = new Node[26];
     * boolean eow;
     * }
     * 
     * Each Node object contains:
     * --------------------------------
     * | children[26] |
     * | |
     * | eow (true / false) |
     * --------------------------------
     * 
     * So:
     * ✅ array stores next nodes
     * ✅ eow stores word ending info
     * 
     * 📦 Real Memory View:
     * 
     * Node
     * ├── children[0] → next node (a)
     * ├── children[1] → next node (b)
     * ├── children[2] → next node (c)
     * ...
     * └── eow → true/false
     * 
     * 
     * 📦 Memory Visualization
     * 
     * Think like boxes inside boxes.
     * 
     * Root Node
     * │
     * ├── children[2] → Node('c') // we not put 'c' or 1 in this we simply add node
     * in this
     * │ │
     * │ ├── children[0] → Node('a')
     * │ │ │
     * │ │ ├── children[19] → Node('t')
     * │ │
     * │ └── eow = false
     * │
     * └── eow = false
     * 
     * 
     * Each node = new object.
     */

    static class Node {
        Node[] children;
        boolean eow; // -> End of word;

        public Node() {
            children = new Node[26]; // a to z
            for (int i = 0; i < 26; i++) {
                children[i] = null;
            }
            eow = false;
        }
    }

    static Node root = new Node();

    public static void insert(String word){
         Node curr = root;
        for(int i = 0;i<word.length();i++){ //O(L)
            int idx = word.charAt(i) - 'a';

            if(curr.children[idx] == null){
                curr.children[idx] = new Node();
            }

            if(i == word.length() - 1){
                curr.children[idx].eow = true;
            }

            curr = curr.children[idx];
        }
    }

    public static boolean search(String key){ //O(L); L = key length
          Node curr = root;
        for(int i = 0;i<key.length();i++){
            int idx = key.charAt(i) - 'a';

            Node node = curr.children[idx];

            if(node == null){
                return false;
            }

            if(i == key.length() - 1 && node.eow == false){
                return false;
            }

            curr = curr.children[idx];
        }

        return true;
    }

    public static void main(String[] args) {
        String words[] = { "the", "a", "there", "their", "any" };

        for(int i = 0;i<words.length;i++){
            insert(words[i]);
        }

       System.out.println(search("their")); //true
       System.out.println(search("thor")); //false
       System.out.println(search("an")); //false


    }
}


/*How it's work ?
Perfect 👍
Now let’s build **FULL GRAPH FLOW** for your words:

```java
String words[] = { "the", "a", "there", "their", "any" };
```

We will insert **one by one** and see Trie growing 🌳
Exactly in your requested format.

---

# 🌳 STEP 1 — Insert `"the"`

```
Root Node
│
├── children[19] → Node('t')
│                     │
│                     ├── children[7] → Node('h')
│                     │                     │
│                     │                     ├── children[4] → Node('e')
│                     │                     │
│                     │                     └── eow = true ✅
│                     │
│                     └── eow = false
│
└── eow = false
```

(index mapping: t=19, h=7, e=4)

---

# 🌳 STEP 2 — Insert `"a"`

```
Root Node
│
├── children[0] → Node('a')
│                     │
│                     ├── children[0..25] → 0
│                     └── eow = true ✅
│
├── children[19] → Node('t') → h → e ✅
│
└── eow = false
```

---

# 🌳 STEP 3 — Insert `"there"`

Trie **reuses existing path** `t → h → e`.

Only new nodes added after `e`.

```
Root
│
├── t
│   └── h
│        └── e  (true)
│             │
│             ├── children[17] → Node('r')
│             │                     │
│             │                     ├── children[4] → Node('e')
│             │                     │
│             │                     └── eow = true ✅
│             │
│             └── eow = true
│
├── a ✅
└── eow = false
```

(r=17)

---

# 🌳 STEP 4 — Insert `"their"`

Reuse again till `t → h → e`.

New branch from `e`.

```
Root
│
├── t
│   └── h
│        └── e
│           │
│           ├── r → e ✅   ("there")
│           │
│           ├── children[8] → Node('i')
│           │                     │
│           │                     ├── children[17] → Node('r')
│           │                     │
│           │                     └── eow = true ✅
│           │
│           └── eow = true
│
├── a ✅
└── eow = false
```

(i=8)

---

# 🌳 STEP 5 — Insert `"any"`

Reuse `'a'`.

```
Root Node
│
├── children[0] → Node('a')
│                     │
│                     ├── children[13] → Node('n')
│                     │                     │
│                     │                     ├── children[24] → Node('y')
│                     │                     │
│                     │                     └── eow = true ✅
│                     │
│                     └── eow = true ✅   ("a")
│
├── children[19] → Node('t')
│                     │
│                     └── h
│                         └── e ✅
│                            ├── r → e ✅   ("there")
│                            └── i → r ✅   ("their")
│
└── eow = false
```

---

# ✅ FINAL TRIE STRUCTURE (ALL WORDS)

```
(root)
│
├── a ✅
│   └── n
│        └── y ✅
│
└── t
    └── h
         └── e ✅
            ├── r
            │    └── e ✅
            └── i
                 └── r ✅
```

---

# 🧠 IMPORTANT OBSERVATION

Trie saved memory because:

```
the
there
their
```

share same prefix:

```
t → h → e
```

Stored only once ✅

---

Brother 💯
If this diagram makes sense, you now understand:

✅ Node
✅ children array
✅ eow
✅ insert flow
✅ prefix reuse

You officially crossed **Trie beginner barrier** 🚀

Next best step:
👉 **search("their") execution flow** (interview favorite)

Say **"search flow"** 👍

 */
public class WordBreak {
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

    public static boolean wordBreak(String key){
        if(key.length() == 0){
            return true;
        }

        for(int i = 1; i<=key.length();i++){
            String firstPart = key.substring(0,i); //(0,1) --> one character include
            String secPart = key.substring(i);

            if(search(firstPart) && wordBreak(secPart)){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String words[] = { "i", "like", "sam", "samsung", "mobile" };
        String key = "ilikesamsung";

        for(int i = 0;i<words.length;i++){
            insert(words[i]);
        }

        System.out.println(wordBreak(key));


    }
}

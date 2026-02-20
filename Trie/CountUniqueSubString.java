public class CountUniqueSubString {

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

    public static int countNode(Node root){
        if(root == null){
            return 0;
        }

        int count = 0;
        for(int i = 0;i<26;i++){
            if(root.children[i] != null){
                count  = count + countNode(root.children[i]);
            }
        }

        return count + 1;
    }

    public static void main(String[] args) {
        String str = "ababa";
        String str2 = "apple";


        for(int i = 0;i<str.length();i++){
            String suffix = str.substring(i); // from i to last index;
            insert(suffix);
        }

        System.out.println(countNode(root));

    }
}



//apple

/*
SUFFIX :
apple
pple
ple
le
e
""

a -> ap -> app -> appl -> apple
p -> pp -> ppl -> pple
p(alreday done not consider) -> pl -> ple
l -> le
e
""

Total = 15;

            (root)
        /   |   |   \
       a    p   l    e
       |   / \
       p  p   l
       |  |    |
       p  l    e
       |  |
       l  e
       |
       e

Simple word for count all unique sub string first find string suffix and for all suffix find prefix then you get all unique substirng and for this only count trie node 
Trie Node count = all unique sub string;
*/

                   
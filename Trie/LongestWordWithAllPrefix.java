
public class LongestWordWithAllPrefix {

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

    public static String ans = "";

    public static void longestWord(Node root,StringBuilder temp){
        if(root == null){
            return;
        }

        for(int i = 0;i<26;i++){
            if(root.children[i] != null && root.children[i].eow == true){
                temp.append((char)(i + 'a'));
                if(temp.length() > ans.length()){
                    ans = temp.toString();
                }
                longestWord(root.children[i],temp);

                temp.deleteCharAt(temp.length()-1); //backtrack
            }
        }
    }

    public static void main(String[] args) {
      String words[] = {"a","banana","app","appl","ap","apply","apple"};

      for(int i = 0;i<words.length;i++){
        insert(words[i]);
      }
      
      longestWord(root,new StringBuilder());
      System.out.println(ans);

    }
}



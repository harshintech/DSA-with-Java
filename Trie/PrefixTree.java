public class PrefixTree {

    /*
    ============================================================
                    🌳 PREFIX TREE (TRIE)
    ============================================================

    Each Node stores:
    -------------------------
    | children[26]          |
    | eow (end of word)     |
    -------------------------

    children[i] → next character node
    eow = true  → word ends here

    Example word: "cat"

    root
      |
      c
      |
      a
      |
      t (eow = true)

    ============================================================
    */

    // ---------------- NODE STRUCTURE ----------------
    static class Node {
        Node[] children = new Node[26];
        boolean eow;

        Node() {
            for (int i = 0; i < 26; i++) {
                children[i] = null;
            }
            eow = false;
        }
    }

    // ROOT NODE
    static Node root = new Node();


    /*
    ============================================================
                        INSERT FUNCTION
    ============================================================

    Insert word character by character.

    Steps:
    1. Start from root
    2. Create node if missing
    3. Move forward
    4. Mark last node eow = true
    */

    public static void insert(String word) {

        Node curr = root;

        for (int i = 0; i < word.length(); i++) {

            int idx = word.charAt(i) - 'a';

            if (curr.children[idx] == null) {
                curr.children[idx] = new Node();
            }

            curr = curr.children[idx];
        }

        curr.eow = true;
    }


    /*
    ============================================================
                        SEARCH WORD
    ============================================================

    Check full word exists.

    Example:
    insert("apple")

    search("apple") → true
    search("app")   → false
    */

    public static boolean search(String key) {

        Node curr = root;

        for (int i = 0; i < key.length(); i++) {

            int idx = key.charAt(i) - 'a';

            if (curr.children[idx] == null)
                return false;

            curr = curr.children[idx];
        }

        return curr.eow;
    }


    /*
    ============================================================
                        PREFIX SEARCH
    ============================================================

    Only prefix required.
    No eow check needed.

    Example:
    insert("apple")

    startsWith("app") → true
    */

    public static boolean startsWith(String prefix) {

        Node curr = root;

        for (int i = 0; i < prefix.length(); i++) {

            int idx = prefix.charAt(i) - 'a';

            if (curr.children[idx] == null)
                return false;

            curr = curr.children[idx];
        }

        return true;
    }


    /*
    ============================================================
                            MAIN METHOD
    ============================================================
    */

    public static void main(String[] args) {

        String words[] = {
                "the",
                "a",
                "there",
                "their",
                "any"
        };

        // INSERT WORDS
        for (String word : words) {
            insert(word);
        }

        System.out.println(search("their")); // true
        System.out.println(search("thor"));  // false
        System.out.println(search("an"));    // false
        System.out.println(search("th"));    // false


        //prefix search
        System.out.println(startsWith("th")); // true
        System.out.println(startsWith("xy")); // false
    }
}

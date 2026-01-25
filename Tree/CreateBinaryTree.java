package Tree;

import java.util.LinkedList;
import java.util.Queue;

class CreateBinaryTree {

    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    static class BinaryTree {

        static int idx = -1;

        public Node buildTree(int nodes[]) {
            idx++;
            if (nodes[idx] == -1) {
                return null;
            }

            Node newNode = new Node(nodes[idx]);
            newNode.left = buildTree(nodes);
            newNode.right = buildTree(nodes);

            return newNode;
        }

        public void preOrder(Node root) {
            if (root == null) {
                return;
            }
            System.out.print(root.data + " ");
            preOrder(root.left);
            preOrder(root.right);

        }

        public void inOrder(Node root) {
            if (root == null) {
                return;
            }
            inOrder(root.left);
            System.out.print(root.data + " ");
            inOrder(root.right);
        }

        public void postOrder(Node root) {
            if (root == null) {
                return;
            }
            postOrder(root.left);
            postOrder(root.right);
            System.out.print(root.data + " ");
        }

        public void levelOrder(Node root) {
            if (root == null) {
                return;
            }
            Queue<Node> q = new LinkedList<>();
            q.add(root);
            q.add(null);

            while (!q.isEmpty()) {
                Node currNode = q.remove();
                if (currNode == null) {
                    System.out.println();
                    if (q.isEmpty()) {
                        break;
                    } else {
                        q.add(null);
                    }
                } else {
                    System.out.print(currNode.data + " ");
                    if (currNode.left != null)
                        q.add(currNode.left);
                    if (currNode.right != null)
                        q.add(currNode.right);
                }

            }
        }

        public int countOfNodes(Node root) {
            if (root == null) {
                return 0;
            }
            int leftNodes = countOfNodes(root.left);
            int rightNodes = countOfNodes(root.right);
            // add 1 for root itself count
            return leftNodes + rightNodes + 1;
        }

        public int sumOfNodes(Node root) {
            if (root == null) {
                return 0;
            }
            int leftNodes = sumOfNodes(root.left);
            int rightNodes = sumOfNodes(root.right);
            // add 1 for root itself count
            return leftNodes + rightNodes + root.data;
        }

        public int height(Node root) {

            if (root == null) {
                return 0;
            }
            int leftHeight = height(root.left);
            int rightHeight = height(root.right);

            int myHeight = Math.max(leftHeight, rightHeight) + 1;

            return myHeight;
        }

        // Approach 1: O(N^2)
        public int diameter(Node root) {
            if (root == null) {
                return 0;
            }
            int diam1 = diameter(root.left);
            int diam2 = diameter(root.right);

            // here we count height every time so we touch every node 2 time that why
            // O(n^2);
            int diam3 = height(root.left) + height(root.right) + 1;

            return Math.max(diam3, Math.max(diam1, diam2));
        }

        // Approach2: O(N)
        static class TreeInfo {
            int ht;
            int diam;

            TreeInfo(int ht, int diam) {
                this.ht = ht;
                this.diam = diam;
            }
        }

        public static TreeInfo diameter2(Node root) {

            if (root == null) {
                return new TreeInfo(0,0);
            }
            TreeInfo left = diameter2(root.left);
            TreeInfo right = diameter2(root.right);

            int myHeight = Math.max(left.ht, right.ht) + 1;

            int diam1 = left.diam;
            int diam2 = right.diam;
            int diam3 = left.ht + right.ht + 1;

            int mydiam = Math.max(Math.max(diam1, diam2), diam3);

            TreeInfo myInfo = new TreeInfo(myHeight, mydiam);
            return myInfo;

        }

    }

    public static void main(String[] args) {

        int nodes[] = { 1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1 };
        // 1
        // / \
        // 2 3
        // / \ \
        // 4 5 6

        BinaryTree tree = new BinaryTree();
        Node root = tree.buildTree(nodes);
        // System.out.println(root.data);

        // Simpler Traversal (Also Called as DFS)
        System.out.print("preOrder  :");
        tree.preOrder(root);
        System.out.println();
        System.out.print("inOrder   :");
        tree.inOrder(root);
        System.out.println();
        System.out.print("postOrder :");
        tree.postOrder(root);
        System.out.println("\n");

        // Lever Traversal (Also Called as BFS)
        System.out.println("levelOrder :");
        tree.levelOrder(root);

        /*
         * DFS = Preorder / Inorder / Postorder
         * -> Ye sab depth-first hi hain
         * -> Har branch ko poori depth tak explore karte hain
         * 
         * BFS = Level Order Traversal
         * -> Level by level traversal
         * -> Queue ka use hota hai
         * 
         * Tree Traversal:
         * - Preorder, Inorder, Postorder → DFS
         * - Level Order → BFS
         * 
         */

        // Count of Nodes in Tree
        System.out.println("Total Node in Tree : " + tree.countOfNodes(root));

        // Sum of Nodes in Tree
        System.out.println("Total Sum of Node in Tree : " + tree.sumOfNodes(root));

        // Height of Tree
        System.out.println("Height of Tree: " + tree.height(root));

        // Diameter of Tree
        // -> Numbers of nodes in the longest path between any 2 nodes;
        // Two Case :
        // 1) Diameter root through
        // 2) Diameter NOT root through
        System.out.println("Diameter of Tree: " + tree.diameter(root));
        System.out.println("Diameter of Tree: " + tree.diameter2(root).diam);

    }

}

class LL{
    Node head;
    private int size;

    public LL(){
        this.size = 0;
    }
    class Node{
        int data;
        Node next;

        //Constructor
        public Node(int data){
            this.data = data;
            this.next = null;
            size++;
        }
    }

    //Insert Operation
    public void addFirst(int data){
        Node newNode = new Node(data);
        if(head == null){
            head = newNode;
            return;
        }
        newNode.next = head;
        head = newNode;
    }

    public void addLast(int data){
        Node newNode = new Node(data);
        if(head == null){
            head = newNode;
            return;
        }

        Node currNode = head;
        while(currNode.next != null){
            currNode = currNode.next;
        }

        currNode.next = newNode;
        
    }

    public void addAtPosition(int data, int position) {
        Node newNode = new Node(data);

        // If the list is empty or position is 0, insert at the head
        if (position == 0) {
            newNode.next = head;
            head = newNode;
            return;
        }

        // Traverse to the node just before the desired position
        Node currNode = head;
        for (int i = 0; i < position - 1; i++) {
            if (currNode == null) {
                throw new IndexOutOfBoundsException("Position is out of bounds");
            }
            currNode = currNode.next;
        }

        // Insert the new node
        newNode.next = currNode.next;
        currNode.next = newNode;
    }

    public void removeFirst(){
        if(head == null){
            System.out.println("Linked List Not Exits");
            return;
        }
        size--;
        head = head.next;
    }

    public void removeLast(){
        if(head == null){
            System.out.println("Linked List Not Exits");
            return;
        }
        size--;
        if(head.next == null){
            head = null;
            return;
        }
        Node currNode = head;
        while(currNode.next.next !=null){
            currNode = currNode.next;
        }
        currNode.next = null;

        //another method
        /* 
        Node secondLast = head;
        Node lastNode = head.next;
        while(lastNode.next != null){
            lastNode = lastNode.next;
            secondLast = secondLast.next;
        }
        secondLast.next = null;
        */
    }

      // Method to remove a node at a specific position
      public void removeAtPosition(int pos) {
        // If the list is empty, throw an exception
        if (head == null) {
            throw new IndexOutOfBoundsException("List is empty");
        }

        // If position is 0, remove the head
        if (pos == 0) {
            head = head.next;
            return;
        }

        // Traverse to the node just before the desired position
        Node currNode = head;
        for (int i = 0; i < pos - 1; i++) {
            if (currNode == null || currNode.next == null) {
                throw new IndexOutOfBoundsException("Position is out of bounds");
            }
            currNode = currNode.next;
        }

        // Remove the node at the specified position
        currNode.next = currNode.next.next;
    }

    public int getSize(){
        return size;
    }
    public void printLinkedList(){
        if(head == null){
            System.out.println("Linked List is Empty");
            return;
        }

        Node currNode = head;
        while(currNode != null){
            System.out.print(currNode.data + " --> ");
            currNode = currNode.next;
        }
        System.out.println("null");
    }

     public void reverseIterate(){

        if(head == null || head.next ==null){
            return;
        }
        Node prevNode = head;
        Node currNode = head.next;
        while(currNode != null){
            Node nextNode = currNode.next;
            currNode.next = prevNode;

            //update
            prevNode = currNode;
            currNode = nextNode; 
        }

        head.next = null;
        head = prevNode;
    }

    public Node reverseRecursive(Node head){
        if(head == null || head.next == null){
            return head;
        }
        Node newHead = reverseRecursive(head.next);
        head.next.next = head;
        head.next = null;

        return newHead;

    }
}

public class SingleLinkedList {
    public static void main(String[] args)
    {

        LL list = new LL();
        System.out.println("After Inserte");
        list.addLast(1);
        list.addLast(2);
        list.addLast(4);
        list.addLast(5);
        list.addFirst(100);
        list.addLast(999);
        list.addAtPosition(3,3);
        list.printLinkedList();

        System.out.println("After Delete");
        list.removeFirst();
        list.removeLast();
        list.printLinkedList();
        // list.reverseIterate();
        list.head = list.reverseRecursive(list.head);

        System.out.println("After Reverse");
        list.printLinkedList();

        System.out.println("Size : " + list.getSize());

        
    }
}

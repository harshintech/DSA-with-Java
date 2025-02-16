class DLL{
    private Node head;
    private Node tail;

    public DLL(){
        this.head = null;
        this.tail = null;
    }

    class Node{
        int data;
        Node next;
        Node prev;

        public Node(int data){
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    // Insertion 
    public void addFirst(int data){
        Node newNode = new Node(data);
        if(head == null){
            head = newNode;
            tail = newNode;
            return;
        }

        newNode.next = head;
        head.prev = newNode;
        head = newNode;
    }

    public void addLast(int data){
        Node newNode = new Node(data);
        if(head == null){
            head = newNode;
            tail = newNode;
            return;
        }

        tail.next = newNode;
        newNode.prev = tail;
        tail = newNode;

        // Node currNode = head;
        // while(currNode.next != null){
        //     currNode = currNode.next;
        // }
        // currNode.next = newNode;
        // newNode.prev = currNode;
    }

    //Deletion
    public void removeFirst(){
        if(head == null){
            System.out.println("Linked list not Exits");
            return;
        }

        head = head.next;

        if(head != null){
            head.prev = null;
        }
        else{
            tail = null;
        }
    }

    public void removeLast(){
        if(head == null){
            System.out.println("Linked List not Exits");
            return;
        }
        tail = tail.prev;
        if(tail != null){
            tail.next = null;
        }else{
            head = null;
        }
    }

    //Delete a node with a specific value
    public void removeNodeWithValue(int key){
        Node currNode = head;
        while(currNode != null && currNode.data != key){
            currNode = currNode.next;
        }

        if(currNode == null){
            return;
        }

        if(currNode.prev != null){
            currNode.prev.next = currNode.next;
        } else{
            head = currNode.next;
        }

        if(currNode.next != null){
            currNode.next.prev = currNode.prev;
        } else{
            tail = currNode.prev;
        }
    }



    public void printLinkedList(){
        if(head == null){
            System.out.println("Linked List is Empty");
            return;
        }

        Node currNode = head;
        while(currNode != null){
            System.out.print(currNode.data + " <--> ");
            currNode = currNode.next;
        }
        System.out.println("null");
    }

    public void displayForward(){
        Node currNode = head;
        while(currNode != null){
            System.out.print(currNode.data + " <-> ");
            currNode = currNode.next;
        }
        System.out.println("null");
    }

    public void displayBackward(){
        Node currNode = tail;
        while(currNode != null){
            System.out.print(currNode.data + " <-> ");
            currNode = currNode.prev;
        }
        System.out.println("null");
    }
    
}

public class DoubleLinkedList {
    public static void main(String[] args){

        DLL dlist = new DLL();
        dlist.addFirst(100);
        dlist.addLast(120);
        dlist.addLast(140);
        dlist.addLast(133);
        dlist.addLast(180);  
        dlist.addLast(190);
        dlist.printLinkedList();
        dlist.removeFirst();
        dlist.printLinkedList();
        dlist.removeNodeWithValue(133);
        dlist.printLinkedList();
        dlist.displayForward();
        dlist.displayBackward();

    }
}

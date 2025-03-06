class Queue{
    int arr[];
    int size;
    int rear = -1;
    int front = -1;

    Queue(int size){
        arr = new int[size];
        this.size = size;
        
    }

    public  boolean isEmpty(){
        return rear == -1;
    }

    public boolean isFull(){
        return rear == size - 1;
    }

    //enqueue
    public void add(int data){
        if(isFull()){
            System.out.println("Full Queue");
            return;
        }

        rear++;
        arr[rear] = data;

    }

    //dequeue
    public int remove(){
        if(isEmpty()){
            return -1;
        }

        int front = arr[0];;
        for(int i = 0;i<rear;i++){
            arr[i] = arr[i+1];
        }

        rear--;
        return front; 

    }

    //peak
    public int peek(){
        if(isEmpty()){
            System.out.println("empty queue");
            return -1;
        }

        return arr[0];

    }


}

public class QueueUsingArray {

    
    public static void main(String []args){

        Queue q = new Queue(10);

        q.add(1);
        q.add(2);
        q.add(3);
        q.add(4);
        q.add(5);

        while(!q.isEmpty()){
            System.out.println(q.peek());
            q.remove();
        }

        

    }
    
}

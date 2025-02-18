import java.util.ArrayList;

class StackAL{
    ArrayList<Integer> list = new ArrayList<>();

    public boolean isEmpty(){
        return list.size() == 0;
    }

    public void push(int data){
        list.add(data);
    }

    public int pop(){
        if(isEmpty()){
            return -1;
        }
        int top = list.get(list.size()-1);
        list.remove(list.size()-1);
        return top;
    }

    public int peek(){
        if(isEmpty()){
            return -1;
        }
        return list.get(list.size()-1);
    } 
    
    
}

public class StackUsingArrayList {
    public static void main(String[] main){

        StackLL s2 = new StackLL();
        s2.push(10);
        s2.push(20);
        s2.push(30);
        s2.push(40);
        s2.push(50);

        while(!s2.isEmpty()){
            System.out.println(s2.peek());
            s2.pop();
        }

        System.out.println(s2.peek());


    }
    
}

import java.util.Stack;

public class StackQuestion1 {

    public static void pushAtBottom(int data, Stack<Integer> s){
        if(s.isEmpty()){
            s.push(data);
            return;
        }
        int top = s.pop();
        pushAtBottom(data,s);
        s.push(top);

    }

    public static void reverse(Stack<Integer> s){
        if(s.isEmpty()){
            return;
        }
        int top = s.pop();
        reverse(s);
        pushAtBottom(top, s);

    }
    public static void main(String[] args){
        Stack<Integer> s4 = new Stack<>();

        s4.push(10);
        s4.push(20);
        s4.push(30);
        s4.push(40);
        s4.push(50);

        pushAtBottom(33,s4);
        reverse(s4);

        while(!s4.isEmpty()){
            System.out.println(s4.peek());
            s4.pop();
        }
    }
    
}

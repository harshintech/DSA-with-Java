import java.util.Stack;

public class StackUsingCollection {
    public static void main(String[] args){
        Stack<Integer> s3 = new Stack<>();

        s3.push(10);
        s3.push(20);
        s3.push(30);
        s3.push(40);
        s3.push(50);

        while(!s3.isEmpty()){
            System.out.println(s3.peek());
            s3.pop();
        }

        //System.out.println(s3.peek());


        
    }
    
}

// Time Complexity : O(n*n!);

public class Backtracking {
    public static void printPermutation(String str,String perm,int idx){
        if(str.length() == 0){
            System.out.println(perm);
            // System.out.println();
            return;
        }

        for(int i = 0;i<str.length();i++){
            char currChar = str.charAt(i);
            // System.out.println("currChar : " + currChar);
            String newStr = str.substring(0, i) +str.substring(i+1);
            // System.out.println("newStr : " + newStr);
            // System.out.println(newStr+ " " + (perm+currChar) + " " +(idx+1));


            printPermutation(newStr, perm+currChar, idx+1);
        }
    }
   public static void main(String[] args) {
    String str = "ABC";
    printPermutation(str, "", 0);
   } 
}

import java.util.Arrays;

public class CyclicSort {
    public static void main(String[] args) {
        
        int[] nums = {3,5,2,4,1};
        sort(nums);

        // System.err.println(nums); --> return memory address
        System.err.println(Arrays.toString(nums));

    }

    static void sort(int[] arr){

        int i = 0;
        while(i<arr.length){
            int correct = arr[i] - 1;

            if(arr[i] != arr[correct]){
                swap(arr,i,correct);
            }else{
                i++;
            }
        }
    }

    static void swap(int[] arr,int i ,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

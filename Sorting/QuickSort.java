public class QuickSort {

    public static int partition(int []arr,int low,int high){
        int pivot = high;
        int i = low -1;

        for(int j = low;j<high;j++){
            
        }

    }

    public static void quickSort(int []arr,int low,int high){
        while(low<high){
            int pidx = partition(arr,low,high);
        }
    }

    public static void main(String []args){
        int []arr = {6,3,9,5,2,8};

        int n = arr.length;

        quickSort(arr,0,n-1);
    }
    
}

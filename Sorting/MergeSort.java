class MergeSort{

    public static void mSort(int []arr,int low,int mid,int high){

        int []merged = new int[high - low + 1];

        int indx1 = low;
        int indx2 = mid + 1;

       int x = 0;

        while(indx1 <= mid && indx2 <= high){
            if(arr[indx1] <= arr[indx2]){
                merged[x++] = arr[indx1++];
            }
            else{
                merged[x++] = arr[indx2++];
            }
        }

        while(indx1<=mid){
            merged[x++] = arr[indx1++];
        }

        while(indx2 <= high){
            merged[x++] = arr[indx2++];
        }

         // 👇 Copy back!
    for (int i = 0; i < merged.length; i++) {
        arr[low + i] = merged[i];
    }
    }

    public static void partition(int []arr,int low,int high){

        if(low>=high){
            return;
        }

        int mid = low + (high - low)/2;

        partition(arr,low,mid);
        partition(arr,mid+1,high);

        mSort(arr,low,mid,high);

        
        
    



    }
    public static void main(String[] args){
       int arr[] = {6,3,9,21,15,7};

       int low = 0;
       int high = arr.length - 1;

       partition(arr,low,high);

 for(int i = 0;i<arr.length;i++){
        System.out.print(arr[i] + " ");
    }
    }
}

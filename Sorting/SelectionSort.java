// 📌 When to use Selection Sort
//  -> Good for learning — very simple concept.
//  -> Useful when memory writes are costly, because it does the minimum number of swaps (only n swaps).
//  -> Sometimes used for small arrays when swap count is more important than comparison count.
// ✅ Example: It’s sometimes better than Bubble Sort if write operations are expensive.


// 📌 Selection Sort — Time Complexity

// | Case             | Time Complexity |
// | ---------------- | --------------- |
// | Best Case        | O(n²)           |
// | Average Case     | O(n²)           |
// | Worst Case       | O(n²)           |


public class SelectionSort {
    public static void main(String[] args) {
        int arr[] = {4,12,34,12,1,67,37,76,89};
      

        for(int i = 0;i<arr.length;i++){
              int smallest = i;
            for(int j = i+1;j<arr.length;j++){
                if(arr[smallest] > arr[j]){
                    smallest = j;
                }
            }

           int temp = arr[smallest];
           arr[smallest] = arr[i];
           arr[i] = temp;
        }

         for(int i = 0;i<arr.length;i++){
            System.out.print(arr[i] + " ");
        }
    }
}

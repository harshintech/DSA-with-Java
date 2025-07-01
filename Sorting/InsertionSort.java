// NOTES :
// 📌 When to use Insertion Sort
//     -> When the array is small.
//     -> When the array is almost sorted — it works very well in this case.

// | Case                            | Time Complexity |
// | ------------------------------- | --------------- |
// | Best Case (already sorted)      | O(n)            |
// | Average Case                    | O(n²)           |
// | Worst Case (reverse sorted)     | O(n²)           |


// ⚡️ Why best case is O(n)?
//  -> If the array is already sorted, each new element just needs 1 comparison.
//  -> So, only 1 pass → linear time.


// ✅ Key takeaway:
// Insertion Sort is simple, stable, and great for small or nearly sorted arrays, but not good for large unsorted data.



public class InsertionSort {
 public static void main(String[] args) {
    int arr[] = {1,5,2,56,34,47,67,23,8};

    for(int i = 1;i<arr.length;i++){
        int current = arr[i];
        int j = i - 1;

        while(j >= 0 && current < arr[j]){
            arr[j + 1] = arr[j];
            j--;
        }

        arr[j+1] = current;
    }

     for(int i = 0;i<arr.length;i++){
        System.out.print(arr[i] + " ");
    }
 }   
}

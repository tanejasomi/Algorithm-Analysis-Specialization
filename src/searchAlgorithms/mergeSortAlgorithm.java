package searchAlgorithms;

import java.util.Arrays;

/* Divide the input array into equal halves and
Sort the left half, sort the right half and merge them recursively.
Time complexity: nlog(n)

@mergeSort: method to actual function to perform merge sort.
    int [] array: Input array to sorted
@mergeSort: divides the input array into two halves recursively call merge function.
    int[]array: Input array to be merged
    int[]temp: same variable used every time to merge (space efficient)
    int leftStart: starting point of sub array
    int rightEnd: ending point of sub array
@merge: compares values from left and right array and insert in array temp in sorted
    int [] array: input array
    int [] temp: single temp array for space efficiency
    int leftStart: starting index of input array
    int rightEnd: end index of input array
* */
public class mergeSortAlgorithm {

    public static void mergeSort(int [] array){
        mergeSort(array, new int [array.length], 0,array.length-1);

    }

    public static void mergeSort(int [] array, int [] temp, int leftStart, int rightEnd){
        if(leftStart >= rightEnd){
            return;
        }
        int middle = (leftStart + rightEnd)/2;
        mergeSort(array,temp,leftStart,middle);
        mergeSort(array,temp,middle+1,rightEnd);
        merge(array,temp,leftStart,rightEnd);
    }

    public static void merge(int [] array, int [] temp, int leftStart,int rightEnd){
        int leftEnd = (rightEnd + leftStart) / 2;
        int rightStart = leftEnd +1;
        int size = rightEnd - leftStart + 1;
        //indexes for left, right and temp array
        int left = leftStart;
        int right = rightStart;
        int index = leftStart;
        while(left <= leftEnd && right <= rightEnd){
            if(array[left] <= array[right]){
                temp[index] = array[left];
                left++;
            }else{
                temp[index] = array[right];
                right++;
            }
            index ++;
        }
        //copy remaining elements, of following two statements either left or right will only execute.
        System.arraycopy(array, left,temp,index,leftEnd-left +1);
        System.arraycopy(array, right, temp, index,rightEnd-right+1);
        //Copy everything back to array
        System.arraycopy(temp, leftStart,array,leftStart,size);

    }
    public static void main(String [] args){
        int [] array = {34, 1, 9000, 5, 9 , 10, 67,343};
        System.out.println("Input Array: "+Arrays.toString(array));
        mergeSort(array);
        System.out.println("Output Array: "+Arrays.toString(array));
    }
}

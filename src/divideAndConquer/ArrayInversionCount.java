package divideAndConquer;
import java.util.Arrays;

/*************************************************************
 * Inversion is number of pairs(i,j) of array A indices with
 * i<j and A[i] > A[j]
 * To calculate inversion we use divide and conquer algorithm,
 * and extend merge sort.
 * Time Complexity nlog(n)
 * @sortAndCount: polymorphic method with two definitions
 *  int [] array: Input array to sorted
 *  @sortAndCount: divides the input array into two halves recursively call merge function.
 *  int[]array: Input array to be merged
 *  int count: keep the count of inversions
 *  int[]temp: same variable used every time to merge (space efficient)
 *  int leftStart: starting point of sub array
 *  int rightEnd: ending point of sub array
 *  @mergeAndCountSplitInversion: compares values from left and right array and insert in array temp in sorted
 *  int [] array: input array
 *  int count: keep the count of inversions
 *  int [] temp: single temp array for space efficiency
 *  int leftStart: starting index of input array
 *  int rightEnd: end index of input array
**********************************************************/

public class ArrayInversionCount {

    public static int sortAndCount(int [] array){

        int count = sortAndCount(array,0,0,array.length -1, new int [array.length]);
        return count;
    }
    public static int sortAndCount(int [] array, int count, int leftStart, int rightEnd, int [] temp){
         if(leftStart >= rightEnd){
             return count;
         }

         int middle = (rightEnd + leftStart) / 2;
         int leftInvCount = sortAndCount(array, count, leftStart, middle, temp);
         int rightInvCount = sortAndCount(array, count, middle +1, rightEnd, temp);
         int mergeCount = mergeAndCountSplitInversion(array, count, leftStart, rightEnd, temp);

         return leftInvCount + rightInvCount + mergeCount;

     }
     public static int mergeAndCountSplitInversion(int [] array, int count, int leftStart, int rightEnd, int [] temp){

         int index = leftStart;
         int left = leftStart;
         int middle = (rightEnd + leftStart) / 2;
         int right =  middle +1;
         int length = rightEnd - leftStart +1;

         while(left <=middle && right <= rightEnd){
             if(array[left] <= array[right]) {
                 temp[index] = array[left];
                 left++;
             }else{
                 temp[index] = array[right];
                 right++;

                 count = count + (middle-left+1);
                 System.out.println("Array index from "+ leftStart+ " to "+ rightEnd+ " count: "+ count);
             }
             index ++;
         }

         //copy remaining elements, of following two statements either left or right will only execute.
         System.arraycopy(array, left,temp,index,middle-left +1);
         //System.arraycopy(array, right, temp, index,rightEnd-right+1);
         while(right <= rightEnd && index <= rightEnd){
             temp[index] = array[right];
             index++;
             right++;
             //count = count + (middle-left);
         }

         //Copy everything back to array
         System.arraycopy(temp, leftStart,array,leftStart,length);

     return count;
     }


     public static void main( String [] args){
         int [] array = {1,3,5,2,4,7,6,8};
       //  System.out.println("Input Array: "+Arrays.toString(array));
        int count=  sortAndCount(array);
        System.out.println("Output Array: "+Arrays.toString(array));
        System.out.println("Inversion count: "+count);
     }

}

package searchAlgorithms;

import java.util.Arrays;
import java.util.Random;

public class QuickSort {

    public static void qsort(int [] array){
        qsort(array,0,array.length-1);

    }
    public static void qsort(int [] array, int l,int r){
        System.out.println("QSort: l: "+ l+" r:"+r);
        if ((r-l)<1){
            return;
        }
        if((r-l)==1){
            if(array[l]>array[r])
                swap(array,l,r);

        }
        int p = choosePivot(l,r);
        int index = partition(array, l,r,p);
        //qsort(array, Math.min(l,index),Math.max(l,index));
        qsort(array, l,index);
        //qsort(array,Math.min(index+1,r),Math.max(index+1,r));
        qsort(array,index+1,r);

    }
    public static int choosePivot(int l,int r){
        /*Random rand = new Random();
        return rand.nextInt(r-l+1)+l;*/

        return(l+r)/2;

    }

    public static int partition(int [] array, int l, int r, int p){
        System.out.println("Partition: l: "+ l+", r: "+r+", p: "+p);
        int pivot = array[p];
        swap(array,l,p);
        int i = l+1;
        for(int j = l+1; j<=r;j++){
            if(array[j]< pivot){
                if(i!=j)
                swap(array,i,j);
                i++;
            }
        }
        swap(array, l,i-1);
        System.out.println("Return index: "+(i-1));
        return i-1;
    }

    public static void swap(int [] A, int i, int j){
        System.out.println("Swap: i: "+ i+" j:"+j);
        int temp = A[i];
        A[i] = A[j];
        A[j]= temp;
    }

    public static void main(String [] args){
        int [] array = {0,34, 1, 9000, 50, 9 , 989, 67,343};
        System.out.println("Input Array: "+ Arrays.toString(array));
        qsort(array);
        System.out.println("Output Array: "+Arrays.toString(array));
    }

}

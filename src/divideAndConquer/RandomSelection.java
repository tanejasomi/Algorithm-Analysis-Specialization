package divideAndConquer;
import java.util.Arrays;
import java.util.Random;
/*
* Problem: array A with n distinct numbers and
* a number i ={1,2,3,...n}. Find ith order statistic
* i.e ith order element of input array.
* Solution: Extension of quick sort algorithm.
*/

public class RandomSelection {

    public static int rSelect(int [] array, int order){
        return rSelect(array,0,array.length-1,order-1);

    }
    public static int rSelect(int [] array, int l,int r,int order){
        //System.out.println("rSelect: l: "+ l+" r:"+r);
        if ((r-l)<1){
            return array[l];
        }
        if((r-l)==1){
            if(array[l]>array[r])
                swap(array,l,r);
            if(order == l)
                return array[l];
            else
                return array[r];

        }
        int p = choosePivot(l,r);
        int index = partition(array, l,r,p);
        if(index == order){
            return (array[index]);

        }else if(order < index){
            return rSelect(array, Math.min(l,index-1),Math.max(l,index-1),order);

        }else{
            return rSelect(array,Math.min(index+1,r),Math.max(index+1,r),order);

        }



    }
    public static int choosePivot(int l,int r){
        Random rand = new Random();
        return rand.nextInt(r-l+1)+l;

        // return(l+r)/2;

    }

    public static int partition(int [] array, int l, int r, int p){
        //System.out.println("Partition: l: "+ l+", r: "+r+", p: "+p);
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
        //System.out.println("Return index: "+(i-1));
        return i-1;
    }

    public static void swap(int [] A, int i, int j){
        if(i!=j){
            //System.out.println("Swap: i: "+ i+" j:"+j);
            int temp = A[i];
            A[i] = A[j];
            A[j]= temp;
        }

    }

    public static void main(String [] args){
        int [] array = {0,34,989, 1, 9000, 5, 9 , 10, 67,343,2};
        System.out.println("Input Array: "+ Arrays.toString(array));
        int order = 7;
        int val = rSelect(array,order);
        System.out.println(order +" element of given array is "+ val);
    }


}

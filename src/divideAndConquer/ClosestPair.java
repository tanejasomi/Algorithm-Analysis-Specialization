package divideAndConquer;

import java.util.ArrayList;
import java.util.Arrays;

/*
* Identify distinct pair that have smallest distance between them.
* Algorithm:
* Step1: make sorted copies of coordinates Px and Py
*
* //sort px and py
* findClosestPair()
* closestSplitPair()
* //
*
* */
public class ClosestPair {

    public static void sortArray(int[][] array, int [][]pX, int[][] pY){
        System.out.println("sortArray");
        System.arraycopy(array, 0,pX,0,array.length);
        System.out.println(" PX");
        for(int i =0; i<array.length;i++){
            System.out.print(pX[i][0]+","+pX[i][1]+" ");
        }

        System.arraycopy(array, 0,pY,0,array.length);
        mergeSortX(pX, new int [array.length][2], 0,array.length-1);
        mergeSortY(pY, new int [array.length][2], 0,array.length-1);
        System.out.println(" ");
        System.out.println(" Sorted PX");
        for(int i =0; i<array.length;i++){
                System.out.print(pX[i][0]+","+pX[i][1]+" ");
        }
        System.out.println(" ");
        System.out.println(" Sorted PY");
        for(int i =0; i<array.length;i++){
            System.out.print(pY[i][0]+","+pY[i][1]+" ");
        }
        System.out.println(" ");

    }

    public static void mergeSortX(int [][] array, int [][] temp, int leftStart, int rightEnd){
        //System.out.println("mergeSortX");
        if(leftStart >= rightEnd){
            return;
        }
        int middle = (leftStart + rightEnd)/2;
        mergeSortX(array,temp,leftStart,middle);
        mergeSortX(array,temp,middle+1,rightEnd);
        mergeX(array,temp,leftStart,rightEnd);
    }

    public static void mergeSortY(int [][] array, int [][] temp, int leftStart, int rightEnd){
        //System.out.println("mergeSortY");
        if(leftStart >= rightEnd){
            return;
        }
        int middle = (leftStart + rightEnd)/2;
        mergeSortY(array,temp,leftStart,middle);
        mergeSortY(array,temp,middle+1,rightEnd);
        mergeY(array,temp,leftStart,rightEnd);
    }

    public static void mergeX(int [][] array, int [][] temp, int leftStart,int rightEnd){
       // System.out.println("mergeX");
        int leftEnd = (rightEnd + leftStart) / 2;
        int rightStart = leftEnd +1;
        int size = rightEnd - leftStart + 1;
        //indexes for left, right and temp array
        int left = leftStart;
        int right = rightStart;
        int index = leftStart;

        while(left <= leftEnd && right <= rightEnd){
            if(array[left][0] <= array[right][0]){
                temp[index][0] = array[left][0];
                temp[index][1] = array[left][1];
                left++;
            }else{
                temp[index][0] = array[right][0];
                temp[index][1]=array[right][1];
                right++;
            }
            index ++;
        }
        //copy remaining elements, of following two statements either left or right will only execute.
        System.arraycopy(array, left,temp,index,leftEnd-left+1);
        System.arraycopy(array, right, temp, index,rightEnd-right+1);
        //Copy everything back to array
        System.arraycopy(temp, leftStart,array,leftStart,size);

    }

    public static void mergeY(int [][] array, int [][] temp, int leftStart,int rightEnd){
        //System.out.println("mergeY");
        int leftEnd = (rightEnd + leftStart) / 2;
        int rightStart = leftEnd +1;
        int size = rightEnd - leftStart + 1;
        //indexes for left, right and temp array
        int left = leftStart;
        int right = rightStart;
        int index = leftStart;

        while(left <= leftEnd && right <= rightEnd){
            if(array[left][1] <= array[right][1]){
                temp[index][1] = array[left][1];
                temp[index][0] = array[left][0];
                left++;
            }else{
                temp[index][1] = array[right][1];
                temp[index][0] = array[right][0];
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


    public static void findClosestPair(int[][] pX, int[][] pY, int[][] pxy, int xi, int xl, int yi, int yl ){
        System.out.println("findClosestPair");
       //ADD BASE CASE FIRST
        if(xl<=2){
            double d1 = getDistance(pX[xi][0],pX[xi+1][1],pX[xi][1],pX[xi+1][1]);
            double d2 = getDistance(pY[yi][0],pY[yi+1][1],pY[yi][1],pY[yi+1][1]);
            if(d1<d2){
                setValue(pxy,pX[xi][0],pX[xi+1][0],pX[xi][1],pX[xi+1][1]);


            }else{
                setValue(pxy,pY[yi][0],pY[yi+1][0],pY[yi][1],pY[yi+1][1]);

            }
            return;
        }
        int rX = (xi +xl/2);
        int rY = (yi + yl/2);
        int qX = xi;
        int qY = yi;

        int[][] p1q1 = new int[2][2];
        int[][] p2q2 = new int[2][2];
        findClosestPair(pX,pY,p1q1,qX,xl/2-1, qY ,yl/2);
        findClosestPair(pX,pY,p2q2,rX,xl/2-1, rY ,yl/2);

        double dX =getDistance(p1q1[0][0],p1q1[1][0],p1q1[0][1],p1q1[1][1]);
        double dY =getDistance(p2q2[0][0],p2q2[1][0],p2q2[0][1],p2q2[1][1]);
        double dMin = Math.min(dX,dY);
        if(dX<dY){
            setValue(pxy,p1q1[0][0],p1q1[1][0],p1q1[0][1],p1q1[1][1]);
        }else{
            setValue(pxy,p2q2[0][0],p2q2[1][0],p2q2[0][1],p2q2[1][1]);

        }

        int[][] p3q3 = new int[2][2];
        //Find closestSplitPair()
        boolean flag;
        flag = closestSplitPair(pX,pY,p3q3,dMin);
        if(flag){
            double dXY=getDistance(p3q3[0][0],p3q3[1][0],p3q3[0][1],p3q3[1][1]);
            if(dXY<dMin){
                setValue(pxy,p3q3[0][0],p3q3[1][0],p3q3[0][1],p3q3[1][1]);
            }

        }

    }

    public static boolean closestSplitPair(int[][]pX,int[][]pY,int[][]p3q3,double dMin){
        System.out.println("closestSplitPair");

     int xMax = pX[pX.length/2][0];
     System.out.println("xMax: "+xMax+" dMin: "+dMin);
     class Coordinates{
         int x;
         int y;
         public Coordinates(int x, int y){
             this.x = x;
             this.y = y;
         }
     }
     ArrayList<Coordinates> sY = new ArrayList<>();

     for(int i =0; (pY[i][0]<=(xMax+dMin) && (i<pY.length-1)); i++) {
         if((pY[i][0]>= (xMax-dMin)) && (pY[i][0]<=(xMax+dMin))){
             sY.add(new Coordinates(pY[i][0],pY[i][1]));
         }
     }

     if(sY.size()<2){
         return false;
     }

     int [][] sYx = new int[sY.size()][2];
     int ind = 0;
     for(Coordinates c: sY ){
         sYx[ind][0] = c.x;
         sYx[ind][1] = c.y;
         ind++;
         System.out.println(c.x+","+c.y+"> ");

     }

     double best = dMin;

     for(int i=1; i <= sYx.length-1;i++){
         for(int j =1; j < (Math.min(7,sYx.length-i));j++){
             double dis = getDistance(sYx[i][0],sYx[i+j][0],sYx[i][1],sYx[i+j][1]);
             if(dis <best){
                 setValue(p3q3,sYx[i][0],sYx[i+j][0],sYx[i][1],sYx[i+j][1]);
                 best = dis;
             }

         }
     }
     return true;
    }


    public static double getDistance(int x1, int x2, int y1, int y2){
        //System.out.println("getDistance");
        return(Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2)));

    }
    public static void setValue(int[][]pxy, int x1, int x2, int y1, int y2){
        System.out.println("setValue: "+x1+","+y1+"  "+x2+","+y2 +" >"+getDistance(x1,x2,y1,y2));
        pxy[0][0]=x1;
        pxy[0][1]=y1;
        pxy[1][0]=x2;
        pxy[1][1]=y2;

    }

    public static void calculateClosestPair(int[][]array,int[][] out){
        System.out.println("calculateClosestPair");
        int [][] pX = new int [array.length][2];
        int [][] pY = new int [array.length][2];
        sortArray(array,pX,pY);
        int[][] px1 = {{0,1},{1,19},{5,9},{6,8},{8,15},{24,2}};
        int[][] px2 = {{0,1},{24,2},{6,8},{5,9},{8,15},{1,19}};
        findClosestPair(px1,px2,out,0,array.length,0,array.length);

    }

    public static void main(String [] args){
        System.out.println("main");
        int [][] array = {{0,1},{5,9},{24,2},{6,8},{1,19},{8,10}};
        //  System.out.println("Input Array: "+Arrays.toString(array));
        int[][] out = new int[2][2];
        calculateClosestPair(array, out);

        System.out.println("closest Pair are: " +" "+ out[0][0]+" "+out[0][1]+" "+out[1][0]+" "+out[1][1]);
        System.out.println("Distance "+ getDistance(out[0][0],out[1][0],out[0][1],out[1][1]));
        System.out.println("Distance 5,9 &6,8:  "+ getDistance(5,6,9,8));
    }


}


/*
public double getDistance() {
    return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
  }
 */
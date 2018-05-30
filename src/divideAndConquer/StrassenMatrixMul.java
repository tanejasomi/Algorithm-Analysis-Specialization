package divideAndConquer;
import java.util.Scanner;
/*** Testing pending for implementation******************************
 * STRASSEN MATRIX MULTIPLICATION: Divide and conquer implementation
 * Step 1: Recursively compute 7 necessary products
 * Step 2: Do addition and substraction
 *
 * X = | A  B |
 *     | C  D |
 *
 * Y = | E  F |
 *     | G  H |
 *
 * P1 = A(F-H)
 * P2 = (A+B)H
 * P3 = (C+D)E
 * P4 = D(G-E)
 * P5 = (A+D)(E+H)
 * P6 = (B-D)(G+H)
 * P7 = (A-C)(E+F)
 *
 * X*Y = | P5+P4-P2+P6   P1+P2      |
 *       | P3+P4        P1+P5-P3-P7 |
 *
 ************************************************************/
public class StrassenMatrixMul {



    public static int[][] strassenMul(int[][] X, int[][] Y){
        int len = X.length;
        int[][] out = new int [len][len];

        if(len == 1) {
            out[0][0] = X[0][0] * Y[0][0];
        } else{

            int [][] A= new int[len/2][len/2];
            int [][] B= new int[len/2][len/2];
            int [][] C= new int[len/2][len/2];
            int [][] D= new int[len/2][len/2];
            int [][] E= new int[len/2][len/2];
            int [][] F= new int[len/2][len/2];
            int [][] G= new int[len/2][len/2];
            int [][] H= new int[len/2][len/2];

            //divide matrix X into 4 halves
            splitMatrix(X, A,0,0);
            splitMatrix(X, B,0,len/2);
            splitMatrix(X, C,len/2,0);
            splitMatrix(X, D,len/2,len/2);

            //divide matrix Y into 4 halves
            splitMatrix(Y, E,0,0);
            splitMatrix(Y, F,0,len/2);
            splitMatrix(Y, G,len/2,0);
            splitMatrix(Y, H,len/2,len/2);
            /*
             * P1 = A(F-H)
             * P2 = (A+B)H
             * P3 = (C+D)E
             * P4 = D(G-E)
             * P5 = (A+D)(E+H)
             * P6 = (B-D)(G+H)
             * P7 = (A-C)(E+F)
             * */

            int [][] P1 = strassenMul(A,substract(F,H));
            int [][] P2 = strassenMul(add(A,B),H);
            int [][] P3 = strassenMul(add(C,D),E);
            int [][] P4 = strassenMul(D,substract(G,E));
            int [][] P5 = strassenMul(add(A,D),add(E,H));
            int [][] P6 = strassenMul(substract(B,D),add(G,H));
            int [][] P7 = strassenMul(substract(A,C),add(E,H));


            /*
             * X*Y = | P5+P4-P2+P6   P1+P2      |
             *       | P3+P4        P1+P5-P3-P7 |
             */
            int [][] C11 = add(add(P5,P4),substract(P6,P2));
            int [][] C12 = add(P1,P2);
            int [][] C21 = add(P3,P4);
            int [][] C22 = substract(add(P1,P5),add(P3,P7));

            merge(C11,out,0,0);
            merge(C12,out,0,len/2);
            merge(C21,out,len/2,0);
            merge(C22,out,len/2,len/2);

        }


        return out;
    }


    public  static int[][] substract(int[][]A, int[][]B ){
        int len = A.length;
        int[][] output = new int [len][len];

        for(int i =0; i <len;i++)
            for(int j = 0; j<len; j++){
            output[i][j] = A[i][j] - B[i][j];
            }
            return  output;
    }

    public static int[][] add(int[][]A, int[][]B ){
        int len = A.length;
        int[][] output = new int [len][len];

        for(int i =0; i <len;i++)
            for(int j = 0; j<len; j++){
                output[i][j] = A[i][j] + B[i][j];
            }
        return  output;
    }
    //For splitting matrix we provide output matrix which will store sub matrix
    // and starting co-ordinates of sub-matrix copy till the length of out matrix
    public static void splitMatrix(int[][] A, int [][] out, int xi, int yi){
        int len = out.length;

        for(int i =0; i<len; i++)
            for(int j=0;j<len;j++){
            out[i][j] = A[xi+i][yi+j];
            }

    }

    //merge values to form resultant matrix
    public static void merge(int[][] C, int[][] out, int xi, int yi){
        int len = C.length;
        for(int i = 0; i<len;i++)
            for(int j =0; j<len; j++){
            out[xi+i][yi+j] = C[i][j];
            }

    }

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        System.out.println("Strassen Multiplication");

        System.out.println("Enter order n: ");
        int n = scan.nextInt();
        int [][] x = new int [n][n];
        int [][] y = new int [n][n];

        System.out.println("Enter first matrix: ");
        for(int i =0; i<n; i++)
            for(int j=0;j<n;j++){
            x[i][j] = scan.nextInt();

            }

        System.out.println("Enter second matrix: ");
        for(int i =0; i<n; i++)
            for(int j=0;j<n;j++){
                y[i][j] = scan.nextInt();

            }


         int [][] out = strassenMul(x,y);

        System.out.println("\n Product of matrices x and y ");
        for(int i = 0; i <n; i++){
            System.out.println();
            for(int j=0; j<n; j++){
                System.out.print(x[i][j]+" ");
            }
        }

        System.out.println("\n Product of matrices x and y ");
        for(int i = 0; i <n; i++){
            System.out.println();
            for(int j=0; j<n; j++){
                System.out.print(y[i][j]+" ");
            }
        }

        System.out.println("\n Product of matrices x and y ");
        for(int i = 0; i <n; i++){
            System.out.println();
            for(int j=0; j<n; j++){
                System.out.print(out[i][j]+" ");
            }
        }

    }


}

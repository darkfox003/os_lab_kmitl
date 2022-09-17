import java.util.Arrays;

public class Lab_MatrixMul {
    public static void main(String[] args) {
        int[][] inputA = {{5, 6, 7}, {4, 8, 9}};
        int[][] inputB = {{6, 4}, {5, 7}, {1, 1}};
        MyData matA = new MyData(inputA);
        MyData matB = new MyData(inputB);
        int matC_r = matA.data.length;
        int matC_c = matB.data[0].length;
        MyData matC = new MyData(matC_r, matC_c);
        Thread[][] threads = new Thread[matC_r][matC_c];
        for (int i = 0; i < matC_r; i++) {
            for (int j = 0; j < matC_c; j++) {
                threads[i][j] = new Thread(new MatrixMulThread(i, j, matA, matB, matC));
                threads[i][j].start();
            }
        }
        for (int i = 0; i < matC_r; i++) {
            for (int j = 0; j < matC_c; j++) {
                try {
                    threads[i][j].join();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        matC.show();
    }
}

class MatrixMulThread implements Runnable {
    int processing_row;
    int processing_col;
    MyData  datA;
    MyData  datB;
    MyData  datC;

    MatrixMulThread(int tRow, int tCol, MyData a, MyData b, MyData c) {
        processing_row = tRow;
        processing_col = tCol;
        datA = a;
        datB = b;
        datC = c;
    }

    @Override
    public void run() {
        int sum = 0;

        for (int i = 0; i < datA.data[0].length; i++)
            sum += (datA.data[processing_row][i] * datB.data[i][processing_col]);
        datC.data[processing_row][processing_col] = sum;
    }
}

class MyData {
    int[][] data;

    MyData(int[][] m) {
        data = m;
    }
    MyData(int r, int c) {
        data = new int[r][c];
        for (int[] arow : data)
            Arrays.fill(arow, 9);
    }

    void    show() {
        System.out.println(Arrays.deepToString(data));
    }
}


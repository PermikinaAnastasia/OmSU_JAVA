

import java.io.*;

public class DemoMatrix {

    public static void writeMatrix(Matrix matrix, File file) throws MyMatrixException, IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write(String.valueOf(matrix.getSize()));
            bw.newLine();
            for (int i = 0; i < matrix.getSize(); i++) {
                for (int j = 0; j < matrix.getSize(); j++) {
                    bw.write(String.valueOf(matrix.getElem(i, j)) + " ");
                }
                bw.newLine();
            }
        }catch (FileNotFoundException e) {
            System.err.println("No file was found!");
        }
    }

    public static Matrix readMatrix(File file) throws MyMatrixException, IOException {
        Matrix matrix = null;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            int size;
            try {
                size = Integer.parseInt(br.readLine());
            } catch (NumberFormatException e) {
                throw new NumberFormatException("No size written!");
            }
            matrix = new Matrix(size);
            String[] line;
            for (int i = 0; i < size; i++) {
                line = br.readLine().split(" ");
                for (int j = 0; j < size; j++) {
                    matrix.setElem(i, j, Double.parseDouble(line[j]));
                }
            }
        }
        return matrix;
    }

    public static double sum (Matrix matrix) throws MyMatrixException {
        double res = 0;
        for (int i = 0; i < matrix.getSize(); i++) {
            for (int j = 0; j < matrix.getSize(); j++){
                res += matrix.getElem(i, j);
            }
        }
        return res;
    }

    public static void printMatrix (Matrix matrix) throws MyMatrixException {
        for (int i = 0; i < matrix.getSize(); i++) {
            for (int j = 0; j < matrix.getSize(); j++) {
                System.out.print(String.valueOf(matrix.getElem(i, j)) + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws IndexException, NullDeterminantException, ClassNotFoundException {
        Matrix matrix = new Matrix(3);
        try {
            matrix.setElem(0, 2, 1);
        } catch (IndexException e) {
            e.printStackTrace();
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("test.dat"))) {
            oos.writeObject(matrix);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("test.dat"))) {
            matrix = (Matrix) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            printMatrix(matrix);
            System.out.println(sum(matrix));
        } catch (MyMatrixException e) {
            e.printStackTrace();
        }
    }
}


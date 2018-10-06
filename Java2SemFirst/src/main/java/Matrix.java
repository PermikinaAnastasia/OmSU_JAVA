

import java.io.Serializable;
import java.util.Arrays;

public class Matrix implements IMatrix, Serializable {
    protected double[] matrix;
    protected int size;
    protected double determinant;
    protected boolean isDeterminant;

    public Matrix(int size) {
        this.size = size;
        matrix = new double[size * size];
        for ( int i = 0; i < size; i++ ) {
            for ( int j = 0; j < size; j++ ) {
                matrix[i * size + j] = (i == j) ? 1 : 0;
            }
        }

    }

    public Matrix(Matrix m) {
        this.size = m.getSize();
        matrix = Arrays.copyOf(m.matrix, m.matrix.length);

    }

    public Matrix multiply(Matrix m) throws MultiMatrixExc, IndexException, NullDeterminantException {
        if (m.getSize() != getSize()) throw new MultiMatrixExc("Different sizes of matrixes");

        Matrix res = new Matrix(getSize());
        double buf;
        for ( int i = 0; i < getSize(); i++ ) {
            for ( int j = 0; j < getSize(); j++ ) {
                buf = 0;
                for ( int k = 0; k < getSize(); k++ ) {
                    buf += getElem(i, k) * m.getElem(k, j);
                }
                res.setElem(i, j, buf);
            }
        }
        return res;
    }

    public int getSize() {
        return size;
    }

    public double getElem(int i, int j) throws IndexException {
        if (i >= getSize() || j >= getSize()) throw new IndexException("No element with such index!");
        return matrix[size * i + j];
    }

    public void setElem(int i, int j, double elem) throws IndexException, NullDeterminantException {
        if (i >= getSize() || j >= getSize()) throw new IndexException("No element with such index!");

        isDeterminant = elem == matrix[i * size + j];
        matrix[size * i + j] = elem;
    }


    public double calculateDet() throws IndexException, NullDeterminantException {
        Matrix mtr = new Matrix(this);
//        mtr.matrix = Arrays.copyOf(matrix, matrix.length);
        double calcResult = 0;
        if (mtr.size == 1) {
            return mtr.getElem(0, 0);
        }
        if (mtr.size == 2) {
            calcResult = mtr.getElem(0, 0) * mtr.getElem(1, 1) - mtr.getElem(0, 1) * mtr.getElem(1, 0);
        } else {
            int sign = 0;
            for ( int i = 0; i < mtr.size; i++ ) {
                sign = (i % 2 == 0) ? 1 : -1;
                calcResult += sign * mtr.getElem(0, i) * (getMinor(mtr, 0, i).calculateDet());
            }

        }
        isDeterminant = true;
        determinant = calcResult;
        return calcResult;
    }

    public static Matrix getMinor(Matrix matrix, int line, int column) throws IndexException, NullDeterminantException {
        Matrix minor = new Matrix(matrix.size - 1);
        int idS = 0;
        int idC = 0;
        for ( int i = 0; i < matrix.size; i++ ) {
            idC = 0;
            for ( int j = 0; j < matrix.size; j++ ) {
                if (i == line) {
                    idS = 1;
                } else {
                    if (j == column) {
                        idC = 1;
                    } else {
                        minor.setElem(i - idS, j - idC, matrix.getElem(i, j));
                    }
                }

            }

        }
        return minor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Matrix)) return false;

        Matrix matrix = (Matrix) o;

        return Arrays.equals(this.matrix, matrix.matrix);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(matrix);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for ( int i = 0; i < size; i++ ) {
            for ( int j = 0; j < size; j++ ) {
                sb.append(matrix[i * size + j]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }


}

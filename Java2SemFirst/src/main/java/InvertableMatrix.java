

import java.util.Arrays;

public class InvertableMatrix extends Matrix implements IInvertableMatrix {

    public InvertableMatrix(int size) {
        super(size);
    }

    public InvertableMatrix(Matrix matrix) throws NullDeterminantException, IndexException {
        super(matrix);
        if (matrix.calculateDet() == 0) {
            throw new NullDeterminantException("Null det, can't create invertable matrix");
        }
    }


    public InvertableMatrix getInverseMatrix() throws NullDeterminantException, IndexException {
        InvertableMatrix inverseMatrix = new InvertableMatrix(this);
        double[] arr = Arrays.copyOf(this.matrix, this.matrix.length);
        double determinant = this.calculateDet();
        for(int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                inverseMatrix.setElem(i, j, arr[j*size + i]);
            }
        }
        for(int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                int sign = ((i + j) % 2 == 0 || (getMinor(inverseMatrix, i, j).calculateDet() == 0))? 1 : -1;
                arr[i*size + j] = sign * (getMinor(inverseMatrix, i, j)).calculateDet()/determinant;
            }
        }
        for(int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                inverseMatrix.setElem(i, j, arr[i*size + j]);
            }
        }
        return inverseMatrix;
    }

    @Override
    public void setElem(int i, int j, double elem) throws IndexException, NullDeterminantException {
        Matrix tmp = new Matrix(this);
        tmp.setElem(i, j, elem);
        if (Math.abs(tmp.calculateDet())<1e-5) throw new NullDeterminantException("Null det!");
        super.setElem(i, j, elem);
    }
}


import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class InvertableMatrixTest {
    private static final double DELTA = 1E-6;

    @Test(expected = NullDeterminantException.class)
    public void testInvertableMatrix1() throws IndexException, NullDeterminantException {
        Matrix matrix = new Matrix(2);
        matrix.setElem(0,0, 1);
        matrix.setElem(0,1, 2);
        matrix.setElem(1,0, 2);
        matrix.setElem(1,1, 4);
        InvertableMatrix invertableMatrix = new InvertableMatrix(matrix);
    }

    @Test
    public void testInvertableMatrix2() throws NullDeterminantException, IndexException {
        Matrix matrix = new Matrix(4);
        for(int i = 0; i < 4; i++) {
            matrix.setElem(i, i, 1);
        }
        InvertableMatrix invMatr = new InvertableMatrix(matrix);
        InvertableMatrix invMatrNew = new InvertableMatrix(invMatr);
        assertEquals(invMatr, invMatrNew);
    }

    @Test
    public void testGetInverseMatrix1() throws IndexException, NullDeterminantException {
        Matrix matrix = new Matrix(4);
        //InvertableMatrix invMatr = new InvertableMatrix(matrix);
//        System.out.println(Arrays.toString(matrix.matrix));
//        System.out.println(Arrays.toString(invMatr.matrix));
//        System.out.println(Arrays.toString(invMatr.getInverseMatrix().matrix));
//
        matrix.setElem(0, 0, 1);
        matrix.setElem(0, 1, 2);
        matrix.setElem(0, 2, 1);
        matrix.setElem(1, 0, 4);
        matrix.setElem(1, 1, 2);
        matrix.setElem(1, 2, 6);
        matrix.setElem(2, 0, 7);
        matrix.setElem(2, 1, 3);
        matrix.setElem(2, 2, 9);

        InvertableMatrix invA = new InvertableMatrix(matrix);
        InvertableMatrix invB = invA.getInverseMatrix();
        System.out.println(Arrays.toString(invA.matrix));
        System.out.println(Arrays.toString(invB.matrix));
//        System.out.println(Arrays.toString(invB.getInverseMatrix().matrix));
        assertArrayEquals(invA.matrix, invB.getInverseMatrix().matrix, DELTA);
       // assertArrayEquals(m.matrix, invA.multiply(invB).matrix, DELTA);

    }

    @Test
    public void testInvertableMatrixSetElem() throws NullDeterminantException, IndexException {
        Matrix matrix = new Matrix(4);
        for(int i = 0; i < 4; i++) {
            matrix.setElem(i, i, 1);
        }
        InvertableMatrix invMatr = new InvertableMatrix(matrix);
        invMatr.setElem(0, 2, 3);
    }

    @Test(expected = NullDeterminantException.class)
    public void testInvertableMatrixSetElemFail() throws NullDeterminantException, IndexException {
        Matrix matrix = new Matrix(4);
        InvertableMatrix invMatr = new InvertableMatrix(matrix);
        invMatr.setElem(2, 2, 0);
    }

}
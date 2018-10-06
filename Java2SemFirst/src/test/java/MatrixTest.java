;

import org.junit.Test;

import static org.junit.Assert.*;

public class MatrixTest {
    private static final double DELTA = 1E-6;

    @Test
    public void testMatrix1() throws IndexException, NullDeterminantException {
        Matrix matrix = new Matrix(3);
        assertEquals(3, matrix.getSize());
        matrix.setElem(0, 1, -2);
        matrix.setElem(1, 2, 1.4);
        assertEquals(matrix, new Matrix(matrix));
    }

    @Test
    public void testMatrixDeterminant() throws IndexException, NullDeterminantException {
        Matrix matrix = new Matrix(3);
        matrix.setElem(0, 0, 0);
        matrix.setElem(0, 1, 3);
        matrix.setElem(0, 2, 2);
        matrix.setElem(1, 0, 1);
        matrix.setElem(1, 1, 4);
        matrix.setElem(1, 2, 7);
        matrix.setElem(2, 0, -3);
        matrix.setElem(2, 1, 2);
        matrix.setElem(2, 2, 8);
        matrix.calculateDet();
        assertEquals(-59, matrix.calculateDet(), DELTA);
    }

    @Test
    public void testSetWrongIndex() throws NullDeterminantException {
        Matrix matrix = new Matrix(2);
        try {
            matrix.setElem(0, 2, -5);
        } catch (IndexException e) {
            assertEquals("No element with such index!", e.getMessage());
        }
    }

    @Test
    public void testGetWrongIndex() throws IndexException {
        Matrix matrix = new Matrix(2);
        try {
            matrix.getElem(0, 2);
        } catch (IndexException e) {
            assertEquals("No element with such index!", e.getMessage());
        }
    }

}
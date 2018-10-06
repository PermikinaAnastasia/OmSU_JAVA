import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.Assert.*;

//import static java.DemoMatrix.sum;
//import static DemoMatrix.sum;

public class DemoMatrixTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void testFileReadWriteMatrix() throws  MyMatrixException {
        int size = 3;
        Matrix matrix = new Matrix(size);
        matrix.setElem(0, 0, 0);
        matrix.setElem(0, 1, 5.4);
        matrix.setElem(0, 2, 3);
        matrix.setElem(1, 0, -2);
        matrix.setElem(1, 1, 7);
        matrix.setElem(1, 2, 8.8);
        matrix.setElem(2, 0, 11);
        matrix.setElem(2, 1, -9);
        matrix.setElem(2, 2, 11);

        try {
            File file = folder.newFile("test.txt");
            DemoMatrix.writeMatrix(matrix, file);
            assertTrue(file.exists());
            assertEquals(size + 1, Files.readAllLines(file.toPath()).size());
            Matrix matrixRead = DemoMatrix.readMatrix(file);
            assertEquals(matrix, matrixRead);
        } catch (IOException e) {
            fail();
        }
    }

    /*@Test
   public void testFileReadMatrixFail() throws MyMatrixException, IOException {
        File file = new File("D:\\Java2SemFirst\\src\\main\\java\\file.txt");
        Matrix matrixRead = DemoMatrix.readMatrix(file);
        Matrix m = new Matrix(matrixRead);
//        System.out.println(Arrays.toString(matrixRead.matrix));
//        System.out.println(Arrays.toString(m.matrix));
        assertEquals(m, matrixRead);
    }
    */





}
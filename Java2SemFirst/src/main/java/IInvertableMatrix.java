

public interface IInvertableMatrix extends IMatrix {
    InvertableMatrix getInverseMatrix() throws MyMatrixException;
}

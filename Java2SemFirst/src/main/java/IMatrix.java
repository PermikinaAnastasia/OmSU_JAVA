
public interface IMatrix {
    double getElem(int i, int j) throws IndexException;
    void setElem(int i, int j, double elem) throws MyMatrixException;
    double calculateDet() throws MyMatrixException;
}

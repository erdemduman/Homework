package sample;

import java.util.ArrayList;

/**
 * @author Erdem Duman <br>
 * This class solves equations with Inverse Matrix method. <br>
 */
public class InverseMatrix implements SolvingMethods{

    private final String nameOfMethod = "Matrix Inversion";

    ArrayList<ArrayList<Double>> matrix;
    private int order;
    ArrayList<Double> resultMatrix;

    /**
     * Constructor of class. <br>
     * @param matrix the given matrix <br>
     * @param order number of variables, that will be solved <br>
     */

    public InverseMatrix(ArrayList<ArrayList<Double>> matrix, int order){
        this.matrix = matrix;
        this.order = order;
        resultMatrix = new ArrayList<>();
    }

    /**
     * This method returns the name of method.<br>
     * @return name of method <br>
     */
    @Override
    public String methodName(){return nameOfMethod;}

    /**
     * This method separates the result of equations <br>
     * from the square matrix, that will be inversed.<br>
     */
    public void storeResult(){
        for(int i = 0; i < this.order; i++){
            resultMatrix.add(matrix.get(i).get(this.order));
        }
    }

    /**
     * This method gets a double 2D ArrayList and fills it with zeros. <br>
     * @param temp ArrayList, that will be filled.<br>
     * @return ArrayList, that is filled. <br>
     */
    public ArrayList<ArrayList<Double>> fill2d(ArrayList<ArrayList<Double>> temp){
        for(int i = 0; i < this.order; i++){
            ArrayList<Double> tmp = new ArrayList<>();
            for(int j = 0; j < this.order; j++){
                tmp.add(0.0);
            }
            temp.add(tmp);
        }

        return temp;
    }

    /**
     * This method gets an integer ArrayList and fills it with zeros. <br>
     * @param temp ArrayList, that will be filled.<br>
     * @return ArrayList, that is filled. <br>
     */
    public ArrayList<Integer> fillInteger(ArrayList<Integer> temp){
        for(int i = 0; i < this.order; i++){
            temp.add(0);
        }

        return temp;
    }

    /**
     * This method gets double ArrayList and fills it with zeros. <br>
     * @param temp ArrayList, that will be filled.<br>
     * @return ArrayList, that is filled. <br>
     */
    public ArrayList<Double> fill(ArrayList<Double> temp){
        for(int i = 0; i < this.order; i++){
            temp.add(0.0);
        }

        return temp;
    }

    /**
     * This method is overridden from SolvingMethods interface <br>
     * to solve the equations. <br>
     * @return result list <br>
     */
    @Override
    public ArrayList<Double> solveEquation() {

        storeResult();

        ArrayList<ArrayList<Double>> inverse = new ArrayList<>();
        ArrayList<ArrayList<Double>> helper = new ArrayList<>();
        ArrayList<Integer> index = new ArrayList<>();

        inverse = fill2d(inverse);
        helper = fill2d(helper);
        index = fillInteger(index);

        for (int i=0; i<this.order; i++)
            helper.get(i).set(i, 1.0);

        matrix = upperTriangle(index);

        for (int i=0; i<this.order-1; i++)
            for (int j=i+1; j<this.order; j++)
                for (int k=0; k<this.order; k++)
                    helper.get(index.get(j)).set(k, helper.get(index.get(j)).get(k) - matrix.get(index.get(j)).get(i)*helper.get(index.get(i)).get(k));

        for (int i=0; i<this.order; ++i)
        {
            inverse.get(this.order-1).set(i, helper.get(index.get(this.order-1)).get(i)/matrix.get(index.get(this.order-1)).get(this.order-1));
            for (int j=this.order-2; j>=0; j--)
            {
                inverse.get(j).set(i, helper.get(index.get(j)).get(i));
                for (int k=j+1; k<this.order; k++) {
                    inverse.get(j).set(i, inverse.get(j).get(i) - matrix.get(index.get(j)).get(k)*inverse.get(k).get(i));
                }
                inverse.get(j).set(i, inverse.get(j).get(i) / matrix.get(index.get(j)).get(j));
            }
        }
        return matrixMult(inverse);
    }

    /**
     * This method uses the given matrix and returns <br>
     * an upper triangle matrix from it.<br>
     * @param index a helper, integer ArrayList.<br>
     * @return upper triangle matrix. <br>
     */
    public ArrayList<ArrayList<Double>> upperTriangle(ArrayList<Integer> index) {

        ArrayList<Double> t = new ArrayList<>();
        t = fill(t);

        for (int i = 0; i < this.order; ++i)
            index.set(i, i);

        for (int i = 0; i < this.order; i++) {
            double val = 0;
            for (int j=0; j < this.order; j++){
                double var = Math.abs(matrix.get(i).get(j));
                if (var > val)
                    val = var;
            }
            t.set(i, val);
        }

        int k = 0;
        for (int j=0; j < this.order-1; j++)
        {
            double val = 0;
            for (int i=j; i<this.order; ++i)
            {
                double var = Math.abs(matrix.get(index.get(i)).get(j));
                var /= t.get(index.get(i));
                if (var > val) {
                    val = var;
                    k = i;
                }
            }

            int temp = index.get(j);
            index.set(j, index.get(k));
            index.set(k, temp);

            for (int i = j + 1; i < this.order; i++) {
                double unk = matrix.get(index.get(i)).get(j)/matrix.get(index.get(j)).get(j);
                matrix.get(index.get(i)).set(j, unk);
                for (int z=j+1; z < this.order; z++)
                    matrix.get(index.get(i)).set(z, matrix.get(index.get(i)).get(z) - unk*matrix.get(index.get(j)).get(z));
            }
        }
        return matrix;
    }

    /**
     * This method performs a matrix multiplication and returns the <br>
     * results.<br>
     * @param inverse inverse matrix <br>
     * @return ArrayList of results <br>
     */
    private ArrayList<Double> matrixMult(ArrayList<ArrayList<Double>> inverse){
        ArrayList<Double> result = new ArrayList<>();

        for(int i = 0; i < this.order; i++){
            Double temp = 0.0;
            for(int j = 0; j < this.order; j++){
                temp += inverse.get(i).get(j) * resultMatrix.get(j);
            }
            result.add(temp);
        }

        return result;
    }
}

package sample;

import java.util.ArrayList;

/**
 * @author Erdem Duman <br>
 * This class solves equations with Gaussian method. <br>
 */
public class Gaussian implements SolvingMethods{
    private final String nameOfMethod = "Gaussian";

    private ArrayList<ArrayList<Double>> matrix;
    private int order;

    /**
     * Constructor of class. <br>
     * @param matrix the given matrix <br>
     * @param order number of variables, that will be solved <br>
     */

    public Gaussian(ArrayList<ArrayList<Double>> matrix, int order){
        this.matrix = matrix;
        this.order = order;
    }

    /**
     * This method returns the name of method.<br>
     * @return name of method <br>
     */
    @Override
    public String methodName(){return nameOfMethod;}

    /**
     * This method is overridden from SolvingMethods interface <br>
     * to solve the equations. <br>
     * @return result list <br>
     */
    @Override
    public ArrayList<Double> solveEquation(){
        int max_row;
        Double max_el;
        Double temp;
        Double t;
        ArrayList<Double> x = new ArrayList<>();

        for(int i = 0; i < order; i++){
            max_el = Math.abs(matrix.get(i).get(i));
            max_row = i;

            for(int k = i+1; k < order; k++){
                if(Math.abs(matrix.get(k).get(i)) > max_el){
                    max_el = Math.abs(matrix.get(k).get(i));
                    max_row = k;
                }
            }

            for(int k = i; k < order+1; k++){
                temp = matrix.get(max_row).get(k);
                matrix.get(max_row).set(k, matrix.get(i).get(k));
                matrix.get(i).set(k, temp);
            }

            for(int k = i+1; k < order; k++){
                t = -matrix.get(k).get(i)/matrix.get(i).get(i);
                for(int j = i; j < order+1; j++){
                    if(i == j){
                        matrix.get(k).set(j, 0.0);
                    }
                    else{
                        matrix.get(k).set(j, matrix.get(k).get(j) + t * matrix.get(i).get(j));
                    }
                }
            }
        }

        for(int i = 0; i < order; i++){
            x.add(0.0);
        }

        for(int i = order-1; i > -1; i--){
            x.set(i, matrix.get(i).get(order)/matrix.get(i).get(i));
            for(int k = i-1; k > -1; k--){
                matrix.get(k).set(order, matrix.get(k).get(order) - matrix.get(k).get(i)*x.get(i));
            }
        }

        return x;

    }
}

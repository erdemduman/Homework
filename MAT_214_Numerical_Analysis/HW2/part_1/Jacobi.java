import java.util.ArrayList;

/**
 * Created on 08/04/17.
 * @author Erdem Duman <br>
 */
public class Jacobi {

    private ArrayList<ArrayList<Double>> list;
    private final double E;
    private final int size;

    public Jacobi(ArrayList<ArrayList<Double>> param){
        list = param;
        E = 0.001;
        size = param.size()/param.get(0).size();
    }

    public void handle(){
        Double[] variables = new Double[size];

        for(int a = 0; a < size; a++)
            variables[a] = 0.0;

        operations(variables, 0);
    }

    public void operations(Double[] var, int iter){

        Double[] temp = new Double[size];
        double result;
        int flag = 0;

        for(int a = 0; a < size; a++){
            result = 0;
            for(int b = 0; b < size; b++){
                if(!(a == b)){
                    result += list.get(a).get(b)*-1*var[b];
                }
            }
            result += list.get(a).get(size);
            result = result/list.get(a).get(a);
            if((result - var[a])/result < E) {
                flag++;
            }
            temp[a] = result;
        }

        if(flag == size){
            System.out.println("Done!");
            return;
        }


        else if(iter > 100){
            System.out.println("There is no unique solution.");
            return;
        }

        else{
            printIteration(var,iter);
            for(int a = 0; a < size; a++){
                var[a] = temp[a];
            }
            iter++;
            operations(var, iter);
        }
    }

    public void printIteration(Double[] arr, int iter){
        System.out.println(iter + ". iteration:");
        for(int a = 0; a < size; a++){
            System.out.println("x" + (a+1) + " = " + arr[a]);
        }
        System.out.print("\n");
    }
}

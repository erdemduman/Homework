import java.util.ArrayList;

/**
 * Created on 08/04/17.
 * @author Erdem Duman <br>
 */
public class PivotingAndJacobi {
    public static void main(String [] args){

		int count = args.length;
		String arg0 = args[0];
		String arg1 = args[1];
		String arg2 = args[2];
		String arg3 = args[3];

		if(count != 4 || !(arg0.equals("-i")) || !(arg2.equals("-m"))){
			System.out.println("You need to type 'java javafilename -i inputfilename -m methodname'");
			return;
		}


        boolean flag = false;
        FileOperations fileObj = new FileOperations();
        ArrayList<ArrayList<Double>> list;
        list = new ArrayList<ArrayList<Double>>();
        list = fileObj.readFile(arg1);

        int size = list.size()/list.get(0).size();

        for(int a = 0; a < size; a++){
            if(list.get(a).size() != size+1)
                flag = true;
        }

        if(flag){
            System.out.println("There is a problem with variable numbers and equation numbers.");
        }

        else{
            System.out.println("Your equations are:");

            for(int a = 0; a < size; a++){
                for(int b = 0; b < size-1; b++){
                    System.out.print("(" + list.get(a).get(b) + " X" + (b+1) + ")" + " + ");
                }
                System.out.print("(" + list.get(a).get(size-1) + " X" + (size) + ")" + " = ");
                System.out.print(list.get(a).get(size));
                System.out.print("\n");
            }

            System.out.print("\n");
			if(arg3.equals("JCB")){
				Jacobi jacobi = new Jacobi(list);
            	jacobi.handle();
			}
			else if(arg3.equals("GESP")){
				Pivoting pivot = new Pivoting();
			}
            
        }

    }
}

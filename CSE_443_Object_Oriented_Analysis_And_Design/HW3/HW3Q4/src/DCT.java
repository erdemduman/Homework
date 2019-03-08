import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * @author Erdem Duman<br>
 * This class is the one, which implements abstract methods<br>
 * according to DCT rules.<br>
 */
public class DCT extends TemplateAbstract{

    /**
     * Constructor that matches super.<br>
     * @param filename file name of input <br>
     */
    public DCT(String filename) {
        super(filename, false);
    }


    /**
     * This method reads input numbers from a file and initialize<br>
     * those numbers to arrays.<br>
     */
    @Override
    protected void fileOperation() {
        BufferedReader br = null;
        try{
            br = new BufferedReader(new FileReader(filename));
            String line;
            int index = 0;
            while((line = br.readLine()) != null){
                N++;
            }

            br.close();
            br = new BufferedReader(new FileReader(filename));

            real = new Double[N];

            while((line = br.readLine()) != null){
                String[] tokens = line.split(",");
                real[index] = Double.parseDouble(tokens[0]);
                index++;
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            try {
                br.close();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
    /**
     * This method performs DFT operation. Does not prints the duration of<br>
     * process whether "option" is enabled. Option is fixed to "False" <br>
     * for this class.<br>
     */
    @Override
    protected void perform() {
        Double[] real_output = new Double[N];
        for (int i = 0; i < N; i++) {
            Double real_sum = 0.0;
            for (int j = 0; j < N; j++) {
                real_sum +=  real[j] * Math.cos((Math.PI/N)*(j+1/2)*i);
            }
            real_output[i] = real_sum;
        }
        outreal = real_output;
        /*for(int i = 0; i < N; i++){
            System.out.printf("%.2f\n", real_output[i]);
        }*/
    }

    /**
     * This method writes the result into a file called "out.txt"<br>
     */
    @Override
    protected void writeToFile() {
        BufferedWriter writer = null;
        try{
            writer = new BufferedWriter(new FileWriter("out.txt", false));
            writer.append("DCT\n");
            for (int i = 0; i < N; i++){
                writer.append(outreal[i].toString() + "\n");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            try {
                writer.flush();
                writer.close();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

    }
}

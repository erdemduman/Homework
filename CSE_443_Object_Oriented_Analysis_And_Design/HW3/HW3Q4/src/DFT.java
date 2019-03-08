import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * @author Erdem Duman<br>
 * This class is the one, which implements abstract methods<br>
 * according to DFT rules.<br>
 */
public class DFT extends TemplateAbstract{

    protected Double[] imag;
    protected Double[] outimag;

    /**
     * Constructor that matches super.<br>
     * @param filename file name of input <br>
     * @param option will be true whether counter will be used. False otherwise.<br>
     */
    public DFT(String filename, boolean option) {
        super(filename, option);
    }

    /**
     * This method reads input numbers from a file and initialize<br>
     * those numbers to arrays.<br>
     */
    @Override
    protected void fileOperation(){
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
            imag = new Double[N];

            while((line = br.readLine()) != null){
                String[] tokens = line.split(",");
                real[index] = Double.parseDouble(tokens[0]);
                imag[index] = Double.parseDouble(tokens[1]);
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
     * This method performs DFT operation. Also prints the duration of<br>
     * process whether "option" is enabled.<br>
     */
    @Override
    protected void perform() {
        long startTime = 0;
        if(this.option == true){
            startTime = System.nanoTime();
        }
        Double[] real_output = new Double[N];
        Double[] imag_output = new Double[N];
        for (int i = 0; i < N; i++) {
            Double real_sum = 0.0;
            Double imag_sum = 0.0;
            for (int j = 0; j < N; j++) {
                Double angle = 2 * Math.PI * j * i / N;
                real_sum +=  real[j] * Math.cos(angle) + imag[j] * Math.sin(angle);
                imag_sum += -real[j] * Math.sin(angle) + imag[j] * Math.cos(angle);
            }
            real_output[i] = real_sum;
            imag_output[i] = imag_sum;
        }

        outreal = real_output;
        outimag = imag_output;

        if(this.option == true){
            final long duration = System.nanoTime() - startTime;
            System.out.println("Duration: " + duration + " nanosecond");
        }
    }

    /**
     * This method writes the result into a file called "out.txt"<br>
     */
    @Override
    protected void writeToFile() {
        BufferedWriter writer = null;
        try{
            writer = new BufferedWriter(new FileWriter("out.txt", false));
            writer.append("DFT\n");
            for (int i = 0; i < N; i++){
                writer.append(outreal[i].toString() + "," + outimag[i].toString() + "\n");
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

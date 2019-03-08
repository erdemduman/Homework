/**
 * Created by Hakki Erdem Duman on 13/05/17.<br>
 */
public class Main {

    public static void main(String[]args){

        //These numbers are from the example of the book.

        double[] xCoordinates = {1.0,1.3,1.6,1.9,2.2};
        double[] yCoordinates={0.7651977,0.6200860,0.4554022,0.2818186,0.1103623};

        System.out.println("x coordinates:");

        for(double each : xCoordinates){
            System.out.printf("%.7f     ", each);
        }

        System.out.println("\n");

        System.out.println("y coordinates:");

        for(double each : yCoordinates){
            System.out.printf("%.7f     ", each);
        }

        System.out.println("\n");

        divDif(xCoordinates,yCoordinates,xCoordinates.length,xCoordinates.length);
    }

    public static void divDif(double[] xCoordinates, double[] yCoordinates,int size, int reps){

        int xIndex=1;
        int tempSize;
        int step = 1;
        tempSize=size-1;

        System.out.print("Step " + step + " = ");
        for(int a = 0; a < tempSize+1; a++){
            System.out.printf("%.7f     ",yCoordinates[a]);
        }
        System.out.println("\n");

        while(reps!=1){

            for(int a = 0; a < tempSize; a++)
                yCoordinates[a] = (yCoordinates[a+1]-yCoordinates[a])/(xCoordinates[a+xIndex]-xCoordinates[a]);

            --reps;
            --tempSize;
            ++xIndex;
            ++step;

            System.out.print("Step " + step + " = ");

            for(int a = 0; a < tempSize+1; a++){
                System.out.printf("%.7f     ",yCoordinates[a]);
            }
            System.out.println("\n");
        }
    }
}


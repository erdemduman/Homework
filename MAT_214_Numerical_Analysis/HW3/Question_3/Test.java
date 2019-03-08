/**
 * Created by Hakki Erdem Duman on 14/05/17.
 */
public class Test {

    public static void main(String[]args){

        // I controlled the program for degree = 1 from here: https://www.easycalculation.com/analytical/least-squares-regression-line-equation.php
        // And for degree = 2 from here: http://keisan.casio.com/exec/system/14059932254941

        int degree; //degree of equation.
        SingleLinkedList<Double> x_axis = new SingleLinkedList<>(); //x coordinates
        SingleLinkedList<Double> y_axis = new SingleLinkedList<>(); //y coordinates

        //here, we fill x axis of coordinates.
        x_axis.append(1.1);
        x_axis.append(1.2);
        x_axis.append(2.3);
        x_axis.append(3.4);
        x_axis.append(3.5);

        //here, we fill the y axis of coordinates.
        y_axis.append(0.9);
        y_axis.append(1.1);
        y_axis.append(1.3);
        y_axis.append(2.4);
        y_axis.append(3.5);

        degree = 2; //degree is given here.

        //if x axis number is not equal to y axis number, there is a trouble.
        if(x_axis.size() != y_axis.size()){
            System.out.println("There is a trouble with coordinates.");
        }

        else{
            double[] finalCoef = leastSqu(x_axis,y_axis,x_axis.size(),degree);
            int counter = finalCoef.length;
            System.out.println("\nCoefficients are here:");
            for (int i = counter - 1; i >= 0;i--)
                System.out.println("x^" + i + " = " + finalCoef[i]);

            System.out.println("\nEquation is:");
            System.out.print("y = ");
            for (int i=0;i<counter;i++)
                System.out.print(" (" + finalCoef[i] + ")" + "x^" + i);
            System.out.print("\n");
        }
    }

    public static double[] leastSqu(SingleLinkedList<Double> x_axis, SingleLinkedList<Double> y_axis, int dataNum, int degree){

        double[] finalCoef = null;
        try{

            SingleLinkedList<Double> storeX = new SingleLinkedList<>();
            SingleLinkedList<Double> storeY = new SingleLinkedList<>();

            System.out.println("\nNumber of Pairs: " + dataNum);

            System.out.println("\nCoordinates are here:");
            for (int i = 0;i < dataNum;i++){
                System.out.println("x" + i + ": " + x_axis.get(i) + "             y" + i + ": " + y_axis.get(i));
            }

            System.out.println("\nFit's polynomial degree: " + degree);

            double[][] AugMat = new double[degree+1][degree+2];
            finalCoef = new double[degree+1];

            for (int i = 0; i < 2*degree+1; i++)
            {
                storeX.append((double)0);
                for (int j=0;j<dataNum;j++)
                    storeX.set(storeX.get(i)+Math.pow(x_axis.get(j),i),i);
            }

            for (int i=0;i<=degree;i++)
                for (int j=0;j<=degree;j++)
                    AugMat[i][j]=storeX.get(i+j);

            for (int i=0;i<degree+1;i++)
            {
                storeY.append((double)0);
                for (int j = 0;j < dataNum; j++)
                    storeY.set(storeY.get(i)+Math.pow(x_axis.get(j),i)*y_axis.get(j),i);
            }
            for (int i = 0; i <= degree; i++)
                AugMat[i][degree+1]=storeY.get(i);
            degree=degree+1;

            for (int i=0;i<degree;i++)
                for (int k=i+1;k<degree;k++)
                    if (AugMat[i][i]<AugMat[k][i])
                        for (int j=0;j<=degree;j++)
                        {
                            double temp=AugMat[i][j];
                            AugMat[i][j]=AugMat[k][j];
                            AugMat[k][j]=temp;
                        }

            for (int i=0;i<degree-1;i++)
                for (int k=i+1;k<degree;k++)
                {
                    double t=AugMat[k][i]/AugMat[i][i];
                    for (int j=0;j<=degree;j++)
                        AugMat[k][j]=AugMat[k][j]-t*AugMat[i][j];
                }
            for (int i=degree-1;i>=0;i--)
            {
                finalCoef[i]=AugMat[i][degree];
                for (int j=0;j<degree;j++)
                    if (j!=i)
                        finalCoef[i]=finalCoef[i]-AugMat[i][j]*finalCoef[j];
                finalCoef[i]=finalCoef[i]/AugMat[i][i];
            }

        }
        catch(MyException e){
            System.out.println(e);
        }

        return finalCoef;
    }

}

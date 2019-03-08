import java.io.*;
import java.text.DecimalFormat;
import java.util.Locale;

import vpt.ByteImage;
import vpt.Image;
import vpt.algorithms.conversion.RGB2Gray;
import vpt.algorithms.histogram.Histogram;
import vpt.algorithms.io.Load;
import vpt.algorithms.mm.gray.GOpening;
import vpt.util.se.FlatSE;

public class Test14{

	private static double[] grayscale(Image img, int w, int h){
		double [] feature_vector = new double[1];

		int pixel = img.getXYByte(w,h);
		feature_vector[0] = pixel;

		return feature_vector;
	}

	private static boolean isAvailable(int image_w, int image_h, int w, int h){

	    if(w > 0 && h > 0 && image_w > w && image_h > h)
	        return true;

	    return false;
    }

	private static double[] histogram(Image img, int w, int h, int frameSize){
        double [] histogram = new double [256];
        int pixel;
        int border = frameSize/2;
        for(int x = w - border; x <= w + border; x++){
            for(int y = h - border; y <= h + border; y++){
                if(isAvailable(img.getXDim(), img.getYDim(), x, y)) {
                    pixel = img.getXYByte(x,y);
                    histogram[pixel]+= 1;
                }

            }
        }

        return histogram;
    }

    private static double[] flattenImg(Image arr, int frameSize){
	    double[] flatten = new double[frameSize*frameSize];
	    int flattenIndex = 0;
	    for(int w = 0; w < frameSize; w++){
	        for(int h = 0; h < frameSize; h++){
	            flatten[flattenIndex] = arr.getXYByte(w,h);
	            flattenIndex++;
            }
        }

	    return flatten;
    }

    private static double[] granulometry(Image img, int w, int h, int frameSize){
        Image temp = new ByteImage(frameSize, frameSize);
        int border = frameSize/2;
        for(int x = w - border; x <= w + border; x++){
            for(int y = h - border; y <= h + border; y++){
                if(isAvailable(img.getXDim(), img.getYDim(), x, y)) {
                    temp.setXYByte(w - x + border,y - h + border,img.getXYByte(x,y));

                }
            }
        }

        temp = GOpening.invoke(temp, FlatSE.square(3));

        return flattenImg(temp, frameSize);

    }

    private static double[] meanAndVariance(Image img, int w, int h, int frameSize){
		double [] mean_variance = new double [2];
		double meanVal = mean(img,w,h,frameSize);
		double varianceVal = variance(img,w,h,frameSize,meanVal);

		mean_variance[0] = meanVal;
		mean_variance[1] = varianceVal;

		return mean_variance;

	}

	private static double mean(Image img, int w, int h, int frameSize){
		int meanCounter = 0;
		int border = frameSize/2;
		for(int x = w - border; x <= w + border; x++){
			for(int y = h - border; y <= h + border; y++){
				if(isAvailable(img.getXDim(), img.getYDim(), x, y)) {
					meanCounter += img.getXYByte(x,y);
				}

			}
		}

		int totalElement = frameSize*frameSize;

		return meanCounter/totalElement;

	}

	private static double variance(Image img, int w, int h, int frameSize, double meanOfImg){
		int varianceCounter = 0;
		int border = frameSize/2;
		for(int x = w - border; x <= w + border; x++){
			for(int y = h - border; y <= h + border; y++){
				if(isAvailable(img.getXDim(), img.getYDim(), x, y)) {
					varianceCounter += Math.pow(img.getXYByte(w,h) - meanOfImg,2);
				}

			}
		}

		return Math.sqrt(varianceCounter)/(frameSize*frameSize);
	}

	private static double[] describe(Image img,int w,int h) {

		double[] feature_vector = null;

		//feature_vector = grayscale(img,w,h);
		//feature_vector = histogram(img,w,h,9);
		feature_vector = meanAndVariance(img,w,h,9);
        //feature_vector = granulometry(img,w,h,9);

		return feature_vector;
	}

	public static void main(String[] args) {
		Locale.setDefault(new Locale("en", "US"));
		DecimalFormat df = new DecimalFormat("#.######");


		String folderName = "Granulometry";
		String img_path = "pan.png";
		String train_path = "train.png";
		String gt_path = "GT.png";
		new File(folderName).mkdir();
		boolean first = true;


		try {
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(folderName + "/train.arff")));
			PrintWriter pw2 = new PrintWriter(new BufferedWriter(new FileWriter(folderName + "/test.arff")));

			Image img = Load.invoke(img_path);
			Image train = Load.invoke(train_path);
			double[] feature = null;
			for(int h=0; h<img.getYDim(); h++)
			{
				for(int w=0; w<img.getXDim(); w++)
				{
					if(train.getXYByte(w,h) == 0)
						continue;
					feature = describe(img,w,h);
					if(first == true) {
						printHeader(pw, feature.length);
						printHeader(pw2, feature.length);
						first = false;
					}

					for(int j = 0; j < feature.length; j++)
						pw.print(df.format(feature[j])+",");

					pw.println(train.getXYByte(w,h));

					System.err.println(h + " " + w + " train");
				}
			}
			pw.close();

			Image pan = Load.invoke(img_path);
			Image gt = Load.invoke(gt_path);
			feature = null;
			for(int h=0; h<pan.getYDim(); h++)
			{
				for(int w=0; w<pan.getXDim(); w++)
				{
					if(gt.getXYByte(w,h) == 0)
						continue;
					feature = describe(pan,w,h);
					for(int j = 0; j < feature.length; j++)
						pw2.print(df.format(feature[j])+",");

					pw2.println(gt.getXYByte(w,h));

					System.err.println(h + " " + w + " test");
				}
			}
			pw2.close();
		} catch (IOException e) {
			e.printStackTrace();
		}


	}

	// *** There is nothing to modify beyond this line *** //

	private static void printHeader(PrintWriter pw, int flength){
		pw.println("@RELATION histogram");
		for(int i = 1; i <= flength; i++)
			pw.println("@ATTRIBUTE o" + i +"	REAL");
		pw.println("@ATTRIBUTE o 	{1,2,3,4,5,6}");
		pw.println("@DATA");
	}
}
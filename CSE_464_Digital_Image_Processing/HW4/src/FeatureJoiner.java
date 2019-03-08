import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * Joins 2 arff files
 * 
 * @author yoktish
 *
 */
public class FeatureJoiner
{
	public static void main(String[] args){
		try{
			String dir1 = "test1";
			String dir2 = "test2";
			String output = "test3";
			
			// create the output directory
			new File(output).mkdir();
			
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(output + "/train.arff")));
			
			BufferedReader br1 = new BufferedReader(new FileReader(dir1 + "/train.arff"));
			BufferedReader br2 = new BufferedReader(new FileReader(dir2 + "/train.arff"));
			
			// read all lines up until the dimensions' description
			int dim1 = 0;
			
			String dimDescription = null;
			
			String s = null;
			
			while((s = br1.readLine()) != null){
				dim1++;
				if(s.startsWith("@ATTRIBUTE o ")){
					dimDescription = s;
					break;
				}
			}
			
			dim1 -=2; // minus the first and description lines to get the feature size
			
			// now get the feature size of the second file
			int dim2 = 0;
			
			while((s = br2.readLine()) != null){
				dim2++;
				if(s.startsWith("@ATTRIBUTE o ")){
					break;
				}
			}
			dim2 -=2; // minus the first and description lines to get the feature size
			
			int dimensions = dim1 + dim2;
			
			printHeader(pw, dimensions, dimDescription);
			
			// read every line and join them
			String s1 = null;
			String s2 = null;
			
			// get rid of the "data" lines
			br1.readLine();
			br2.readLine();
			
			while((s1 = br1.readLine()) != null && (s2 = br2.readLine()) != null){
				// remove the class id from the end
				s1 = s1.substring(0, s1.lastIndexOf(','));
				pw.println(s1 + ',' + s2);
			}
			pw.close();
			br1.close();
			br2.close();
			
			// now the first test file
			pw = new PrintWriter(new BufferedWriter(new FileWriter(output + "/test1a.arff")));
			
			printHeader(pw, dimensions, dimDescription);
			
			br1 = new BufferedReader(new FileReader(dir1 + "/test1a.arff"));
			br2 = new BufferedReader(new FileReader(dir2 + "/test1a.arff"));
			
			// read every line and join them
			s1 = null;
			s2 = null;
			
			// first ignore the header of each
			while((s = br1.readLine()) != null){
				if(s.startsWith("@DATA"))
					break;
			}
			
			while((s = br2.readLine()) != null){
				if(s.startsWith("@DATA"))
					break;
			}
			
			while((s1 = br1.readLine()) != null && (s2 = br2.readLine()) != null){
				// remove the class id from the end
				s1 = s1.substring(0, s1.lastIndexOf(','));
				pw.println(s1 + ',' + s2);
			}
			pw.close();
			br1.close();
			br2.close();
			
			// now the second test file
			pw = new PrintWriter(new BufferedWriter(new FileWriter(output + "/test1b.arff")));
			
			printHeader(pw, dimensions, dimDescription);
			
			br1 = new BufferedReader(new FileReader(dir1 + "/test1b.arff"));
			br2 = new BufferedReader(new FileReader(dir2 + "/test1b.arff"));
			
			// read every line and join them
			s1 = null;
			s2 = null;
			
			// first ignore the header of each
			while((s = br1.readLine()) != null){
				if(s.startsWith("@DATA"))
					break;
			}
			
			while((s = br2.readLine()) != null){
				if(s.startsWith("@DATA"))
					break;
			}
			
			while((s1 = br1.readLine()) != null && (s2 = br2.readLine()) != null){
				// remove the class id from the end
				s1 = s1.substring(0, s1.lastIndexOf(','));
				pw.println(s1 + ',' + s2);
			}
			pw.close();
			br1.close();
			br2.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private static void printHeader(PrintWriter pw, int dimensions, String description){
		pw.println("@RELATION deneme");
		
		for(int i = 1; i <= dimensions; i++)
			pw.println("@ATTRIBUTE o" + i +"	REAL");
		
		pw.println(description);
		
		pw.println("@DATA");
	}
}
import java.util.ArrayList;
import java.util.Random;
/**
 * This test class generates some random strings from the given<br>
 * file. And then prints, encodes and decodes those strings.<br>
 * @author Hakki Erdem Duman. <br>
 */
public class Homework6Q2 {
    public static void main(String[]args){

        FileOperations fileObj = new FileOperations();
        HuffmanTree.HuffData[] arr = fileObj.readFile("freq.txt");

        HuffmanTree huffmanObj = new HuffmanTree();
        huffmanObj.buildTree(arr);

        //The test part
        ArrayList<Character> charArr= new ArrayList<Character>();
        for(HuffmanTree.HuffData each : arr){
            charArr.add(each.getSymbol());
        }

        for(int a = 0; a < 5; a++){
            String test = "";
            Random random = new Random();
            Integer max = random.nextInt(arr.length);
            max++;
            for(int b = 0; b < max; b++){
                Integer letter = random.nextInt(charArr.size());
                test += charArr.get(letter);
            }

            System.out.println("String: " + test);
            System.out.println("Encode: " + huffmanObj.encode(test));
            System.out.println("Decode: " + huffmanObj.decode(huffmanObj.encode(test)));
            System.out.print("\n");
        }
    }
}

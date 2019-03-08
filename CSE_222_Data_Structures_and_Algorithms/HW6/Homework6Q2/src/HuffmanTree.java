import java.util.*;
import java.io.*;

/** Class to represent and build a Huffman tree.
 *   @author Hakki Erdem Duman
 * */

public class HuffmanTree
        implements Serializable {

    // Nested Classes
    /** A datum in the Huffman tree. */
    public static class HuffData
            implements Serializable {
        // Data Fields
        /** The weight or probability assigned to this HuffData. */
        private double weight;

        /** The alphabet symbol if this is a leaf. */
        private Character symbol;

        public HuffData(double weight, Character symbol) {
            this.weight = weight;
            this.symbol = symbol;
        }

        public Character getSymbol(){return symbol;}
        public double getWeight(){return weight;}
    }

    // Data Fields
    /** A reference to the completed Huffman tree. */
    private BinaryTree < HuffData > huffTree;

    String willBeReturned;

    /** A Comparator for Huffman trees; nested class. */
    private static class CompareHuffmanTrees
            implements Comparator < BinaryTree < HuffData >> {
        /** Compare two objects.
         @param treeLeft The left-hand object
         @param treeRight The right-hand object
         @return -1 if left less than right,
         0 if left equals right,
         and +1 if left greater than right
         */
        public int compare(BinaryTree < HuffData > treeLeft,
                           BinaryTree < HuffData > treeRight) {
            double wLeft = treeLeft.getData().weight;
            double wRight = treeRight.getData().weight;
            return Double.compare(wLeft, wRight);
        }
    }

    /** Builds the Huffman tree using the given alphabet and weights.
     post:  huffTree contains a reference to the Huffman tree.
     @param symbols An array of HuffData objects
     */
    public void buildTree(HuffData[] symbols) {
        Queue < BinaryTree < HuffData >> theQueue
                = new PriorityQueue < BinaryTree < HuffData >>
                (symbols.length, new CompareHuffmanTrees());
        // Load the queue with the leaves.
        for (HuffData nextSymbol : symbols) {
            BinaryTree < HuffData > aBinaryTree =
                    new BinaryTree < HuffData > (nextSymbol, null, null);
            theQueue.offer(aBinaryTree);
        }

        // Build the tree.
        while (theQueue.size() > 1) {
            BinaryTree < HuffData > left = theQueue.poll();
            BinaryTree < HuffData > right = theQueue.poll();
            double wl = left.getData().weight;
            double wr = right.getData().weight;
            HuffData sum = new HuffData(wl + wr, null);
            BinaryTree < HuffData > newTree =
                    new BinaryTree < HuffData > (sum, left, right);
            theQueue.offer(newTree);
        }

        // The queue should now contain only one item.
        huffTree = theQueue.poll();
    }

    /**
     * This method gets a string and calls the other encode method<br>
     * to encode the given string.<br>
     * @param willBeEncoded the given string.<br>
     * @return encoded string.<br>
     * @author Hakki Erdem Duman <br>
     */
    public String encode(String willBeEncoded){

        willBeReturned = "";

        for(int a = 0; a < willBeEncoded.length(); a++){
            BinaryTree < HuffData > temp = huffTree;
            String tempStr = "";
            encode(willBeEncoded.charAt(a),temp,tempStr);
        }

        return willBeReturned;
    }

    /**
     * This method encodes the given string.<br>
     * @param letter a letter of the given string.<br>
     * @param temp temporary tree.<br>
     * @param tempStr encoded data of the given letter.<br>
     * @author Hakki Erdem Duman <br>
     */
    private void encode(Character letter,BinaryTree < HuffData > temp, String tempStr){

        if(temp.getData().getSymbol() != null){
            if(temp.getData().getSymbol() == letter){
                willBeReturned += tempStr;
            }
        }

        else{
            encode(letter, temp.getLeftSubtree(), tempStr + "0");
            encode(letter, temp.getRightSubtree(), tempStr + "1");
        }

    }

    /** Method to decode a message that is input as a string of
     digit characters '0' and '1'.
     @param codedMessage The input message as a String of
     zeros and ones.
     @return The decoded message as a String
     */
    public String decode(String codedMessage) {
        StringBuilder result = new StringBuilder();
        BinaryTree < HuffData > currentTree = huffTree;
        for (int i = 0; i < codedMessage.length(); i++) {
            if (codedMessage.charAt(i) == '1') {
                currentTree = currentTree.getRightSubtree();
            }
            else {
                currentTree = currentTree.getLeftSubtree();
            }
            if (currentTree.isLeaf()) {
                HuffData theData = currentTree.getData();
                result.append(theData.symbol);
                currentTree = huffTree;
            }
        }
        return result.toString();
    }

    /**
     * This method creates a HuffData object.<br>
     * @param value weight of character.<br>
     * @param data character.<br>
     * @return the created object.<br>
     */
    public HuffData createInner(double value, Character data){
        HuffData obj = new HuffData(value, data);

        return obj;
    }
}

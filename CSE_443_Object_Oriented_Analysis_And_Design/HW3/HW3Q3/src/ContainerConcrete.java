/**
 * @author Erdem Duman<br>
 * This class implements Container interface to make user<br>
 * access iterator.<br>
 */

public class ContainerConcrete implements Container {

    private Object[][] arr;

    /**
     * Constructor of class.<br>
     * @param arr 2D array<br>
     */
    public ContainerConcrete(Object[][] arr){
        this.arr = arr;
    }

    /**
     * This method returns an iterator to be used.<br>
     * @return iterator <br>
     */
    @Override
    public Iterator getIterator() {
        return new InnerIterator();
    }

    /**
     * @author Erdem Duman<br>
     * This inner class implements Iterator interface to iterate a 2D array.<br>
     */
    private class InnerIterator implements Iterator{

        private int index_x;
        private int index_y;
        private int state;
        private int border_x;
        private int border_y;
        private int generalCounter;

        /**
         * Constructor of class.<br>
         */
        private InnerIterator(){
            this.border_x = 0;
            this.border_y = 0;
            this.state = 1;
            this.index_x = 0;
            this.index_y = 0;
            this.generalCounter = 0;
        }

        /**
         * This method checks whether there is still element in<br>
         * array to iterate.<br>
         * @return true whether there is element to iterate, false otherwise.<br>
         */
        @Override
        public boolean hasNext() {
            if(this.generalCounter < arr[0].length * arr.length){
                return true;
            }
            return false;
        }

        /**
         * This method returns the following element of 2D array.<br>
         * In this example, array is iterated clockwise.<br>
         * @return the following element <br>
         */
        @Override
        public Object next() {
            Object returnable = null;
            if(this.hasNext()){
                returnable = arr[index_x][index_y];
                if(state == 1){
                    index_y++;
                    if(index_y == (arr[0].length - border_y) - 1){
                        state = 2;
                    }
                }
                else if(state == 2){
                    index_x++;
                    if(index_x == (arr.length - border_x) - 1){
                        state = 3;
                    }
                }

                else if(state == 3){
                    index_y--;
                    if(index_y == border_y){
                        state = 4;
                    }
                }

                else if(state == 4){
                    index_x--;
                    if(index_x == border_x){
                        border_x++;
                        border_y++;
                        index_x = border_x;
                        index_y = border_y;
                        state = 1;
                    }
                }
            }

            this.generalCounter++;
            return returnable;
        }
    }
}

/**
 * @author Erdem Duman <br>
 * This class implements ModernPayment's payment method. <br>
 */
public class CardA implements ModernPayment {
    /**
     * This method is overridden from ModernPayment interface to pay.<br>
     * It's the new, modern version.<br>
     * @param cardNo number of card <br>
     * @param amount amount of payment <br>
     * @param destination destination of payment <br>
     * @param installments installments of payment <br>
     * @return 0
     */
    @Override
    public int pay(String cardNo, float amount, String destination, String installments) {
        System.out.println("Payment: Modern Payment");
        System.out.println("Card No: " + cardNo);
        System.out.println("Amount: " + amount);
        System.out.println("Destination: " + destination);
        System.out.println("Installments: " + installments);
        return 0;
    }
}

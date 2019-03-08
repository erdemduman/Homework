/**
 * @author Erdem Duman <br>
 * This class implements TurboPayments's payment method. <br>
 */
public class CardB implements TurboPayment {
    /**
     * This method is overridden from TurboPayment interface to pay.<br>
     * It's the old, deprecated version.<br>
     * @param turboCardNo number of card <br>
     * @param turboAmount amount of payment <br>
     * @param destinationTurboOfCourse destination of payment <br>
     * @param installmentsButInTurbo installments of payment <br>
     * @return 0
     */
    @Override
    public int payInTurbo(String turboCardNo, float turboAmount, String destinationTurboOfCourse, String installmentsButInTurbo) {
        System.out.println("Payment: Turbo Payment");
        System.out.println("Card No: " + turboCardNo);
        System.out.println("Amount: " + turboAmount);
        System.out.println("Destination: " + destinationTurboOfCourse);
        System.out.println("Installments: " + installmentsButInTurbo);
        return 0;
    }
}

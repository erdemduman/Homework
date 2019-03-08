/**
 * @author Erdem Duman <br>
 * This is an adapter class that helps TurboPayment to<br>
 * adapt ModernPayment.<br>
 */

public class PaymentAdapter implements TurboPayment {

    ModernPayment mp;

    /**
     * Constructor of class.<br>
     * @param mp gets a ModernPayment object to use its payment method.<br>
     */
    public PaymentAdapter(ModernPayment mp){
        this.mp = mp;
    }

    /**
     * This method is overridden from TurboPayment but ModernPayment's payment<br>
     * method is called inside of it.<br>
     * @param turboCardNo number of card <br>
     * @param turboAmount amount of payment <br>
     * @param destinationTurboOfCourse destination of payment <br>
     * @param installmentsButInTurbo installments of payment <br>
     * @return ModernPayment's return <br>
     */
    @Override
    public int payInTurbo(String turboCardNo, float turboAmount, String destinationTurboOfCourse, String installmentsButInTurbo) {
        return mp.pay(turboCardNo, turboAmount, destinationTurboOfCourse, installmentsButInTurbo);
    }
}

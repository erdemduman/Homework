/**
 * @author Erdem Duman <br>
 * This interface keeps the payment method of Modern Payment. <br>
 */
public interface ModernPayment {
    int pay(String cardNo, float amount, String destination, String installments);
}

/**
 * @author Erdem Duman <br>
 * This interface keeps the payment method of Turbo Payment, which is the old one. <br>
 */
public interface TurboPayment {
    int payInTurbo(String turboCardNo, float turboAmount, String destinationTurboOfCourse, String installmentsButInTurbo);
}

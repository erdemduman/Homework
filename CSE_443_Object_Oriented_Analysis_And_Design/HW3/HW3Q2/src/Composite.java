/**
 * @author Erdem Duman <br>
 * This interface is written to be implemented by<br>
 * AddressBook class.<br>
 *
 */
public interface Composite {
    void add(Composite c);
    void remove(Composite c);
    String getName();
    String getMail();
}

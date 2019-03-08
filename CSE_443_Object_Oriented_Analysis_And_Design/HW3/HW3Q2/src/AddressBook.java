import java.util.ArrayList;
import java.util.List;

/**
 * @author Erdem Duman <br>
 * This class implements Composite interface and<br>
 * provides to create address books with composite<br>
 * design pattern.<br>
 */

public class AddressBook implements Composite{
    private String name;
    private String mail;
    /**
     * This list keeps sub users of an object, which is generated<br>
     * by this class.<br>
     */
    private List<Composite> compositeList;

    /**
     * Constructor of class.<br>
     * @param mail mail of user <br>
     * @param name name of user <br>
     */
    public AddressBook(String mail, String name){
        this.name = name;
        this.mail = mail;
        compositeList = new ArrayList<>();
    }

    /**
     * This method adds user to the list<br>
     * @param c Composite object to be added<br>
     */
    @Override
    public void add(Composite c) {
        compositeList.add(c);
    }

    /**
     * This method removes user from the list<br>
     * @param c Composite object to be removed<br>
     */
    @Override
    public void remove(Composite c) {
        compositeList.remove(c);
    }

    /**
     * This method gets name of the user.<br>
     * @return name of user.<br>
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * This method gets mail of the user.<br>
     * @return mail of user.<br>
     */
    @Override
    public String getMail() {
        return mail;
    }

    /**
     * This method is overridden from Object class.<br>
     * Returns itself whether there is no sub user of the object.<br>
     * Returns itself and its sub users whether there are sub users.<br>
     * @return members text.<br>
     */
    @Override
    public String toString(){
        String str = "";
        if(compositeList.isEmpty()){
            str = this.mail + " " + this.name + "\n";
        } else{
            str = this.mail + " " + this.name + "\n";
            for(Composite c: compositeList){
                str += "\t" + c.getMail() + " " + c.getName() + "\n";
            }
        }
        return str;
    }
}

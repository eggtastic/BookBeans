package src.main.java.user;

public class Customer extends User {
    boolean dining;
    boolean watching;
    boolean paid;
    boolean served;

    Customer(String username, String password, boolean dining, boolean watching, boolean paid, boolean served) {
        super(username, password);
        this.dining = dining;
        this.watching = watching;
        this.paid = paid;
        this.served = served;
    }
}

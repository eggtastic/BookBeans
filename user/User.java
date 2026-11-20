package user;

abstract class User {
    String username;
    String password;

    User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

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

public class Admin extends User {

}

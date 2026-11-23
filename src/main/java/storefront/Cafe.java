package storefront;

import java.util.ArrayList;

import product.Consumables;
import product.Product;


public class Cafe extends Storefront {

    // Number of different menu products
    private int MenuItems;

    private ArrayList<Consumables> Menu = new ArrayList<>();


    public int getMenuItems() {
        return MenuItems;
    }


    public Cafe(String name, double earnings, int customers,
         int MenuItems) {

        super(name, earnings, customers);

        this.MenuItems = MenuItems;
    }


    // CREATE

    public void addConsumable(Consumables consumable) {
        Menu.add(consumable);
        MenuItems++;
    }

    // UPDATE

    public void updateConsumable(Consumables consumable,
                                String name, double price, int stock) {

        consumable.setName(name);
        consumable.setPrice(price);
        consumable.setStock(stock);
    }
    // DELETE

    public void deleteConsumable(Consumables consumable) {
        if (Menu.remove(consumable)) {
            MenuItems--;
        }
    }


    // READ

    public ArrayList<Consumables> getMenu() {
        return Menu;
    }


    @Override
    public void displayStorefront() {

        for (Consumables item : Menu) {
            item.displayProduct();
        }
    }


    // BUY

    @Override
    public void BuyProduct(Product product) {

        if (product instanceof Consumables) {

            if (product.getStock() > 0) {

                product.reduceStock();

                setEarnings(getEarnings() + product.getPrice());

                System.out.println(
                    "Thank you for your purchase of " +
                    product.getName()
                );

            } else {

                System.out.println(
                    "Sorry, " + product.getName() +
                    " is out of stock."
                );
            }

        } else {

            System.out.println(
                "This product is not sold in the cafe."
            );
        }
    }
}
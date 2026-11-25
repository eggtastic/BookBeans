package storefront;

import java.util.ArrayList;

import product.Consumables;
import product.Product;


public class Cafe extends Storefront {

    // Number of different menu products
    private int MenuItems;

    private ArrayList<Consumables> Menu =
        new ArrayList<>();


    public Cafe(
        String name,
        double earnings,
        int MenuItems
    ) {

        super(name, earnings);

        this.MenuItems = MenuItems;
    }


    // =====================================================
    // READ
    // =====================================================

    public int getMenuItems() {
        return MenuItems;
    }


    public ArrayList<Consumables> getMenu() {
        return Menu;
    }


    // =====================================================
    // CREATE
    // =====================================================

    @Override
    public void addProduct(Product product) {

        if (product instanceof Consumables) {

            Menu.add((Consumables) product);
            MenuItems++;
        }
    }


    // =====================================================
    // UPDATE
    // =====================================================

    @Override
    public void updateProduct(Product product) {

        if (product instanceof Consumables) {

            Consumables consumable =
                (Consumables) product;

            if (!Menu.contains(consumable)) {
                Menu.add(consumable);
            }
        }
    }


    // =====================================================
    // DELETE
    // =====================================================

    @Override
    public void deleteProduct(Product product) {

        if (product instanceof Consumables) {

            if (Menu.remove((Consumables) product)) {
                MenuItems--;
            }
        }
    }


    // =====================================================
    // DISPLAY STOREFRONT
    // =====================================================

    @Override
    public void displayStorefront() {

        for (Consumables item : Menu) {
            item.displayProduct();
        }
    }


    // =====================================================
    // BUY
    // =====================================================

    @Override
    public void BuyProduct(Product product) {

        if (product instanceof Consumables) {

            if (product.getStock() > 0) {

                product.reduceStock();

                setEarnings(
                    getEarnings()
                    + product.getPrice()
                );
            }
        }
    }
}
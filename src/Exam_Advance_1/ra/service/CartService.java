package Exam_Advance_1.ra.service;

import Exam_Advance_1.ra.model.CartItem;
import Exam_Advance_1.ra.model.Product;

import java.util.ArrayList;
import java.util.List;

public class CartService {
    private List<CartItem> cartItems;

    public CartService() {
        cartItems = new ArrayList<>();
    }

    public void displayAllProducts() {

        for (CartItem cartItem : cartItems) {
            Product product = cartItem.getProduct();
            System.out.println("Product ID: " + product.getProductId());
            System.out.println("Name: " + product.getProductName());
            System.out.println("Price: " + product.getProductPrice());
            System.out.println("-----------------------");
        }
    }

    public void addProductToCart(int productId) {

        Product product = getProductById(productId);
        if (product != null) {
            boolean found = false;
            for (CartItem cartItem : cartItems) {
                if (cartItem.getProduct().getProductId().equals(String.valueOf(productId))) {
                    int newQuantity = cartItem.getQuantity() + 1;
                    if (checkStockAvailability(cartItem.getProduct(), newQuantity)) {
                        cartItem.setQuantity(newQuantity);
                        System.out.println("Product added to cart.");
                    } else {
                        System.out.println("Insufficient stock.");
                    }
                    found = true;
                    break;
                }

            }
            if (!found) {
                if (checkStockAvailability(product, 1)) {
                    CartItem newCartItem = new CartItem(cartItems.size() + 1, product, product.getProductPrice(), 1);
                    cartItems.add(newCartItem);
                    System.out.println("Product added to cart.");
                } else {
                    System.out.println("Insufficient stock.");
                }
            }
        } else {
            System.out.println("Invalid product ID.");
        }
    }

    public void displayCartItems() {

        if (cartItems.isEmpty()) {
            System.out.println("Cart is empty.");
        } else {
            for (CartItem cartItem : cartItems) {
                Product product = cartItem.getProduct();
                System.out.println("Cart Item ID: " + cartItem.getCartItemId());
                System.out.println("Product ID: " + product.getProductId());
                System.out.println("Name: " + product.getProductName());
                System.out.println("Price: " + product.getProductPrice());
                System.out.println("Quantity: " + cartItem.getQuantity());
                System.out.println("-----------------------");
            }
        }
    }

    public void updateCartItemQuantity(int cartItemId, int newQuantity) {

        boolean found = false;
        for (CartItem cartItem : cartItems) {
            if (cartItem.getCartItemId() == cartItemId) {
                Product product = cartItem.getProduct();
                if (checkStockAvailability(product, newQuantity)) {
                    cartItem.setQuantity(newQuantity);
                    System.out.println("Quantity updated successfully.");
                } else {
                    System.out.println("Insufficient stock.");
                }
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Invalid cart item ID.");
        }
    }

    public void removeCartItem(int cartItemId) {

        boolean found = false;
        for (CartItem cartItem : cartItems) {
            if (cartItem.getCartItemId() == cartItemId) {
                Product product = cartItem.getProduct();
                int removedQuantity = cartItem.getQuantity();
                cartItems.remove(cartItem);
                adjustStock(product, removedQuantity);
                System.out.println("Product removed from cart.");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Invalid cart item ID.");
        }
    }

    public void clearCart() {

        cartItems.clear();
        System.out.println("Cart cleared.");
    }

    private boolean checkStockAvailability(Product product, int quantity) {

        return product.getStock() >= quantity;
    }

    private void adjustStock(Product product, int quantity) {

        product.setStock(product.getStock() + quantity);
    }

    private Product getProductById(int productId) {
        return null;
    }
}

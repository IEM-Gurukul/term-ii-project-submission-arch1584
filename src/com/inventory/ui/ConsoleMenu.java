package com.inventory.ui;

import java.util.*;
import com.inventory.model.Product;
import com.inventory.dao.FileItemDAO;
import com.inventory.service.InventoryService;
import com.inventory.exception.ItemNotFoundException;
import com.inventory.exception.InsufficientStockException;
import com.inventory.exception.DuplicateSKUException;

public class ConsoleMenu {
  public static void main(String[] args) {

      FileItemDAO dao = new FileItemDAO();
      InventoryService service = new InventoryService(dao);
      Scanner scanner = new Scanner(System.in);
      int choice = 0;


      System.out.println("~   Welcome to Inventory Management System   ~");


      while (choice != 9) {
          System.out.println("\n--- MENU ---");
          System.out.println("1. Add Product");
          System.out.println("2. Delete Product");
          System.out.println("3. Update Product");
          System.out.println("4. Reduce Stock");
          System.out.println("5. Restock Product");
          System.out.println("6. Find Product");
          System.out.println("7. View All Products");
          System.out.println("8. Total Products");
          System.out.println("9. Exit");
          System.out.print("Enter choice: ");


          if (!scanner.hasNextInt()) {
              System.out.println("Invalid input. Please enter a number.");
              scanner.next();
              continue;
          }

          choice = scanner.nextInt();
          scanner.nextLine(); 

          switch (choice) {

              case 1:
                  // Add Product;
                  System.out.print("Enter Name: ");
                  String name = scanner.nextLine();
                  System.out.print("Enter SKU: ");
                  String sku = scanner.nextLine();
                  System.out.print("Enter Price: ");
                  double price = scanner.nextDouble();
                  scanner.nextLine();
                  System.out.print("Enter Quantity: ");
                  int quantity = scanner.nextInt();
                  scanner.nextLine();
                  System.out.print("Enter Description: ");
                  String description = scanner.nextLine();
                  try {
                      boolean added = service.addProduct(name, sku, price, quantity, description);
                      if (added) System.out.println("Product added successfully!");
                  } catch (DuplicateSKUException e) {
                      System.out.println("Error: " + e.getMessage());
                  }
                  break;

              case 2:
                  // Delete Product
                  System.out.print("Enter SKU to delete: ");
                  String deleteSKU = scanner.nextLine();
                  try {
                      boolean deleted = service.deleteProduct(deleteSKU);
                      if (deleted) System.out.println("Product deleted successfully!");
                  } catch (ItemNotFoundException e) {
                      System.out.println("Error: " + e.getMessage());
                  }
                  break;

              case 3:
                  // Update Product
                  System.out.print("Enter SKU to update: ");
                  String updateSKU = scanner.nextLine();
                  if (service.findProduct(updateSKU) == null) {
                    System.out.println("Error: Product not found: " + updateSKU);
                    break;
                  }
                  System.out.print("Enter new Price: ");
                  double newPrice = scanner.nextDouble();
                  scanner.nextLine();
                  System.out.print("Enter new Description: ");
                  String newDescription = scanner.nextLine();
                  try {
                      boolean updated = service.updateProduct(updateSKU, newPrice, newDescription);
                      if (updated) System.out.println("Product updated successfully!");
                  } catch (ItemNotFoundException e) {
                      System.out.println("Error: " + e.getMessage());
                  }
                  break;

              case 4:
                  // Reduce Stock
                  System.out.print("Enter SKU: ");
                  String reduceSKU = scanner.nextLine();
                  System.out.print("Enter quantity to reduce: ");
                  int reduceQty = scanner.nextInt();
                  scanner.nextLine();
                  try {
                      boolean reduced = service.reduceStock(reduceSKU, reduceQty);
                      if (reduced) {
                        System.out.println("Stock reduced successfully!");
                        Product updatedProduct = service.findProduct(reduceSKU);
                        if (updatedProduct != null) updatedProduct.display();
                      }
                      else System.out.println("Quantity cannot be zero.");
                  } catch (ItemNotFoundException e) {
                      System.out.println("Error: " + e.getMessage());
                  } catch (InsufficientStockException e) {
                      System.out.println("Error: " + e.getMessage());
                  }
                  break;

              case 5:
                  // Restock Product
                  System.out.print("Enter SKU: ");
                  String restockSKU = scanner.nextLine();
                  System.out.print("Enter quantity to restock: ");
                  int restockQty = scanner.nextInt();
                  scanner.nextLine();
                  try {
                      boolean restocked = service.restockProduct(restockSKU, restockQty);
                      if (restocked) {
                        System.out.println("Stock restocked successfully!");
                        Product updatedProduct = service.findProduct(restockSKU);
                        if (updatedProduct != null) updatedProduct.display();
                      }
                      else System.out.println("Quantity cannot be zero.");
                  } catch (ItemNotFoundException e) {
                      System.out.println("Error: " + e.getMessage());
                  }
                  break;

              case 6:
                  // Find Product
                  System.out.print("Enter SKU to find: ");
                  String findSKU = scanner.nextLine();
                  Product found = service.findProduct(findSKU);
                  if (found != null) {
                      found.display();
                  } else {
                      System.out.println("Product not found.");
                  }
                  break;

              case 7:
                  // View All Products
                  List<Product> allProducts = service.getAllProducts();
                  if (allProducts.isEmpty()) {
                      System.out.println("No products in inventory.");
                  } else {
                      System.out.println("\n--- All Products ---");
                      for (Product p : allProducts) {
                          p.display();
                      }
                  }
                  break;

              case 8:
                  // Total Products
                  System.out.println("Total products in inventory: " + service.totalProducts());
                  break;

              case 9:
                  // Exit
                  System.out.println("Exiting!");
                  break;

              default:
                  System.out.println("Invalid choice. Please enter a number between 1 and 9.");
                  break;
          }
      }
      scanner.close();
  }
}
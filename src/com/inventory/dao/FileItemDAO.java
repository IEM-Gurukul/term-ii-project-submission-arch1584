package com.inventory.dao;

import java.util.*;
import java.io.*;

import com.inventory.model.Product;

public class FileItemDAO implements ProductDAO {

  public static final String COMMA_DELIMITER = ",";
  static {
    new File("data").mkdirs();
  }

  private String productToCSV(Product p) {
    return p.toString();
  }

  private Product csvToProduct(String line) {
    if (line == null || line.trim().isEmpty()) {
        return null;
    }

    try {
      String[] fields = line.split(COMMA_DELIMITER);
      int pid = Integer.parseInt(fields[0]);
      String name = fields[1];
      String sku = fields[2];
      double price = Double.parseDouble(fields[3]);
      int quantity = Integer.parseInt(fields[4]);
      String description = fields[5];
      Product p = new Product(name, sku, price, quantity, description);
      p.setProductID(pid);
      return p; } catch (NumberFormatException e) {
      e.printStackTrace();
    }
    return null;

  }

  public void save(Product p) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/inventory.csv", true))) {
      writer.write(productToCSV(p));
      writer.newLine();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public Product findBySKU(String sku) {
    File file = new File("data/inventory.csv");
    if (!file.exists()) return null;
    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      String line;
      while ((line = reader.readLine()) != null) {
        Product p = csvToProduct(line);
        if (p != null && p.getSKU().equals(sku)) {
          return p;
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  public List<Product> findAll() {
    File file = new File("data/inventory.csv");
    if (!file.exists()) {
        return new ArrayList<>();
    }
    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      List<Product> prodlist = new ArrayList<>();
      String line;
      while ((line = reader.readLine()) != null) {
        Product p = csvToProduct(line);
        if (p != null)
          prodlist.add(p);
      }
      return prodlist;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return new ArrayList<>();
  }

  public void deleteBySKU(String sku) {
    File file = new File("data/inventory.csv");
    if (!file.exists()) return;
    try (BufferedReader reader = new BufferedReader(new FileReader(file));
        BufferedWriter writer = new BufferedWriter(new FileWriter("data/temp.csv"))) {
      String line;
      int check = 0;
      while ((line=reader.readLine()) != null) {
        Product p = csvToProduct(line);
        if (p != null && !p.getSKU().equals(sku)) {
          check = 1;
          writer.write(line);
          writer.newLine();
        }
      }
      if (check == 0) {
        System.out.println("Product Not Found. No changes made.");
      }
      else {
        System.out.println("Product deleted successfully!");
      }
      new File("data/inventory.csv").delete();
      new File("data/temp.csv").renameTo(new File("data/inventory.csv"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void updateStock(String sku, int quantity) {
    File file = new File("data/inventory.csv");
    if (!file.exists()) return;
    try (BufferedReader reader = new BufferedReader(new FileReader(file));
        BufferedWriter writer = new BufferedWriter(new FileWriter("data/temp.csv"))) {
      String line;
      while ((line=reader.readLine()) != null) {
        Product prod = csvToProduct(line);
        if (prod!=null && prod.getSKU().equals(sku)) {
          prod.setQuantity(prod.getQuantity()+quantity);
          writer.write(productToCSV(prod));
          writer.newLine();
        }
        else {
          writer.write(line);
          writer.newLine();
        }
      }
      
      new File("data/inventory.csv").delete();
      new File("data/temp.csv").renameTo(new File("data/inventory.csv"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void update(Product p) {
    File file = new File("data/inventory.csv");
    if (!file.exists()) return;
    try (BufferedReader reader = new BufferedReader(new FileReader(file));
        BufferedWriter writer = new BufferedWriter(new FileWriter("data/temp.csv"))) {
      String line;
      while ((line=reader.readLine()) != null) {
        Product prod = csvToProduct(line);
        if (prod!=null && prod.getSKU().equals(p.getSKU())) {
          writer.write(productToCSV(p));
          writer.newLine();
        }
        else {
          writer.write(line);
          writer.newLine();
        }
      }
      
      new File("data/inventory.csv").delete();
      new File("data/temp.csv").renameTo(new File("data/inventory.csv"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public int getNextProductID() {
    List<Product> all = findAll();
    int maxID = 0;
    for (Product p : all) {
        if (p.getProductID() > maxID) {
            maxID = p.getProductID();
        }
    }
    return maxID + 1;
  }

}

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
    try {
      String[] fields = line.split(COMMA_DELIMITER);
      int pid = Integer.parseInt(fields[0]);
      String name = fields[1];
      String sku = fields[2];
      double price = Double.parseDouble(fields[3]);
      int quantity = Integer.parseInt(fields[4]);
      String description = fields[5];
      Product p = new Product(pid, name, sku, price, quantity, description);
      return p; } catch (NumberFormatException e) {
      e.printStackTrace();
    }
    return null;

  }

  public void save(Product p) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/inventory.csv", true))) {
      writer.newLine();
      writer.write(productToCSV(p));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public Product findBySKU(String sku) {
    try (BufferedReader reader = new BufferedReader(new FileReader("data/inventory.csv"))) {
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
    try (BufferedReader reader = new BufferedReader(new FileReader("data/inventory.csv"))) {
      List<Product> prodlist = new ArrayList<Product>();
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
    try (BufferedReader reader = new BufferedReader(new FileReader("data/inventory.csv"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("data/temp.csv"))) {
      String line;
      while ((line=reader.readLine()) != null) {
        Product p = csvToProduct(line);
        if (p != null && !p.getSKU().equals(sku)) {
          writer.newLine();
          writer.write(line);
        }
      }
      
      new File("data/inventory.csv").delete();
      new File("data/temp.csv").renameTo(new File("data/inventory.csv"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void update(Product p) {
    try (BufferedReader reader = new BufferedReader(new FileReader("data/inventory.csv"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("data/temp.csv"))) {
      String line;
      while ((line=reader.readLine()) != null) {
        Product prod = csvToProduct(line);
        if (prod!=null && prod.getSKU().equals(p.getSKU())) {
          writer.newLine();
          writer.write(productToCSV(p));
        }
        else {
          writer.newLine();
          writer.write(line);
        }
      }
      
      new File("data/inventory.csv").delete();
      new File("data/temp.csv").renameTo(new File("data/inventory.csv"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  

}

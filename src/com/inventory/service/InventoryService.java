package com.inventory.service;
import java.util.*;
import com.inventory.model.Product;
import com.inventory.dao.ProductDAO;
import com.inventory.exception.InsufficientStockException;
import com.inventory.exception.ItemNotFoundException;

public class InventoryService {
  private ProductDAO dao;

  public InventoryService(ProductDAO dao) {
    this.dao = dao;
  }

  public boolean addProduct(String name, String sku, double price, int quantity, String description) {
    int newID = dao.getNextProductID();
    Product p = new Product(name, sku, price, quantity, description);
    p.setProductID(newID);
    dao.save(p);
    return true;
  }

  public boolean deleteProduct(String sku) {
    Product product = dao.findBySKU(sku);
    if (product == null) {
      throw new ItemNotFoundException("Product not found: " + sku);
    }
    if (!sku.isEmpty()) {
      dao.deleteBySKU(sku);
      return true;
    }
    else {
      return false;
    }
  }

  public boolean reduceStock(String sku, int quantity) {
    Product product = dao.findBySKU(sku);
    if (product!= null) {
      if (product.getQuantity()>=quantity) {
        if (quantity>0) 
          quantity*=-1;
        if (quantity != 0) {
          dao.updateStock(sku,quantity);
          return true;
        }
        else {
          return false;
        }  
      }
      else {
        throw new InsufficientStockException("Stock insufficient");
      }
    }
    else {
      throw new ItemNotFoundException("Product not found: "+sku);
    }
  }

  public boolean restockProduct(String sku, int quantity) {
    Product product = dao.findBySKU(sku);
    if (product != null) {   
      if (quantity<0) 
      quantity*=-1;
      if (quantity != 0) {
        dao.updateStock(sku,quantity);
        return true;
      }
      else {
        return false;
      }
    }
    else {
      throw new ItemNotFoundException("Product not found: "+sku);
    }
  }


  public boolean updateProduct(String sku, double price, String description) {
    Product product = dao.findBySKU(sku);
    if (product == null) {
      throw new ItemNotFoundException("Product not found: " + sku);
    }
    product.setPrice(price);
    product.setDescription(description);
    dao.update(product);
    return true;
  }

  public int totalProducts() {
    List<Product> prodlist = dao.findAll();
    return prodlist.size();
  }

  public List<Product> getAllProducts() {
    List<Product> prodlist = dao.findAll();
    return prodlist;
  }

  public Product findProduct(String sku) {
    return dao.findBySKU(sku);
  }
}
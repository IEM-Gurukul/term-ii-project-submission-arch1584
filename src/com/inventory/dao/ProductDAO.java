package com.inventory.dao;

import java.util.List;
import com.inventory.model.Product;

public interface ProductDAO {
  void save(Product p);
  Product findBySKU(String sku);
  List<Product> findAll();
  void deleteBySKU(String sku);
  void updateStock(String sku, int quantity);
  public void update(Product p);
}
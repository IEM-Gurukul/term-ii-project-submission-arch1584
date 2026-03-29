package com.inventory.model;

public class Product {
  private int productID;
  private String name;
  private String sku;
  private double price;
  private int quantity;
  private String description;

  public Product (String prodname, String prodsku, double prodprice, int prodquantity, String proddescription) {
    name = prodname;
    sku = prodsku;
    price = prodprice;
    quantity = prodquantity;
    description = proddescription;
  }

  public int getProductID() {
    return productID;
  }

  public String getName() {
      return name;
    }

  public String getSKU() {
    return sku;
  }

  public double getPrice() {
    return price;
  }

  public int getQuantity() {
    return quantity;
  }

  public String getDescription() {
    return description;
  }

  public void setName(String newname) {
    this.name = newname;
  }

  public void setPrice(double newprice) {
    this.price = newprice;
  }

  public void setQuantity(int newquantity) {
    this.quantity = newquantity;
  }

  public void setDescription(String newdescription) {
    this.description = newdescription;
  }

  public void setProductID(int id) {
    this.productID = id;
  }

  @Override
  public String toString() {
    return productID+","+name+","+sku+","+price+","+quantity+","+description ;
  }

  public void display() {
    System.out.println("Product ID: "+productID+"  Name: "+name+"  SKU: "+sku+"  Price: "+price+"  Quantity: "+quantity+"  Description: "+description);
  }


}
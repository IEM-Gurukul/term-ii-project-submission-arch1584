package com.inventory.model;

public class Item {
  private int productID;
  private String name;
  private String sku;
  private double price;
  private int quantity;
  private String description;

  public Item (int pid, String itemname, String itemsku, double itemprice, int itemquantity, String itemdescription) {
    productID = pid;
    name = itemname;
    sku = itemsku;
    price = itemprice;
    quantity = itemquantity;
    description = itemdescription;
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

  @Override
  public String toString() {
    return "Item Details:\n ID: "+productID+" Name: "+name+" SKU: "+sku+" Price: "+price+" Quantity: "+quantity+" Description: "+description ;
  }
}
public class ConsoleMenu {
  public static void main (String[] args) {
    switch (choice) {
      case 0: 
      case 1:
      case 2: 
      case 3:
      case 4:
      case 5: 
      System.out.println("Product Details");
    for (int i=0;i<prodlist.size();i++) {
      p = prodlist.get(i);
      System.out.println("Product ID: "+p.getProductID()+" Name: "+p.getName()+" SKU: "+p.getSKU()+" Price: "+p.getPrice()+" Quantity: "+p.getQuantity()+" Description: "+p.getDescription());
    }

    }
  }
}
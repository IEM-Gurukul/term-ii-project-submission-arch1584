import java.util.List;
import com.inventory.model.Product;

public interface ProductDAO {
  void save(Product p);
  Product findBySKU(String sku);
  List<Product> findAll();
  void deleteBySKU(String sku);
  void update(Product p);
}
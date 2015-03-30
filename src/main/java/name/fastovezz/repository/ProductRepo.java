package name.fastovezz.repository;

import name.fastovezz.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Long> {
    Product findByName(String productName);
}

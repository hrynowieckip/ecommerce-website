package hrynowieckip.ecommercewebsite.domain.repository;

import hrynowieckip.ecommercewebsite.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Boolean existsByName(String name);
    List<Product> getAllBy();
    Product getByName(String name);
}

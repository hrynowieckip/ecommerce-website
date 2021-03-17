package hrynowieckip.ecommercewebsite.domain.repository;

import hrynowieckip.ecommercewebsite.domain.model.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
}

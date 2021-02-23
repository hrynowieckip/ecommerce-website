package hrynowieckip.ecommercewebsite.domain.repository;

import hrynowieckip.ecommercewebsite.domain.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> getAll();
    Boolean existsByName(String name);
    Category getByName(String name);
}

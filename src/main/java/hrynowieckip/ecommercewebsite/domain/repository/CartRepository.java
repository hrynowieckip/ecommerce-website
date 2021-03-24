package hrynowieckip.ecommercewebsite.domain.repository;

import hrynowieckip.ecommercewebsite.domain.model.Cart;
import hrynowieckip.ecommercewebsite.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart getByUser(User user);
}

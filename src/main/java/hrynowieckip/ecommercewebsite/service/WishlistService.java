package hrynowieckip.ecommercewebsite.service;

import hrynowieckip.ecommercewebsite.converter.ProductConverter;
import hrynowieckip.ecommercewebsite.data.product.ProductSummary;
import hrynowieckip.ecommercewebsite.domain.model.Product;
import hrynowieckip.ecommercewebsite.domain.model.User;
import hrynowieckip.ecommercewebsite.domain.model.Wishlist;
import hrynowieckip.ecommercewebsite.domain.repository.ProductRepository;
import hrynowieckip.ecommercewebsite.domain.repository.UserRepository;
import hrynowieckip.ecommercewebsite.domain.repository.WishlistRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class WishlistService {
    private final WishlistRepository wishlistRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ProductConverter productConverter;

    public Set<ProductSummary> getAllProductForUserFromWishlist(String username) {
        User user = userRepository.getByUsername(username);
        Wishlist wishlist = wishlistRepository.getByUser(user);
        Set<Product> products = wishlist.getProducts();
        return products.stream()
                .map(productConverter::toProductSummary)
                .collect(Collectors.toSet());
    }

    public Long addProductToWishlist(String productName, String username) {
        log.debug("Getting user by username: {}", username);
        User user = userRepository.getByUsername(username);
        log.debug("Getting product by product name: {}", productName);
        Product product = productRepository.getByName(productName);

        Wishlist wishlist;
        if (user.getWishlist() == null) {
            wishlist = Wishlist.builder()
                    .products(new HashSet<>(Arrays.asList(product)))
                    .user(user)
                    .build();
            user.setWishlist(wishlist);
        } else {
            Wishlist wishlistUser = user.getWishlist();
            Set<Product> productFromUserWishlist = wishlistUser.getProducts();
            productFromUserWishlist.add(product);
            wishlistUser.setProducts(productFromUserWishlist);
            user.setWishlist(wishlistUser);
            wishlist = user.getWishlist();
        }
        if (product.getWishlist() == null) {
            product.setWishlist(new ArrayList<>(Arrays.asList(wishlist)));
        } else {
            List<Wishlist> productWishlist = product.getWishlist();
            productWishlist.add(wishlist);
            product.setWishlist(productWishlist);
        }

        Wishlist savedWishlist = wishlistRepository.save(wishlist);
        return savedWishlist.getId();
    }
}

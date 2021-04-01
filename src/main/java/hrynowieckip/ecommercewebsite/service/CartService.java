package hrynowieckip.ecommercewebsite.service;


import hrynowieckip.ecommercewebsite.converter.ProductConverter;
import hrynowieckip.ecommercewebsite.data.product.ProductSummary;
import hrynowieckip.ecommercewebsite.domain.model.Cart;
import hrynowieckip.ecommercewebsite.domain.model.Product;
import hrynowieckip.ecommercewebsite.domain.model.User;
import hrynowieckip.ecommercewebsite.domain.repository.CartRepository;
import hrynowieckip.ecommercewebsite.domain.repository.ProductRepository;
import hrynowieckip.ecommercewebsite.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ProductConverter productConverter;

    public List<ProductSummary> getAllProductForUserFromCart(String username) {
        User user = userRepository.getByUsername(username);
        Cart cart = cartRepository.getByUser(user);
        List<Product> products;
        if (cart == null) {
            log.debug("Cart is empty");
            products = new ArrayList<>();
        } else {
            products = cart.getProducts();
        }
        return products.stream()
                .map(productConverter::toProductSummary)
                .collect(Collectors.toList());
    }

    public Long addProductToCart(String productName, String username) {
        log.debug("Getting user by username: {}", username);
        User user = userRepository.getByUsername(username);
        log.debug("Getting product by product name: {}", productName);
        Product product = productRepository.getByName(productName);

        Cart cart;
        if (user.getCart() == null) {
            cart = Cart.builder()
                    .products(new ArrayList<>(Arrays.asList(product)))
                    .user(user)
                    .build();
            user.setCart(cart);
        } else {
            Cart userCart = user.getCart();
            List<Product> productFromUserCart = userCart.getProducts();
            productFromUserCart.add(product);
            userCart.setProducts(productFromUserCart);
            user.setCart(userCart);
            cart = user.getCart();
        }
        if (product.getCarts() == null) {
            product.setCarts(new ArrayList<>(Arrays.asList(cart)));
        } else {
            List<Cart> productCarts = product.getCarts();
            productCarts.add(cart);
            product.setCarts(productCarts);
        }

        Cart savedCart = cartRepository.save(cart);
        return savedCart.getId();
    }

    public String deleteProductFromUserCart(String productName, String username) {
        log.debug("Product name to delete from user cart: {}", productName);
        log.debug("Getting user by username: {}", username);
        User user = userRepository.getByUsername(username);
        log.debug("Getting product by product name: {}", productName);
        Product product = productRepository.getByName(productName);

        Cart cart = user.getCart();
        List<Product> products = cart.getProducts();
        products.remove(product);
        cart.setProducts(products);

        List<Cart> productCarts = product.getCarts();
        productCarts.remove(cart);
        product.setCarts(productCarts);
        return productName;
    }
}

package hrynowieckip.ecommercewebsite.service;

import hrynowieckip.ecommercewebsite.converter.ProductConverter;
import hrynowieckip.ecommercewebsite.data.product.ProductSummary;
import hrynowieckip.ecommercewebsite.domain.model.Cart;
import hrynowieckip.ecommercewebsite.domain.model.Product;
import hrynowieckip.ecommercewebsite.domain.model.User;
import hrynowieckip.ecommercewebsite.domain.repository.CartRepository;
import hrynowieckip.ecommercewebsite.domain.repository.ProductRepository;
import hrynowieckip.ecommercewebsite.domain.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class CartServiceTest {
    @Mock
    private CartRepository cartRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private ProductConverter productConverter;
    @InjectMocks
    private CartService cartService;

    @Test
    public void getAllProductForUserFromCartTest() {
        //Given
        List<Product> productList = Arrays.asList(Product.builder()
                        .name("productTest1")
                        .build(),
                Product.builder()
                        .name("productTest2").build());

        List<ProductSummary> resultProductList = Arrays.asList(ProductSummary.builder()
                        .name("productTest1")
                        .build(),
                ProductSummary.builder()
                        .name("productTest2").build());

        User userBuild = User.builder()
                .username("user@user")
                .build();
        Cart cartBuild = Cart.builder()
                .products(productList)
                .user(userBuild)
                .build();

        //When
        Mockito.when(userRepository.getByUsername("user@user")).thenReturn(userBuild);
        Mockito.when(cartRepository.getByUser(userBuild)).thenReturn(cartBuild);
        for (int i = 0; i < productList.size(); i++) {
            Mockito.when(productConverter.toProductSummary(productList.get(i)))
                    .thenReturn(resultProductList.get(i));
        }

        List<ProductSummary> allProductForUserFromCart =
                cartService.getAllProductForUserFromCart("user@user");
        //Then
        assertThat(allProductForUserFromCart)
                .isNotNull()
                .isEqualTo(resultProductList);

    }

}
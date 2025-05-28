package hrynowieckip.ecommercewebsite.converter;

import hrynowieckip.ecommercewebsite.domain.dto.ProductSummary;
import hrynowieckip.ecommercewebsite.domain.model.Product;
import hrynowieckip.ecommercewebsite.domain.dto.AddProductCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductConverterTest {
    private ProductConverter productConverter;

    @BeforeEach
    public void setup() {
        productConverter = new ProductConverter();
    }

    @Test
    void fromTest() {
        //Given
        Product expected = Product.builder()
                .name("Product")
                .description("Test description")
                .price(BigDecimal.valueOf(5.99))
                .build();

        AddProductCommand addProductCommand = AddProductCommand.builder()
                .name("Product")
                .description("Test description")
                .price(BigDecimal.valueOf(5.99))
                .build();
        //When
        Product result = productConverter.from(addProductCommand);
        //Then
        assertEquals(expected, result);
    }

    @Test
    void toProductSummaryTest() {
        //Given
        ProductSummary expected = ProductSummary.builder()
                .id(2L)
                .name("ProductName")
                .description("Product description")
                .price(BigDecimal.valueOf(9.99))
                .photos(new ArrayList<>())
                .comments(new ArrayList<>())
                .build();

        Product product = Product.builder()
                .id(2L)
                .name("ProductName")
                .description("Product description")
                .price(BigDecimal.valueOf(9.99))
                .photos(new ArrayList<>())
                .comments(new ArrayList<>())
                .build();
        //When
        ProductSummary result = productConverter.toProductSummary(product);
        //Then
        assertEquals(expected, result);
    }
}
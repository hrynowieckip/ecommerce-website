package hrynowieckip.ecommercewebsite.converter;

import hrynowieckip.ecommercewebsite.data.product.ProductSummary;
import hrynowieckip.ecommercewebsite.domain.model.Product;
import hrynowieckip.ecommercewebsite.web.command.AddProductCommand;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {
    public Product from(AddProductCommand addProductCommand) {
        return Product.builder()
                .name(addProductCommand.getName())
                .description(addProductCommand.getDescription())
                .price(addProductCommand.getPrice())
                .build();
    }
    public ProductSummary toProductSummary(Product product){
        return ProductSummary.builder()
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}

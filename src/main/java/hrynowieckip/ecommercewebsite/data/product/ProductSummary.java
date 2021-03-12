package hrynowieckip.ecommercewebsite.data.product;

import hrynowieckip.ecommercewebsite.domain.model.ProductImage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductSummary {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;

    private List<ProductImage> photos = new ArrayList<>();
}

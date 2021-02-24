package hrynowieckip.ecommercewebsite.data.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductSummary {
    private String name;
    private String description;
    private BigDecimal price;
}

package hrynowieckip.ecommercewebsite.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddProductCommand {
    @NotEmpty @Size(min = 6)
    private String name;
    private String description;
    private BigDecimal price;

    private String category;
}

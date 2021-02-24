package hrynowieckip.ecommercewebsite.web.command;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
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

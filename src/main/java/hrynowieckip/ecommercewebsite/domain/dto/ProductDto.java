package hrynowieckip.ecommercewebsite.domain.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record ProductDto(Long id, String name, String description, BigDecimal price, List<ProductImageDto> photos, List<CommentDto> comments) {
}

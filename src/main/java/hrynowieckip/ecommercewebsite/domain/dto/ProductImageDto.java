package hrynowieckip.ecommercewebsite.domain.dto;

import lombok.Builder;

@Builder
public record ProductImageDto(byte[] image) {
}

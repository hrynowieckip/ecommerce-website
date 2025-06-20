package hrynowieckip.ecommercewebsite.domain.dto;

import lombok.Builder;

@Builder
public record CommentDto(String author, String comment) {
}

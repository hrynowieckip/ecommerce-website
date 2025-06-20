package hrynowieckip.ecommercewebsite.domain.dto;

import lombok.Builder;

@Builder
public record UserDto(String username, String firstName, String lastName, String address, String zipCode, String city,
                      String phoneNumber) {
}

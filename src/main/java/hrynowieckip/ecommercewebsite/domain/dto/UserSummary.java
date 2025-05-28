package hrynowieckip.ecommercewebsite.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSummary {
    private String username;
    private String firstName;
    private String lastName;
    private String address;
    private String zipCode;
    private String city;
    private String phoneNumber;
}

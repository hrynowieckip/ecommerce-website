package hrynowieckip.ecommercewebsite.domain.model;


import lombok.*;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "product")
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private byte[] image;

    @ManyToOne
    private Product product;

    public String generateBase64Image()
    {
        return Base64.encodeBase64String(this.getImage());
    }
}

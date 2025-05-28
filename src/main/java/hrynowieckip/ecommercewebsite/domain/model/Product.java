package hrynowieckip.ecommercewebsite.domain.model;

import lombok.*;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "name")
@ToString(exclude = {"category", "comments", "wishlist", "photos", "carts"})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;
    private String description;
    private BigDecimal price;

    @ManyToOne
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductImage> photos = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<Comment> comments = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Wishlist> wishlist = new ArrayList<>();

    @ManyToMany
    private List<Cart> carts = new ArrayList<>();
}

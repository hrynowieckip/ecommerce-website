package hrynowieckip.ecommercewebsite.domain.model;

import lombok.*;

import jakarta.persistence.*;

@Entity
@Table(name = "comments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "product")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String author;
    private String comment;

    @ManyToOne
    private Product product;
}

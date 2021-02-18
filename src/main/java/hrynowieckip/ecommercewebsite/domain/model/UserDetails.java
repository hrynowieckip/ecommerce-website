package hrynowieckip.ecommercewebsite.domain.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="users_details")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
@ToString(exclude = "user")
@EqualsAndHashCode(of ={"userId"} )
public class UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String zipCode;
    private String city;
    private String phoneNumber;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(insertable = false,updatable = false, name = "user_id")
    private Long userId;
}

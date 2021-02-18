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
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String zipCode;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String phoneNumber;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(insertable = false,updatable = false, name = "user_id")
    private Long userId;
}

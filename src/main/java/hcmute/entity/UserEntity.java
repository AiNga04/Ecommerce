package hcmute.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import hcmute.model.enums.AuthProvider;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;

    private String password;

    private String email;

    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    private Boolean enabled;

    private String verify_code;

    private String reset_pwd_token;

    @Column(name = "surname", columnDefinition = "nvarchar(50)")
    private String surname;

    @Column(name = "name", columnDefinition = "nvarchar(50)")
    private String name;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "phone_number", columnDefinition = "varchar(50)")
    private String phoneNumber;

    @Column(name = "image_url", columnDefinition = "varchar(255)")
    private String imageUrl;

    @Column(name = "gender")
    private int gender;

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<UserRoleEntity> authorities;

    @ManyToOne
    @JoinColumn(name = "id_branch")
    private BranchEntity branchByUser;

    @OneToOne(mappedBy = "customerByCart")
    private CartEntity cart;

    @OneToMany(mappedBy = "customerByOrder")
    private Set<Order> orders;

    @OneToMany(mappedBy = "userEntity")
    @JsonBackReference
//    @ToStringExclude
    private List<Notification> notifications;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;
}

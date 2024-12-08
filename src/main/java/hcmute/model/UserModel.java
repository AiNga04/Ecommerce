package hcmute.model;

import hcmute.entity.BranchEntity;
import hcmute.entity.CartEntity;
import hcmute.entity.Order;
import hcmute.entity.UserRoleEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
    private int id;
    private String username;
    private String password;
    private String email;
    private AuthProvider provider;
    private Boolean enabled;
    private String verify_code;
    private String reset_pwd_token;
    private String surname;
    private String name;
    private String birthday;
    private String phoneNumber;
    private String imageUrl;
    private int gender;
    private List<UserRoleEntity> authorities;
    private BranchEntity branchByUser;
    private Set<CartEntity> carts;
    private Set<Order> orders;
    private Boolean isEdit;
}

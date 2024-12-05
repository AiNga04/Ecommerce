package hcmute.model;

import hcmute.entity.RoleEntity;
import hcmute.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleModel {
    private Integer role_user_id;
    private UserEntity user;
    private RoleEntity role;
    private int user_id;
    private String role_id;
    private int idBranch;
    private Boolean isEdit;
}

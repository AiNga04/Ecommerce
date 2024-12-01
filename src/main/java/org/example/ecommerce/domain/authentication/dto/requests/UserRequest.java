package org.example.ecommerce.domain.authentication.dto.requests;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest implements Serializable {
    private String fullname;
    private boolean status;
    private String type;
    private String email;
    private String phone;
    private String username;
    private String password;
    private String role;
}

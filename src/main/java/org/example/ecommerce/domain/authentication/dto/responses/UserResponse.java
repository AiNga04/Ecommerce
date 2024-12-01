package org.example.ecommerce.domain.authentication.dto.responses;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse implements Serializable {
    private Integer id;
    private String fullname;
    private boolean status;
    private String type;
    private String email;
    private String phone;
    private String username;
    private String role;
}

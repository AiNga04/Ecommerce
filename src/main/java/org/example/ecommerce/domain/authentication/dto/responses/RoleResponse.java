package org.example.ecommerce.domain.authentication.dto.responses;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleResponse implements Serializable {
    private Integer id;
    private String name;
    private String description;
}

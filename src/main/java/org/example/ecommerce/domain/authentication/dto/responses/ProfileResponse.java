package org.example.ecommerce.domain.authentication.dto.responses;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileResponse implements Serializable {
    private Integer id;
    private Date dateOfBirth;
    private String gender;
    private String interest;
    private byte[] avatar;
}

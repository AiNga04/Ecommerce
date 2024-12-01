package org.example.ecommerce.domain.authentication.dto.requests;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileRequest implements Serializable {
    private Date dateOfBirth;
    private String gender;
    private String interest;
    private byte[] avatar;
}

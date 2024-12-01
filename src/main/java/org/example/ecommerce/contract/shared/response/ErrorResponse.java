package org.example.ecommerce.contract.shared.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse implements Serializable {
    private Date timestamp;
    private int status;
    private String path;
    private String error;
    private String message;
}

package org.example.ecommerce.domain.common.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.example.ecommerce.domain.common.constants.EnumsErrorResponse;

@Getter
@Setter
public class AppException extends RuntimeException {
    EnumsErrorResponse errorResponse;

    public AppException(EnumsErrorResponse errorResponse) {
        super(errorResponse.getMessage());
        this.errorResponse = errorResponse;
    }
}

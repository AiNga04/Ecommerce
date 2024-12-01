package org.example.ecommerce.contract.shared.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@Builder
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class BaseResponse<T> implements Serializable {
    int status;
    String message;
    @NonFinal
    T data;

    /**
     * Response data when the API executes successfully or getting error. For PUT, PATCH, DELETE
     *
     * @param status
     * @param message
     */
    public BaseResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
package org.example.ecommerce.contract.shared.response;

public interface ApiError {
    static ApiError createFieldApiError(
            String field, String message, String path, Object rejectedValue
    ) {
        return new ApiFieldError(field, message, path, rejectedValue);
    }

    String message();

    record ApiFieldError(
            String field, String message, String path, Object rejectedValue
    ) implements ApiError {
    }
}
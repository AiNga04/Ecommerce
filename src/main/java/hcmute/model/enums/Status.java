package hcmute.model.enums;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import hcmute.entity.StatusDeserializer;
import lombok.Getter;

@Getter
@JsonDeserialize(using = StatusDeserializer.class)
public enum Status {
    ACTIVE("Active"),
    INACTIVE("Inactive"),
    DELETED("Deleted"),
    PENDING("Pending"),
    CANCEL("Cancel"),
    WAITING("Waiting"),
    DELIVERED("Delivered"),
    PROCESSING("Processing"),
    RETURNED("Returned"),
    SHIPPING("Shipping"),
    SHIPPED("Shipped"),
    CART("Cart"),
    ORDER("Order"),
    COMPLETED("Completed"),
    REFUNDED("Refunded"),
    PAID("Paid"),
    UNPAID("Unpaid"),
    FAIL("Fail"),
    LOCKED("Locked");

    private final String value;

    Status(String value) {
        this.value = value;
    }

    public static boolean isValidStatus(String status) {
        for (Status validStatus : Status.values()) {
            if (validStatus.name().equalsIgnoreCase(status)) {
                return false;
            }
        }
        return true;
    }

    public static Status fromValue(String value) {
        for (Status status : values()) {
            if (status.getValue().equalsIgnoreCase(value)) {
                return status;
            }
        }
        return null;
    }
}

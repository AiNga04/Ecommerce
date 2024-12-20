package hcmute.dto.request;

import hcmute.model.enums.Status;
import hcmute.entity.Voucher;
import hcmute.model.enums.VoucherType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Setter
@Getter
@RequiredArgsConstructor
public class VoucherAdminRequest {

    private Long voucherId;

    private String code;

    private String name;

    private String description;

    private Long discount;

    private Long minPrice;

    private Long maxPrice;

    private Long maxDiscount;

    private Long quantity;

    private Date startDate;

    private Date endDate;

    private String type;

    private String username;

    public static Voucher convertCreateToVoucher(VoucherAdminRequest request) {
        Voucher voucher = new Voucher();
        voucher.setCode(request.getCode());
        voucher.setName(request.getName());
        voucher.setDescription(request.getDescription());
        voucher.setDiscount(request.getDiscount());
        voucher.setMinPrice(request.getMinPrice());
        voucher.setMaxPrice(request.getMaxPrice());
        voucher.setMaxDiscount(request.getMaxDiscount());
        voucher.setQuantity(request.getQuantity());
        voucher.setStartDate(request.getStartDate());
        voucher.setEndDate(request.getEndDate());
//        voucher.setCreateAt(LocalDateTime.now());
//        voucher.setUpdateAt(LocalDateTime.now());
        voucher.setQuantityUsed(0L);
        voucher.setStatus(Status.ACTIVE);
        voucher.setType(convertType(request.getType()));

        return voucher;
    }

    public static Voucher convertUpdateToVoucher(VoucherAdminRequest request, Voucher voucher) {
        voucher.setName(request.getName());
        voucher.setDescription(request.getDescription());
        voucher.setDiscount(request.getDiscount());
        voucher.setMinPrice(request.getMinPrice());
        voucher.setMaxPrice(request.getMaxPrice());
        voucher.setMaxDiscount(request.getMaxDiscount());
        voucher.setQuantity(request.getQuantity());
        voucher.setStartDate(request.getStartDate());
        voucher.setEndDate(request.getEndDate());
//        voucher.setUpdateAt(LocalDateTime.now());
        voucher.setType(convertType(request.getType()));

        return voucher;
    }

    private static VoucherType convertType(String type) {
        if (type.equals("percent")) {
            return VoucherType.PERCENTAGE_SYSTEM;
        }
        if (type.equals("money")) {
            return VoucherType.AMOUNT_SYSTEM;
        } else {
            return VoucherType.SHIPPING;
        }
    }

    public void validate() {
        if (this.code == null || this.code.isEmpty()) {
            throw new IllegalArgumentException("Mã giảm giá không được để trống");
        }
        if (this.name == null || this.name.isEmpty()) {
            throw new IllegalArgumentException("Tên giảm giá không được để trống");
        }
        if (this.description == null || this.description.isEmpty()) {
            throw new IllegalArgumentException("Mô tả giảm giá không được để trống");
        }
        if (this.discount == null) {
            throw new IllegalArgumentException("Giá trị giảm giá không được để trống");
        }
        if (this.discount < 0) {
            throw new IllegalArgumentException("Giá trị giảm giá không được nhỏ hơn 0%");
        }
        if (this.discount == 0) {
            throw new IllegalArgumentException("Giá trị giảm giá không được bằng 0%");
        }
//        if (this.minPrice == null) {
//            throw new IllegalArgumentException("Giá trị đơn hàng tối thiểu không được để trống");
//        }
//        if (this.maxPrice == null) {
//            throw new IllegalArgumentException("Giá trị đơn hàng tối đa không được để trống");
//        }
//        if (this.maxDiscount == null) {
//            throw new IllegalArgumentException("Giá trị giảm giá tối đa không được để trống");
//        }
        if (this.quantity == null) {
            throw new IllegalArgumentException("Số lượng giảm giá không được để trống");
        }
        if (this.quantity > 1000) {
            throw new IllegalArgumentException("Số lượng giảm giá không được lớn hơn 1000");
        }
        if (this.startDate == null) {
            throw new IllegalArgumentException("Ngày bắt đầu không được để trống");
        }
        if (this.endDate == null) {
            throw new IllegalArgumentException("Ngày kết thúc không được để trống");
        }
//        if (this.minPrice < 0 || this.maxPrice < 0 || this.maxDiscount < 0 || this.quantity < 0) {
//            throw new IllegalArgumentException("Giá trị không được nhỏ hơn 0");
//        }
        if (this.startDate.after(this.endDate)) {
            throw new IllegalArgumentException("Ngày bắt đầu không được sau ngày kết thúc");
        }
//        if (this.startDate.before(new Date())) {
//            throw new IllegalArgumentException("Ngày bắt đầu không được trước ngày hiện tại");
//        }
        if (this.endDate.equals(this.startDate)) {
            throw new IllegalArgumentException("Ngày bắt đầu không được trùng ngày kết thúc");
        }

        trim();

    }

    public void validateCreate() {
        validate();

        if (this.type == null || this.type.isEmpty()) {
            throw new IllegalArgumentException("Loại giảm giá không được để trống");
        }
        if (!this.type.equals("percent".trim()) && !this.type.equals("money".trim()) && !this.type.equals("shipping".trim())) {
            throw new IllegalArgumentException(
                    "Loại giảm giá không hợp lệ. Loại giảm giá cửa chỉ có thể là percent, money hoặc shipping");
        }
        if (this.type.equals("percent") && this.discount > 100) {
            throw new IllegalArgumentException("Giá trị giảm giá không được lớn 100%");
        }
//        if (this.type.equals("money") && this.discount >= this.maxDiscount) {
//            throw new IllegalArgumentException("Giá trị giảm giá không được lớn hơn giá trị giảm giá tối đa");
//        }
    }

    public void validateUpdate() {
        if (this.voucherId == null) {
            throw new IllegalArgumentException("Mã giảm giá không được để trống");
        }

        validateCreate();
    }

    public void trim() {
        this.code = this.code.trim();
        this.name = this.name.trim();
        this.description = this.description.trim();
        this.type = this.type.trim();
        this.username = this.username.trim();
    }

}


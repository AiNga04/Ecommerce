package hcmute.model;

import hcmute.entity.MilkTeaCategoryEntity;
import hcmute.entity.MilkTeaEntity;
import hcmute.model.enums.Status;
import hcmute.entity.Voucher;
import hcmute.model.enums.VoucherType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VoucherDTO {

    private Long voucherId;

    private Status status;

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

    private Long quantityUsed;

    private String type;

    private boolean applyToAll;

    private MilkTeaCategoryEntity category;

    private List<MilkTeaEntity> specificProducts;

    public static VoucherDTO convertEntityToDTO(Voucher voucher) {
        VoucherDTO voucherDTO = new VoucherDTO();
        voucherDTO.setVoucherId(voucher.getVoucherId());
        voucherDTO.setStatus(voucher.getStatus());
        voucherDTO.setCode(voucher.getCode());
        voucherDTO.setName(voucher.getName());
        voucherDTO.setDescription(voucher.getDescription());
        voucherDTO.setDiscount(voucher.getDiscount());
        voucherDTO.setMinPrice(voucher.getMinPrice());
        voucherDTO.setMaxPrice(voucher.getMaxPrice());
        voucherDTO.setMaxDiscount(voucher.getMaxDiscount());
        voucherDTO.setQuantity(voucher.getQuantity());
        voucherDTO.setStartDate(voucher.getStartDate());
        voucherDTO.setEndDate(voucher.getEndDate());
        voucherDTO.setQuantityUsed(voucher.getQuantityUsed());
        voucherDTO.setApplyToAll(voucher.isApplyToAll());
        voucherDTO.setCategory(voucher.getCategory() != null ? voucher.getCategory() : new MilkTeaCategoryEntity());
        voucherDTO.setSpecificProducts(voucher.getSpecificProducts() != null ? voucher.getSpecificProducts() : new ArrayList<>());
        
        if (voucher.getType() == null ||
                voucher.getType().equals(VoucherType.PERCENTAGE_SHOP) ||
                voucher.getType().equals(VoucherType.PERCENTAGE_SYSTEM)) {
            voucherDTO.setType("Giảm theo phần trăm");
        }
        if (voucher.getType().equals(VoucherType.AMOUNT_SHOP) ||
                voucher.getType().equals(VoucherType.AMOUNT_SYSTEM)) {
            voucherDTO.setType("Giảm theo tiền");
        }
        if (voucher.getType().equals(VoucherType.SHIPPING)) {
            voucherDTO.setType("Giảm phí vận chuyển");
        }
//        if (voucher.getType().equals(VoucherType.FREE_SHIP)){
//            voucherDTO.setType("Miễn phí vận chuyển");
//        }

        return voucherDTO;
    }

    public static List<VoucherDTO> convertToListDTO(List<Voucher> vouchers) {
        List<VoucherDTO> voucherDTOs = new ArrayList<>();
        for (Voucher voucher : vouchers) {
            voucherDTOs.add(convertEntityToDTO(voucher));
        }

        voucherDTOs.sort(Comparator
                                 .comparing(VoucherDTO::getType)
                                 .thenComparing(VoucherDTO::getStartDate)
                                 .thenComparing(VoucherDTO::getEndDate)
                                 .reversed());

        return voucherDTOs;
    }

    public String generateRandomCode(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder code = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            code.append(characters.charAt(random.nextInt(characters.length())));
        }
        return code.toString();
    }
}



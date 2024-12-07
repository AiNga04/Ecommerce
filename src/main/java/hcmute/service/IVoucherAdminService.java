package hcmute.service;

import hcmute.dto.request.VoucherAdminRequest;
import hcmute.dto.response.ListVoucherAdminResponse;
import hcmute.dto.response.VoucherAdminResponse;
import hcmute.model.enums.Status;
import hcmute.entity.Voucher;
import hcmute.model.enums.VoucherType;
import org.springframework.transaction.annotation.Transactional;

public interface IVoucherAdminService {
    VoucherAdminResponse addNewVoucherAdmin(VoucherAdminRequest request);

    VoucherAdminResponse getVoucherAdminByVoucherId(Long voucherId);

    ListVoucherAdminResponse getListVoucherAdmin(String username);

    ListVoucherAdminResponse getListVoucherAdminByUsername(String username);

    ListVoucherAdminResponse getListVoucherAdminByStatus(String username, Status status);

    ListVoucherAdminResponse getListVoucherAdminByType(String username, VoucherType type);

    @Transactional
    VoucherAdminResponse updateVoucherAdmin(VoucherAdminRequest request, String username);

    @Transactional
    VoucherAdminResponse updateStatusVoucherAdmin(Long voucherId, Status status, String username);

    Voucher checkVoucherSystem(Long voucherId);
}

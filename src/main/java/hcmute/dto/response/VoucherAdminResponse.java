package hcmute.dto.response;

import hcmute.model.ResponseAbstract;
import hcmute.model.VoucherDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VoucherAdminResponse extends ResponseAbstract {
    private String username;
    private VoucherDTO voucherDTO;
}
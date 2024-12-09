package hcmute.dto.response;

import hcmute.model.ResponseAbstract;
import hcmute.model.VoucherDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ListVoucherAdminResponse extends ResponseAbstract {
    private String username;
    private int count;
    private List<VoucherDTO> voucherDTOs;
}
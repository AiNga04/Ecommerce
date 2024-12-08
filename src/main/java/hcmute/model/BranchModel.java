package hcmute.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BranchModel {
    private Integer idBranch;
    private String name;
    private String addressDetail;
    private String opentime;
    private String image;
    private MultipartFile imageFile;
    private String description;
    private String idCity;
    private Boolean isEdit = false;
}

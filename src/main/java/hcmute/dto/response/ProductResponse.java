package hcmute.dto.response;

import hcmute.entity.BranchMilkTea;
import hcmute.entity.CartDetailEntity;
import hcmute.model.MilkTeaTypeModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@Getter
@Setter
public class ProductResponse {
    private Boolean isEdit;
    private int idMilkTea;
    private String name;
    private int cost;
    private String description;
    private int orderQuantity;
    private String image;
    private MultipartFile imageFile;
    // use for product detail
    private String milkTeaType;
    private String size;
    private int milkTeaTypeId;
    private int branchId;
    private MilkTeaTypeModel milkTeaTypeByMilkTea;
    private Set<CartDetailEntity> cartDetails;
    private Set<BranchMilkTea> branchMilkTeas;
}

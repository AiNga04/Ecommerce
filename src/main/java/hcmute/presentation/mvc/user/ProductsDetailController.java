package hcmute.presentation.mvc.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import hcmute.entity.*;
import hcmute.infrastruture.security.CustomUserDetails;
import hcmute.model.CommentDTO;
import hcmute.model.MilkTeaModel;
import hcmute.model.OrderProduct;
import hcmute.model.OrderProduct.OrderItem;
import hcmute.service.*;
import hcmute.service.impl.CookieServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("product_detail")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
@Slf4j
public class ProductsDetailController {
    IMilkTeaService milkTeaService;
    ICartDetailService cartDetailService;
    IBranchService branchService;
    IBranchMilkTeaService branchMilkTeaService;
    CookieServiceImpl cookieServiceImpl;
    ICartService cartService;

    @Autowired
    ICommentService commentService;

    @Autowired
    IUserService userService;


    @GetMapping("/{id}")
    public ModelAndView detail(ModelMap model,
                               @PathVariable("id") int id,
                               @RequestParam(value = "reviewText", required = false) String reviewText) {
        Optional<MilkTeaEntity> optMilkTea = milkTeaService.findByIdMilkTea(id);
        MilkTeaModel milkTeaModel = new MilkTeaModel();

        if (optMilkTea.isPresent()) {
            MilkTeaEntity entity = optMilkTea.get();

            // copy từ entity sang model
            BeanUtils.copyProperties(entity, milkTeaModel);
            int typeId = entity.getMilkTeaTypeByMilkTea().getIdType();

            // set attributes cho model
            milkTeaModel.setMilkTeaType(entity.getMilkTeaTypeByMilkTea().getName());
            milkTeaModel.setMilkTeaTypeId(typeId);

            List<MilkTeaEntity> relevantProducts = milkTeaService.findRelevantProducts(typeId, id);

            // Lấy tất cả bình luận của sản phẩm
            List<CommentDTO> comments = commentService.findAllWithUser();

            // Tính trung bình số sao cho tất cả các bình luận
            double averageRating = comments.stream()
                    .mapToInt(comment -> Integer.parseInt(comment.getReviewText()))
                    .average()
                    .orElse(0);

            // Định dạng trung bình số sao với 1 chữ số sau dấu phẩy
            DecimalFormat df = new DecimalFormat("#.0");
            String formattedAverageRating = df.format(averageRating); // Định dạng

            // Nếu reviewText được truyền vào, lọc bình luận theo số sao
            if (reviewText != null) {
                comments = comments.stream()
                        .filter(comment -> comment.getReviewText().equals(reviewText))
                        .collect(Collectors.toList());
            }

            // Thêm giá trị trung bình đã định dạng vào model
            model.addAttribute("comments", comments);
            model.addAttribute("averageRating", formattedAverageRating); // Sử dụng giá trị đã định dạng
            model.addAttribute("milkTea", milkTeaModel);
            model.addAttribute("relevantProducts", relevantProducts);

            return new ModelAndView("user/product_detail", model);
        }

        model.addAttribute("message", "Sản phẩm này không tồn tại");
        return new ModelAndView("user/error", model);
    }



    @PostMapping("/{id}/comment")
public String addComment(@PathVariable("id") int productId,
                         @RequestParam String reviewText,
                         @RequestParam String commentText,
                         @AuthenticationPrincipal CustomUserDetails customUserDetails, // Sử dụng CustomUserDetails
                         ModelMap model) {

    log.info("Add comment to product {}.", productId);

    Optional<MilkTeaEntity> milkTeaOpt = milkTeaService.findByIdMilkTea(productId);
    if (!milkTeaOpt.isPresent()) {
        log.info("MilkTea {} not found.", productId);
        model.addAttribute("message", "Sản phẩm không tồn tại.");
        return "redirect:/product_detail/" + productId;
    }

    MilkTeaEntity milkTea = milkTeaOpt.get();

    // Lấy username từ CustomUserDetails
    String username = customUserDetails.getUsername();
    log.info("Username của người dùng: {}", username);

    // Tạo đối tượng Comment và lưu vào DB
    Comment comment = Comment.builder()
            .reviewText(reviewText)
            .milkTea(milkTea)
            .comment(commentText)
            .user(customUserDetails.getUser()) // Lấy user từ CustomUserDetails
            .createdAt(new Date())
            .updatedAt(new Date())
            .build();

    commentService.save(comment);

    return "redirect:/product_detail/" + productId;
}



    @GetMapping("/check")
    public String check(ModelMap model, @RequestParam("data") String data) {
        String dataEncoded = data;
        byte[] decodedBytes = Base64.getDecoder().decode(data);
        data = new String(decodedBytes, StandardCharsets.UTF_8);
        ObjectMapper objectMapper = new ObjectMapper();
        List<BranchEntity> listBranches = branchService.findAll();
        List<Integer> listBranchesEligible = new ArrayList<Integer>();
        int idMilkTea = 0;
        try {
            OrderProduct orderProduct = objectMapper.readValue(data, OrderProduct.class);
            Boolean isSuccess = false;
            for (BranchEntity branch : listBranches) {
                Boolean isChecked = true;
                for (OrderItem item : orderProduct.getList()) {
                    idMilkTea = Integer.parseInt(item.getIdMilkTea());
                    Optional<MilkTeaEntity> entity = milkTeaService.findByIdMilkTea(idMilkTea);
                    if (entity.isPresent()) {
                        int idBranch = branch.getIdBranch();
                        Optional<Integer> remainQuantityOptional = branchMilkTeaService
                                .findRemainQuantityByBranchIdAndMilkTeaId(idBranch, idMilkTea, item.getSize());
                        if (remainQuantityOptional.isPresent()) {
                            if (remainQuantityOptional.get() < Integer.parseInt(item.getQuantity())) {
                                isChecked = false;
                                break;
                            }
                        } else {
                            isChecked = false;
                            break;
                        }
                    }
                }
                if (isChecked) {
                    listBranchesEligible.add(branch.getIdBranch());
                    isSuccess = true;
                }
            }
            if (isSuccess) {
                String json = objectMapper.writeValueAsString(listBranchesEligible);
                byte[] bytes = json.getBytes();
                String base64Encoded = Base64.getEncoder().encodeToString(bytes);
                return "redirect:/payment?data=" + dataEncoded + "&listBranch=" + base64Encoded;
            } else {
                model.addAttribute(
                        "message",
                        "Xin lỗi quý khách! Hiện tại sản phẩm này đã hết hàng trên toàn bộ chi nhánh!"
                );
                model.addAttribute("status", "fail");
                Optional<MilkTeaEntity> optMilkTea = milkTeaService.findByIdMilkTea(idMilkTea);
                MilkTeaModel milkTeaModel = new MilkTeaModel();

                if (optMilkTea.isPresent()) {
                    MilkTeaEntity entity = optMilkTea.get();

                    // copy from entity to model
                    BeanUtils.copyProperties(entity, milkTeaModel);
                    int typeId = entity.getMilkTeaTypeByMilkTea().getIdType();

                    // set attributes for model
                    milkTeaModel.setMilkTeaType(entity.getMilkTeaTypeByMilkTea().getName());
                    milkTeaModel.setMilkTeaTypeId(typeId);

                    List<MilkTeaEntity> relevantProducts = milkTeaService.findRelevantProducts(typeId, idMilkTea);

                    model.addAttribute("milkTea", milkTeaModel);
                    model.addAttribute("relevantProducts", relevantProducts);
                }
                return "user/product_detail";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private int getCartId(int idUser) {
        Optional<CartEntity> cartOpt = cartService.findCartsByUserId(idUser);
        CartEntity cart = cartOpt.get();
        return cart.getIdCart();
    }

    @GetMapping("/addtocart")
    public RedirectView addToCart(
            RedirectAttributes redirectAttributes, @RequestParam("id") int id,
            @RequestParam("size") String size
    ) {

        try {
            int idUser = Integer.parseInt(cookieServiceImpl.getValue("USER_ID"));
            int idCart = getCartId(idUser);
            cartDetailService.addProductToCart(idCart, id, size);
            redirectAttributes.addFlashAttribute("cartMessage", "success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("cartMessage", "fail");
        }

        // redirect
        return new RedirectView("/product_detail/" + id);
    }
}

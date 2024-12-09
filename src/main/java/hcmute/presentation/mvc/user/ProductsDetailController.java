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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
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
    IStorageService storageService;

    @Autowired
    ICommentService commentService;

    @Autowired
    IUserService userService;

    @GetMapping("/{id}")
    public ModelAndView detail(ModelMap model,
                               @PathVariable("id") int id,
                               @RequestParam(value = "reviewText", required = false) String reviewText,
                               @RequestParam(value = "page", defaultValue = "0") int page,
                               @RequestParam(value = "size", defaultValue = "5") int size) {

        Optional<MilkTeaEntity> optMilkTea = milkTeaService.findByIdMilkTea(id);
        MilkTeaModel milkTeaModel = new MilkTeaModel();

        if (optMilkTea.isPresent()) {
            MilkTeaEntity entity = optMilkTea.get();
            BeanUtils.copyProperties(entity, milkTeaModel);
            int typeId = entity.getMilkTeaTypeByMilkTea().getIdType();

            milkTeaModel.setMilkTeaType(entity.getMilkTeaTypeByMilkTea().getName());
            milkTeaModel.setMilkTeaTypeId(typeId);

            List<MilkTeaEntity> relevantProducts = milkTeaService.findRelevantProducts(typeId, id);

            Pageable pageable = PageRequest.of(page, size);

            // Tìm tất cả các bình luận liên quan đến sản phẩm (không áp dụng lọc reviewText)
            Page<CommentDTO> allCommentsPage = commentService.findAllWithUserPaged(pageable);

            // Tính trung bình số sao cho tất cả các bình luận (không bị ảnh hưởng bởi filter reviewText)
            double averageRating = allCommentsPage.getContent().stream()
                    .mapToInt(comment -> Integer.parseInt(comment.getReviewText())) // Convert reviewText (số sao)
                    .average()
                    .orElse(0);

            // Định dạng trung bình số sao với 1 chữ số sau dấu phẩy
            DecimalFormat df = new DecimalFormat("#.0");
            String formattedAverageRating = df.format(averageRating);

            // Thêm giá trị trung bình đã định dạng vào model
            model.addAttribute("averageRating", formattedAverageRating);

            // Tìm các bình luận theo reviewText nếu có (lọc bình luận khi có search)
            Page<CommentDTO> commentPage;
            if (reviewText != null) {
                // Lọc bình luận theo reviewText (số sao)
                commentPage = commentService.findByReviewTextPaged(reviewText, pageable);
            } else {
                // Nếu không có lọc, lấy tất cả bình luận
                commentPage = allCommentsPage;
            }

            model.addAttribute("comments", commentPage.getContent());
            model.addAttribute("milkTea", milkTeaModel);
            model.addAttribute("relevantProducts", relevantProducts);

            // Thêm phân trang
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", commentPage.getTotalPages());
            model.addAttribute("totalComments", commentPage.getTotalElements());

            return new ModelAndView("user/product_detail", model);
        }

        model.addAttribute("message", "Sản phẩm này không tồn tại");
        return new ModelAndView("user/error", model);
    }

    @PostMapping("/{id}/comment")
    public String addComment(@PathVariable("id") int productId,
                             @RequestParam String reviewText,
                             @RequestParam String commentText,
                             @RequestParam(value = "image", required = false) MultipartFile imageFile,
                             @AuthenticationPrincipal CustomUserDetails customUserDetails,
                             ModelMap model) {

        System.out.println("Received request to add comment for product ID: " + productId);

        // Kiểm tra sản phẩm có tồn tại không
        Optional<MilkTeaEntity> milkTeaOpt = milkTeaService.findByIdMilkTea(productId);
        if (!milkTeaOpt.isPresent()) {
            model.addAttribute("message", "Sản phẩm không tồn tại.");
            return "redirect:/product_detail/" + productId;
        }

        MilkTeaEntity milkTea = milkTeaOpt.get();

        // Tạo đối tượng Comment
        Comment comment = Comment.builder()
                .reviewText(reviewText)
                .milkTea(milkTea)
                .comment(commentText)
                .user(customUserDetails.getUser())
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();

        // Nếu có ảnh được tải lên, xử lý lưu ảnh
        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                // Lấy tên file lưu trữ từ service
                String storeFilename = storageService.getStorageFilename(imageFile, String.valueOf(comment.getId()));

                // Lưu ảnh vào thư mục lưu trữ
                storageService.store(imageFile, storeFilename);

                // Lưu đường dẫn ảnh vào đối tượng Comment
                comment.setImageUrl(storeFilename); // Lưu tên file ảnh vào trường imageUrl
            } catch (Exception e) {
                model.addAttribute("message", "Không thể tải ảnh lên: " + e.getMessage());
                return "redirect:/product_detail/" + productId;
            }
        }

        // Lưu comment vào DB
        commentService.save(comment);

        // Chuyển hướng về trang chi tiết sản phẩm
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

package hcmute.service.impl;

import hcmute.entity.Comment;
import hcmute.model.CommentDTO;
import hcmute.repository.CommentRepository;
import hcmute.service.ICommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements ICommentService {
    private final CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public void save(Comment comment) {
        commentRepository.save(comment);
    }

    public Page<CommentDTO> findAllWithUserPaged(Pageable pageable) {
        List<Comment> comments = commentRepository.findAll(pageable).getContent();

        List<CommentDTO> commentDTOs = new ArrayList<>();
        for (Comment comment : comments) {
            CommentDTO commentDTO = new CommentDTO();
            commentDTO.setReviewText(comment.getReviewText());
            commentDTO.setComment(comment.getComment());

            // Lấy tên người dùng từ Comment -> UserEntity
            if (comment.getUser() != null) {
                commentDTO.setUsername(comment.getUser().getUsername());
                commentDTO.setName(comment.getUser().getName());
                commentDTO.setSurname(comment.getUser().getSurname());
            } else {
                commentDTO.setUsername("Không xác định");
                commentDTO.setName("Không xác định");
                commentDTO.setSurname("Không xác định");
            }

            commentDTO.setCreatedAt(comment.getCreatedAt());
            commentDTO.setUpdatedAt(comment.getUpdatedAt());

            commentDTOs.add(commentDTO);
        }

        return new PageImpl<>(commentDTOs, pageable, commentRepository.count());
    }
    public String uploadImage(MultipartFile imageFile) throws IOException {
        // Kiểm tra xem file có tồn tại và không trống không
        if (imageFile == null || imageFile.isEmpty()) {
            throw new IOException("Không có ảnh để tải lên.");
        }

        // Lưu ảnh vào thư mục "uploads" với tên ngẫu nhiên
        String filename = UUID.randomUUID().toString() + "-" + imageFile.getOriginalFilename();
        Path path = Paths.get("uploads", filename);

        // Kiểm tra nếu thư mục chưa tồn tại thì tạo thư mục
        Files.createDirectories(path.getParent());

        // Lưu ảnh vào hệ thống tệp
        Files.copy(imageFile.getInputStream(), path);

        // Trả về đường dẫn URL của ảnh đã tải lên
        return "/uploads/" + filename;
    }




    public Page<CommentDTO> findByReviewTextPaged(String reviewText, Pageable pageable) {
        Page<Comment> commentPage = commentRepository.findByReviewText(reviewText, pageable);

        // Sử dụng ModelMapper hoặc BeanUtils để chuyển đổi từ Comment sang CommentDTO
        List<CommentDTO> commentDTOList = commentPage.getContent().stream()
                .map(comment -> {
                    CommentDTO dto = new CommentDTO();
                    BeanUtils.copyProperties(comment, dto); // Hoặc sử dụng ModelMapper

                    // Lấy thông tin người dùng từ Comment và gán vào DTO
                    if (comment.getUser() != null) {
                        dto.setUsername(comment.getUser().getUsername());
                        dto.setName(comment.getUser().getName());
                        dto.setSurname(comment.getUser().getSurname());
                    } else {
                        dto.setUsername("Không xác định");
                        dto.setName("Không xác định");
                        dto.setSurname("Không xác định");
                    }

                    return dto;
                })
                .collect(Collectors.toList());

        return new PageImpl<>(commentDTOList, pageable, commentPage.getTotalElements());
    }

}

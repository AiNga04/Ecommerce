package hcmute.service.impl;

import hcmute.entity.Comment;
import hcmute.model.CommentDTO;
import hcmute.repository.CommentRepository;
import hcmute.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<CommentDTO> findAllWithUser() {
        List<Comment> comments = commentRepository.findAll();

        // Tạo một danh sách CommentDTO để chuyển đổi dữ liệu trước khi gửi về frontend
        List<CommentDTO> commentDTOs = new ArrayList<>();

        // Lặp qua từng Comment và chuyển đổi sang CommentDTO
        for (Comment comment : comments) {
            CommentDTO commentDTO = new CommentDTO();
            commentDTO.setReviewText(comment.getReviewText());
            commentDTO.setComment(comment.getComment());

            // Lấy tên người dùng từ Comment -> UserEntity
            if (comment.getUser() != null) {
                commentDTO.setUsername(comment.getUser().getUsername()); // Lấy tên người dùng
                commentDTO.setName(comment.getUser().getName());         // Lấy tên đầy đủ
                commentDTO.setSurname(comment.getUser().getSurname());   // Lấy họ người dùng
            } else {
                commentDTO.setUsername("Không xác định"); // Nếu không có user, gán tên mặc định
                commentDTO.setName("Không xác định");
                commentDTO.setSurname("Không xác định");
            }

            // Lấy ngày tạo và ngày cập nhật của bình luận
            commentDTO.setCreatedAt(comment.getCreatedAt());
            commentDTO.setUpdatedAt(comment.getUpdatedAt());

            // Thêm CommentDTO vào danh sách
            commentDTOs.add(commentDTO);
        }

        return commentDTOs;
    }
}

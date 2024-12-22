package hcmute.service;

import hcmute.entity.Comment;
import hcmute.model.CommentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ICommentService {
    void save(Comment comment);

    Page<CommentDTO> findAllWithUserPaged(Pageable pageable);

    String uploadImage(MultipartFile imageFile) throws IOException;

    Page<CommentDTO> findByReviewTextPaged(String reviewText, Pageable pageable);
}

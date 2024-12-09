package hcmute.repository;

import hcmute.entity.Comment;
import hcmute.model.CommentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findAll();
   Page<Comment> findByReviewText(String reviewText, Pageable pageable);

}

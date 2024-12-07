package hcmute.service.impl;

import hcmute.entity.CommentEntity;
import hcmute.repository.CommentRepository;
import hcmute.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentServiceImpl implements ICommentService {

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<CommentEntity> getAllComments() {
        return commentRepository.findByOrderByCreatedAtDesc();
    }

    @Override
    public CommentEntity saveComment(CommentEntity comment) {
        comment.setCreatedAt(LocalDateTime.now());
        return commentRepository.save(comment);
    }
}


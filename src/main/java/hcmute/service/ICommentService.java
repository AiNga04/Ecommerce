package hcmute.service;

import hcmute.entity.Comment;
import hcmute.model.CommentDTO;

import java.util.List;

public interface ICommentService {
    void save(Comment comment);

    List<CommentDTO> findAllWithUser();
}

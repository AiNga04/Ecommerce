package hcmute.service;

import hcmute.entity.CommentEntity;

import java.util.List;

public interface ICommentService {
    List<CommentEntity> getAllComments();
    CommentEntity saveComment(CommentEntity comment);
}

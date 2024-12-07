package hcmute.controller.user;

import hcmute.entity.CommentEntity;
import hcmute.service.ICommentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/comments")
public class CommentController {

    private final ICommentService commentService;

    public CommentController(ICommentService commentService) {
        this.commentService = commentService;
    }

    // Hiển thị danh sách bình luận
    @GetMapping
    public String getAllComments(Model model) {
        List<CommentEntity> comments = commentService.getAllComments();
        model.addAttribute("comments", comments);
        return "comments"; // Tên file JSP
    }

    // Thêm bình luận mới
    @PostMapping
    public String createComment(CommentEntity comment) {
        commentService.saveComment(comment);
        return "redirect:/comments"; // Sau khi thêm xong, chuyển về danh sách
    }
}


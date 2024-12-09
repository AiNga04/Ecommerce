//package hcmute.presentation.mvc.user;
//
//import hcmute.entity.Comment;
//import hcmute.service.ICommentService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//@Controller
//public class CommentController{
//
//    private final ICommentService commentService;
//
//    @Autowired
//    public CommentController(ICommentService commentService) {
//        this.commentService = commentService;
//    }
//
//    @GetMapping("/comments")
//    public String showComments(Model model) {
//        model.addAttribute("comments", commentService.findAllWithUser());
//        return "product_detail"; // Trả về view comment.jsp
//    }
//
//    @PostMapping("/comments")
//    public String addComment(@RequestParam String username, @RequestParam String reviewText, @RequestParam String commentText) {
//        // Giả sử bạn đã có các entity User và MilkTea
//        Comment comment = new Comment();
//        comment.setReviewText(reviewText);
//        comment.setComment(commentText);
//        // Cần phải set User và MilkTea, ví dụ bằng cách tìm từ database
//        commentService.save(comment);
//        return "redirect:/comments"; // Redirect lại để hiển thị bình luận mới
//    }
//}

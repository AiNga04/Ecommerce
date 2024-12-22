<!-- comment.jsp -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bình luận</title>
</head>
<body>
<h2>Bình luận</h2>
<form action="/comments" method="post">
    <label for="username">Tên người dùng:</label>
    <input type="text" id="username" name="username" required><br><br>

    <label for="reviewText">Đánh giá:</label>
    <textarea id="reviewText" name="reviewText" rows="4" cols="50" required></textarea><br><br>

    <label for="commentText">Nội dung bình luận:</label><br>
    <textarea id="commentText" name="commentText" rows="4" cols="50" required></textarea><br><br>

    <button type="submit">Gửi bình luận</button>
</form>

<h3>Các bình luận đã gửi</h3>
<div id="commentsSection">
    <c:forEach var="comment" items="${comments}">
        <div class="comment">
            <strong>${comment.user.username}:</strong>
            <p>${comment.reviewText}</p>
            <p>${comment.comment}</p>
        </div>
        <hr>
    </c:forEach>
</div>
</body>
</html>

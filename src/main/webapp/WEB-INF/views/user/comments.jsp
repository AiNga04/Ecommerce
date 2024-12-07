<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đánh Giá Sản Phẩm</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h2>Đánh Giá Sản Phẩm</h2>
    <div class="rating-summary mb-4">
        <h3>4.7 trên 5</h3>
        <button class="btn btn-outline-primary">Tất Cả</button>
        <button class="btn btn-outline-primary">5 Sao</button>
        <button class="btn btn-outline-primary">4 Sao</button>
        <button class="btn btn-outline-primary">3 Sao</button>
        <button class="btn btn-outline-primary">2 Sao</button>
        <button class="btn btn-outline-primary">1 Sao</button>
    </div>

    <form action="/comments" method="post" class="mb-4">
        <div class="form-group">
            <label for="username">Tên người dùng:</label>
            <input type="text" class="form-control" id="username" name="username" required>
        </div>
        <div class="form-group">
            <label for="rating">Đánh giá:</label>
            <select class="form-control" id="rating" name="rating" required>
                <option value="1">1 sao</option>
                <option value="2">2 sao</option>
                <option value="3">3 sao</option>
                <option value="4">4 sao</option>
                <option value="5">5 sao</option>
            </select>
        </div>
        <div class="form-group">
            <label for="content">Nội dung:</label>
            <textarea class="form-control" id="content" name="content" rows="3" required></textarea>
        </div>
        <div class="form-group">
            <label for="productVersion">Phân loại hàng:</label>
            <input type="text" class="form-control" id="productVersion" name="productVersion">
        </div>
        <div class="form-group">
            <label for="images">Link hình ảnh:</label>
            <input type="text" class="form-control" id="images" name="images">
        </div>
        <button type="submit" class="btn btn-primary mt-3">Thêm Bình Luận</button>
    </form>

    <div>
        <c:forEach var="comment" items="${comments}">
            <div class="comment mb-4 p-3 border rounded">
                <div class="d-flex justify-content-between">
                    <h5>${comment.username}</h5>
                    <p>${comment.createdAt}</p>
                </div>
                <p>Phân loại hàng: ${comment.productVersion}</p>
                <p>Đánh giá: ${comment.rating} ⭐</p>
                <p>${comment.content}</p>
                <c:if test="${not empty comment.images}">
                    <div class="images">
                        <img src="${comment.images}" alt="Comment Image" style="width:100px;height:auto;">
                    </div>
                </c:if>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>

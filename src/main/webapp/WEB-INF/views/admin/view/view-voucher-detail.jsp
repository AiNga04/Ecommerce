<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> <%@ include
file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>CHI TIẾT CHƯƠNG TRÌNH KHUYẾN MÃI</title>
        <!-- Add your additional CSS links here -->
        <link href="<c:url value='/templates/admin/css/styles.css'/>" rel="stylesheet" />
    </head>
    <body class="sb-nav-fixed">
        <%@include file="/common/admin/header/header.jsp" %>
        <div id="layoutSidenav_content">
            <main>
                <div class="container-fluid px-4">
                    <h1 class="mt-4">CHI TIẾT CHƯƠNG TRÌNH KHUYẾN MÃI</h1>
                    <div class="card mb-4">
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item">
                                <a style="text-decoration: none" href="/admin/index">Trang chủ</a>
                            </li>
                            <li class="breadcrumb-item">
                                <a style="text-decoration: none" href="/admin/voucher"
                                    >Danh sách chương trình khuyến mãi</a
                                >
                            </li>
                            <li class="breadcrumb-item active">Chi tiết chương trình khuyến mãi</li>
                        </ol>
                        <div class="card-body">
                            <div class="row">
                                <div class="col-md-6">
                                    <h5>Mã voucher: ${voucher.voucherId}</h5>
                                    <p><strong>Mã code:</strong> ${voucher.code}</p>
                                    <p><strong>Tên voucher:</strong> ${voucher.name}</p>
                                    <p><strong>Mô tả:</strong> ${voucher.description}</p>
                                    <p>
                                        <strong>Giảm giá:</strong>
                                        <c:choose>
                                            <c:when test="${voucher.type == 'Giảm theo phần trăm'}">
                                                ${voucher.discount}%
                                            </c:when>
                                            <c:otherwise> ${voucher.discount} VND </c:otherwise>
                                        </c:choose>
                                    </p>
                                    <p><strong>Giá tối thiểu:</strong> ${voucher.minPrice} VND</p>
                                    <p><strong>Giá tối đa:</strong> ${voucher.maxPrice} VND</p>
                                    <p><strong>Giảm giá tối đa:</strong> ${voucher.maxDiscount} VND</p>
                                    <p><strong>Số lượng:</strong> ${voucher.quantity}</p>
                                    <p><strong>Ngày bắt đầu:</strong> ${voucher.startDate}</p>
                                    <p><strong>Ngày kết thúc:</strong> ${voucher.endDate}</p>
                                    <p><strong>Số lượng đã dùng:</strong> ${voucher.quantityUsed}</p>
                                    <p><strong>Loại:</strong> ${voucher.type}</p>
                                    <p><strong>Trạng thái:</strong> ${voucher.status}</p>
                                    <p>
                                        <strong>Áp dụng cho tất cả:</strong> ${voucher.applyToAll ? 'Có' :
                                        'Không'}
                                    </p>
                                    <p>
                                        <strong>Danh mục:</strong> ${voucher.category != null ?
                                        voucher.category.name : 'N/A'}
                                    </p>
                                    <p>
                                        <strong>Sản phẩm cụ thể:</strong>
                                        <c:forEach items="${voucher.specificProducts}" var="product">
                                            ${product.name}<br />
                                        </c:forEach>
                                    </p>
                                </div>
                            </div>
                            <a href="/admin/voucher/edit/${voucher.voucherId}" class="btn btn-primary">
                                <i class="fas fa-edit"></i> Sửa
                            </a>
                            <a
                                href="/admin/voucher/delete/${voucher.voucherId}"
                                class="btn btn-danger"
                                onclick="return confirmDelete();"
                            >
                                <i class="fas fa-trash"></i> Xóa
                            </a>
                        </div>
                    </div>
                </div>
            </main>
        </div>

        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js"></script>
        <script src="<c:url value='/templates/admin/js/scripts.js'/>"></script>
        <script>
            function confirmDelete() {
                return confirm("Bạn có chắc chắn muốn xóa mã giảm giá này không?");
            }
        </script>
    </body>
</html>

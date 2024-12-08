<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include
        file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <title>DANH SÁCH CHƯƠNG TRÌNH KHUYẾN MÃI</title>
    <!-- Add your additional CSS links here -->
    <link href="<c:url value='/templates/admin/css/styles.css'/>" rel="stylesheet"/>
</head>
<body class="sb-nav-fixed">
    <%@include file="/common/admin/header/header.jsp" %>
    <div id="layoutSidenav_content">
        <main>
            <div class="container-fluid px-4">
                <h1 class="mt-4">DANH SÁCH CHƯƠNG TRÌNH KHUYẾN MÃI</h1>
                <div class="card mb-4">
                    <ol class="breadcrumb mb-4">
                        <li class="breadcrumb-item">
                            <a style="text-decoration: none" href="/admin/index">Trang chủ</a>
                        </li>
                        <li class="breadcrumb-item active">
                            <a style="text-decoration: none; color: black" href="/admin/voucher"
                            >Danh sách chương trình khuyến mãi</a
                            >
                        </li>
                    </ol>
                    <div class="card mb-4">
                        <div class="card-header">
                            <a href="/admin/voucher/add" class="btn btn-success float-right">
                                <i class="fas fa-plus"></i> Thêm mã giảm giá
                            </a>
                        </div>
                        <div class="card-body">
                            <table class="table table-striped table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th>STT</th>
                                    <th>Mã voucher</th>
                                    <th>Mã code</th>
                                    <th>Tên voucher</th>
                                    <th>Mô tả</th>
                                    <th>Giảm giá</th>
                                    <%--                                    <th>Giá tối thiểu</th>--%>
                                    <%--                                    <th>Giá tối đa</th>--%>
                                    <%--                                    <th>Giảm giá tối đa</th>--%>
                                    <%--                                    <th>Số lượng</th>--%>
                                    <%--                                    <th>Ngày bắt đầu</th>--%>
                                    <%--                                    <th>Ngày kết thúc</th>--%>
                                    <th>Số lượng đã dùng</th>
                                    <th>Loại</th>
                                    <th>Trạng thái</th>
                                    <th>Thao tác</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${vouchers}" var="voucher" varStatus="loop">
                                    <tr>
                                        <td>${loop.index + 1}</td>
                                        <td>${voucher.voucherId}</td>
                                        <td>${voucher.code}</td>
                                        <td>${voucher.name}</td>
                                        <td>${voucher.description}</td>
                                        <td>
                                            <c:choose>
                                                <c:when
                                                        test="${voucher.type == 'Giảm theo phần trăm'}"
                                                >
                                                    ${voucher.discount}%
                                                </c:when>
                                                <c:otherwise> ${voucher.discount} VND </c:otherwise>
                                            </c:choose>
                                        </td>
                                            <%--                                        <td>${voucher.minPrice} VND</td>--%>
                                            <%--                                        <td>${voucher.maxPrice} VND</td>--%>
                                            <%--                                        <td>${voucher.maxDiscount} VND</td>--%>
                                            <%--                                        <td>${voucher.quantity}</td>--%>
                                            <%--                                        <td>${voucher.startDate}</td>--%>
                                            <%--                                        <td>${voucher.endDate}</td>--%>
                                        <td>${voucher.quantityUsed}</td>
                                        <td>${voucher.type}</td>
                                        <td>${voucher.status}</td>
                                        <td>
                                            <a
                                                    href="/admin/voucher/edit/${voucher.voucherId}"
                                                    class="btn btn-primary btn-sm"
                                            >
                                                <i class="fas fa-edit"></i> Sửa
                                            </a>
                                            <a
                                                    href="/admin/voucher/detail/${voucher.voucherId}"
                                                    class="btn btn-primary btn-sm"
                                            >
                                                <i class="fas fa-edit"></i> Chi tiết
                                            </a>
                                            <a
                                                    href="/admin/voucher/delete/${voucher.voucherId}"
                                                    class="btn btn-danger btn-sm"
                                                    onclick="return confirmDelete();"
                                            >
                                                <i class="fas fa-trash"></i> Xóa
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>

                            <!-- Pagination -->
                            <nav aria-label="Page navigation">
                                <ul class="pagination justify-content-center">
                                    <li class="page-item ${currentPage == 1 ? 'disabled' : ''}">
                                        <a class="page-link" href="/admin/voucher?page=${currentPage - 1}"
                                        >Previous</a
                                        >
                                    </li>
                                    <c:forEach begin="1" end="${totalPages}" var="pageNumber">
                                        <li
                                                class="page-item ${pageNumber == currentPage ? 'active' : ''}"
                                        >
                                            <a class="page-link" href="/admin/voucher?page=${pageNumber}"
                                            >${pageNumber}</a
                                            >
                                        </li>
                                    </c:forEach>
                                    <li class="page-item ${currentPage == totalPages ? 'disabled' : ''}">
                                        <a class="page-link" href="/admin/voucher?page=${currentPage + 1}"
                                        >Next</a
                                        >
                                    </li>
                                </ul>
                            </nav>
                        </div>
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

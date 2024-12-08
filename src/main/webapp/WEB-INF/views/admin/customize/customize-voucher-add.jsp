<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> <%@ include
file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <title>Thêm Mã Giảm Giá</title>
        <link href="<c:url value='/templates/admin/css/styles.css'/>" rel="stylesheet" />
        <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
            rel="stylesheet"
        />
    </head>
    <body class="sb-nav-fixed">
        <%@include file="/common/admin/header/header.jsp" %>
        <div id="layoutSidenav_content">
            <main>
                <div class="container-fluid px-4">
                    <h1 class="mt-4">Thêm Mã Giảm Giá</h1>
                    <div class="card mb-4">
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item"><a href="/admin/index">Trang chủ</a></li>
                            <li class="breadcrumb-item"><a href="/admin/voucher">Danh sách voucher</a></li>
                            <li class="breadcrumb-item active">Thêm voucher</li>
                        </ol>

                        <div class="card">
                            <div class="card-header">
                                <h4 class="card-title">Thông tin voucher</h4>
                            </div>
                            <div class="card-body">
                                <form
                                    action="/admin/voucher/add"
                                    method="POST"
                                    class="needs-validation"
                                    novalidate
                                >
                                    <!-- Basic Information -->
                                    <div class="row mb-3">
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label for="code"
                                                    >Mã Code <span class="text-danger">*</span></label
                                                >
                                                <input
                                                    type="text"
                                                    class="form-control"
                                                    id="code"
                                                    name="code"
                                                    required
                                                />
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label for="name"
                                                    >Tên Voucher <span class="text-danger">*</span></label
                                                >
                                                <input
                                                    type="text"
                                                    class="form-control"
                                                    id="name"
                                                    name="name"
                                                    required
                                                />
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label for="type"
                                                    >Loại Voucher <span class="text-danger">*</span></label
                                                >
                                                <select class="form-select" id="type" name="type" required>
                                                    <option value="">Chọn loại</option>
                                                    <option value="percent">
                                                        Giảm theo phần trăm (Cửa hàng)
                                                    </option>
                                                    <%--
                                                    <option value="PERCENTAGE_SYSTEM">
                                                        --%> <%-- Giảm theo phần trăm (Hệ thống)--%> <%--
                                                    </option>
                                                    --%> <%--
                                                    <option value="AMOUNT_SHOP">
                                                        --%> <%-- Giảm số tiền (Cửa hàng)--%> <%--
                                                    </option>
                                                    --%> <%--
                                                    <option value="AMOUNT_SYSTEM">
                                                        --%> <%-- Giảm số tiền (Hệ thống)--%> <%--
                                                    </option>
                                                    --%>
                                                    <option value="money">Giảm cố định (Cửa hàng)</option>
                                                    <option value="shipping">Miễn phí vận chuyển</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row mb-3">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label for="description">Mô tả</label>
                                                <textarea
                                                    class="form-control"
                                                    id="description"
                                                    name="description"
                                                    rows="3"
                                                ></textarea>
                                            </div>
                                        </div>
                                    </div>

                                    <!-- Price Settings -->
                                    <div class="row mb-3">
                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <label for="discount"
                                                    >Giảm giá <span class="text-danger">*</span></label
                                                >
                                                <input
                                                    type="number"
                                                    class="form-control"
                                                    id="discount"
                                                    name="discount"
                                                    required
                                                />
                                            </div>
                                        </div>
                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <label for="minPrice">Giá tối thiểu</label>
                                                <input
                                                    type="number"
                                                    class="form-control"
                                                    id="minPrice"
                                                    name="minPrice"
                                                />
                                            </div>
                                        </div>
                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <label for="maxPrice">Giá tối đa</label>
                                                <input
                                                    type="number"
                                                    class="form-control"
                                                    id="maxPrice"
                                                    name="maxPrice"
                                                />
                                            </div>
                                        </div>
                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <label for="maxDiscount">Giảm giá tối đa</label>
                                                <input
                                                    type="number"
                                                    class="form-control"
                                                    id="maxDiscount"
                                                    name="maxDiscount"
                                                />
                                            </div>
                                        </div>
                                    </div>

                                    <!-- Time Settings -->
                                    <div class="row mb-3">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label for="startDate"
                                                    >Ngày bắt đầu <span class="text-danger">*</span></label
                                                >
                                                <input
                                                    type="datetime"
                                                    class="form-control"
                                                    id="startDate"
                                                    name="startDate"
                                                    required
                                                />
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label for="endDate"
                                                    >Ngày kết thúc <span class="text-danger">*</span></label
                                                >
                                                <input
                                                    type="datetime"
                                                    class="form-control"
                                                    id="endDate"
                                                    name="endDate"
                                                    required
                                                />
                                            </div>
                                        </div>
                                    </div>

                                    <!-- Quantity Settings -->
                                    <div class="row mb-3">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label for="quantity"
                                                    >Số lượng <span class="text-danger">*</span></label
                                                >
                                                <input
                                                    type="number"
                                                    class="form-control"
                                                    id="quantity"
                                                    name="quantity"
                                                    required
                                                />
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label for="status">Trạng thái</label>
                                                <select class="form-select" id="status" name="status">
                                                    <option value="ACTIVE">Kích hoạt</option>
                                                    <option value="INACTIVE">Không kích hoạt</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>

                                    <!-- Applicability Settings -->
                                    <div class="row mb-3">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label for="applyToAll">Áp dụng cho tất cả sản phẩm</label>
                                                <select class="form-select" id="applyToAll" name="applyToAll">
                                                    <option value="true">Có</option>
                                                    <option value="false">Không</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label for="category">Danh mục</label>
                                                <select class="form-select" id="category" name="category">
                                                    <option value="">Chọn danh mục</option>
                                                    <c:forEach items="${categories}" var="category">
                                                        <option value="${category.idCategory}">
                                                            ${category.name}
                                                        </option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row mb-3">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label for="specificProducts">Sản phẩm cụ thể</label>
                                                <select
                                                    class="form-select"
                                                    id="specificProducts"
                                                    name="specificProducts"
                                                    multiple
                                                >
                                                    <c:forEach items="${products}" var="product">
                                                        <option value="${product.idMilkTea}">
                                                            ${product.name}
                                                        </option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                    </div>

                                    <!-- Display selected specific products -->
                                    <div class="row mb-3">
                                        <div class="col-md-12">
                                            <div id="selectedProducts" class="form-group">
                                                <label>Sản phẩm đã chọn:</label>
                                                <ul id="selectedProductsList" class="list-group"></ul>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-12">
                                            <button type="submit" class="btn btn-primary">Lưu voucher</button>
                                            <a href="/admin/voucher" class="btn btn-secondary">Hủy</a>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </main>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
        <script>
            // Form validation
            (function () {
                "use strict";
                var forms = document.querySelectorAll(".needs-validation");
                Array.from(forms).forEach((form) => {
                    form.addEventListener(
                        "submit",
                        (event) => {
                            if (!form.checkValidity()) {
                                event.preventDefault();
                                event.stopPropagation();
                            }
                            form.classList.add("was-validated");

                            // Date format validation
                            // const startDate = document.getElementById("startDate");
                            // const endDate = document.getElementById("endDate");

                            // if (startDate.value) {
                            //     startDate.value = formatDateForSubmit(startDate.value);
                            // }
                            // if (endDate.value) {
                            //     endDate.value = formatDateForSubmit(endDate.value);
                            // }
                        },
                        false
                    );
                });

                // Date validation
                document.getElementById("endDate").addEventListener("change", function () {
                    var startDate = new Date(document.getElementById("startDate").value);
                    var endDate = new Date(this.value);

                    if (endDate <= startDate) {
                        this.setCustomValidity("Ngày kết thúc phải sau ngày bắt đầu");
                    } else {
                        this.setCustomValidity("");
                    }
                });

                // Price validation
                document.getElementById("maxPrice").addEventListener("change", function () {
                    var minPrice = parseInt(document.getElementById("minPrice").value);
                    var maxPrice = parseInt(this.value);

                    if (maxPrice <= minPrice) {
                        this.setCustomValidity("Giá tối đa phải lớn hơn giá tối thiểu");
                    } else {
                        this.setCustomValidity("");
                    }
                });

                // Applicability settings
                const applyToAll = document.getElementById("applyToAll");
                const category = document.getElementById("category");
                const specificProducts = document.getElementById("specificProducts");
                const selectedProductsList = document.getElementById("selectedProductsList");

                applyToAll.addEventListener("change", function () {
                    if (this.value === "true") {
                        category.disabled = true;
                        specificProducts.disabled = true;
                    } else {
                        category.disabled = false;
                        specificProducts.disabled = false;
                    }
                });

                category.addEventListener("change", function () {
                    if (this.value !== "") {
                        applyToAll.disabled = true;
                        specificProducts.disabled = true;
                    } else {
                        applyToAll.disabled = false;
                        specificProducts.disabled = false;
                    }
                });

                specificProducts.addEventListener("change", function () {
                    if (this.selectedOptions.length > 0) {
                        applyToAll.disabled = true;
                        category.disabled = true;
                    } else {
                        applyToAll.disabled = false;
                        category.disabled = false;
                    }

                    // Display selected products
                    selectedProductsList.innerHTML = "";
                    Array.from(this.selectedOptions).forEach((option) => {
                        const li = document.createElement("li");
                        li.classList.add("list-group-item");
                        li.textContent = option.text;
                        const removeButton = document.createElement("button");
                        removeButton.classList.add("btn", "btn-danger", "btn-sm", "float-end");
                        removeButton.textContent = "Xóa";
                        removeButton.addEventListener("click", function () {
                            option.selected = false;
                            li.remove();
                            if (specificProducts.selectedOptions.length === 0) {
                                applyToAll.disabled = false;
                                category.disabled = false;
                            }
                        });
                        li.appendChild(removeButton);
                        selectedProductsList.appendChild(li);
                    });
                });
            })();

            // // Date format helper function
            // function formatDateForSubmit(dateString) {
            //     const date = new Date(dateString);
            //     return date.toISOString().slice(0, 16); // Format: YYYY-MM-DDTHH:mm
            // }
        </script>
    </body>
</html>

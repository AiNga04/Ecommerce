<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User</title>
    <link
            href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
            rel="stylesheet" type="text/css">
    <link
            href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
            rel="stylesheet" type="text/css">
    <link
            href="https://fonts.googleapis.com/css?family=Open+Sans:400,300,600"
            rel="stylesheet" type="text/css">
    <link href='<c:url value="/css/payment.css"/>' rel="stylesheet"
          type="text/css">
    <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
          integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <%--    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"--%>
    <%--            type="text/javascript"></script>--%>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"
            type="text/javascript"></script>

    <script
            src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            type="text/javascript"></script>
    <script
            src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            type="text/javascript"></script>
</head>
<body>
    <div class="payment">
        <div data-name='${dataJSON}' data-id="${idBranch }"
             class="payment-content">
            <h1 class="bold-text" style="margin-bottom: 46px; margin-top: 16px">Thanh
                toán đơn hàng</h1>
            <div class="row w-100">
                <div class="col-5">
                    <div class="payment-container">
                        <div class="mb-4">
                            <h4>Giao hàng</h4>
                            <div class="payment-info">
                                <div class="mb-4" style="position: relative;">
                                    <label for="addressInp" class="form-label">Địa chỉ nhận
                                        hàng </label> <input id="addressInp" type="text"
                                                             class="form-control bold-text" placeholder="Địa chỉ">
                                    <ul class="places-result"></ul>
                                </div>
                                <div class="mb-4" style="position: relative;">
                                    <label for="addressInp" class="form-label">Chọn cửa
                                        hàng</label>
                                    <ul class="list-branches" style="list-style: none">
                                        <c:forEach var="branch" items="${branches }" varStatus="loop">
                                            <li class="list-branches-item">
                                                <div class="d-flex align-items-center">
                                                    <input data-id="${branch.idBranch }" type="radio"
                                                           class="radio-branch" name="branchRadio"
                                                        ${loop.index == 0 ? 'checked' : '' }
                                                           id="flexRadio${branch.idBranch}${loop.index}"/>
                                                    <div class="list-branches-item-info"
                                                         style="margin-left: 12px">
                                                        <p class="m-0 bold-text list-branches-item-name"
                                                           style="font-size: 24px;">${branch.name }</p>
                                                        <p
                                                                class="bold-text text-secondary fst-italic list-branches-item-address"
                                                                style="margin-bottom: 6px">${branch.addressDetail }</p>
                                                        <p class="m-0 branch-fee" style="font-size: 20px;">
                                                            Phí giao hàng: <span
                                                                class="branch-fee-val bold-text text-danger">0đ</span>
                                                        </p>
                                                    </div>
                                                </div>
                                                <hr>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </div>
                                <div class="mb-3">
                                    <label for="surnameInp" class="form-label">Tên khách
                                        hàng </label>
                                    <div class="d-flex align-items-center">
                                        <input id="surnameInp" type="text" readonly="readonly"
                                               value="${customer.surname}" class="form-control"
                                               style="margin-right: 12px"> <input type="text"
                                                                                  readonly="readonly"
                                                                                  value="${customer.name}"
                                                                                  class="form-control">
                                    </div>
                                </div>
                                <div class="mb-3">
                                    <label for="phoneNumberInp" class="form-label">Số điện
                                        thoại </label> <input id="phoneNumberInp" name="phoneNumber"
                                                              type="text" value="${customer.phoneNumber}"
                                                              class="form-control" placeholder="Số điện thoại">
                                </div>
                            </div>
                        </div>
                        <div class="mb-4">
                            <h4>Phương thức thanh toán</h4>

                            <!-- Lặp qua danh sách phương thức thanh toán và tạo radiobutton -->
                            <c:forEach var="paymentMethod" items="${listPayMethod}">
                                <div class="form-check">
                                    <input class="form-check-input-radio" type="radio"
                                           data-id="${paymentMethod.idPayMethod}" name="payMethodByOrder"
                                           id="flexRadioDefault${paymentMethod.idPayMethod}"/> <label
                                        class="form-check-label"
                                        for="flexRadioDefault${paymentMethod.idPayMethod}">
                                        ${paymentMethod.name} </label>
                                </div>
                            </c:forEach>
                        </div>

                        <%-- Display list voucher for product --%>
                        <div class="mb-4">
                            <h4>Mã giảm giá</h4>
                            <div class="payment-voucher">
                                <div class="voucher-result">
                                    <p class="voucher-result-text">Voucher hiện tại: <span
                                            id="currentVoucher">Không có</span></p>
                                    <p class="voucher-result-value">Giảm giá: <span id="currentDiscount">0 VND</span>
                                    </p>
                                    <button class="btn btn-secondary" id="btnSelectVoucher">Chọn voucher khác</button>
                                </div>
                            </div>
                        </div>


                        <div>
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" value=""
                                       id="flexCheckDefault"> <label class="check-label"
                                                                     for="flexCheckDefault"> Tôi đồng ý với các điều
                                khoản
                                và điều kiện mua hàng của Shop </label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-7">
                    <div class="products-choosen">
                        <h4>Các món đã chọn</h4>
                        <ul class="list-product-choosen">
                            <c:forEach var="milkTea" items="${listMilkTea}">
                                <li class="cart-item payment-item">
                                    <div class="cart-item-container">
                                        <c:url
                                                value="/home/image/${milkTea.image != null ? milkTea.image : null }"
                                                var="imgUrl"/>
                                        <img src="${imgUrl}" class="cart-product-image payment-product-image"/>
                                        <div class="cart-item-info">
                                            <div class="d-flex align-items-center"
                                                 style="margin-bottom: 6px">
                                                <p class="cart-product-name m-0">${milkTea.getName()}</p>
                                                <p class="cart-quantity ms-2">x${milkTea.getOrderQuantity()}</p>
                                            </div>
                                            <p class="product-size">${milkTea.getSize()}</p>
                                        </div>
                                    </div>
                                    <p class="cart-price cart-price-payment">${milkTea.getCost()}đ</p>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>

                    <div class="payment-note">
                        <div class="mb-3">
                            <h4 for="exampleFormControlTextarea1" class="form-label">
                                Ghi chú</h4>
                            <textarea class="form-control" id="exampleFormControlTextarea1"
                                      rows="3"></textarea>
                        </div>
                    </div>

                    <div class="payment-price">
                        <h4>Tổng cộng</h4>
                        <ul>
                            <li>
                                <p>Tổng giá tiền</p>
                                <p class="payment-price-val payment-price-value">${orderProduct.getTotalPrice() }đ</p>
                            </li>
                            <li>
                                <p>Tổng số lượng</p>
                                <p class="payment-product payment-price-value">${orderProduct.getTotalProduct() }</p>
                            </li>
                            <li>
                                <p>Phí giao hàng</p>
                                <p class="payment-fee payment-price-value">0đ</p>
                            </li>
                            <li>
                                <p>Thành tiền</p>
                                <p class="payment-final-price payment-price-value">100000đ</p>
                            </li>
                            <li>
                                <p>Ngày đặt hàng</p>
                                <p class="payment-order-day payment-price-value"></p>
                            </li>
                            <li>
                                <p>Ngày giao hàng dự kiến</p>
                                <p class="payment-ship-day payment-price-value"></p>
                            </li>
                        </ul>
                    </div>
                    <button class="cart-btn-submit payment-btn">Đặt hàng</button>
                </div>
            </div>
        </div>
    </div>


    <%-- Modal for selecting voucher --%>
    <div class="modal fade" id="voucherModal" tabindex="-1" aria-labelledby="voucherModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="voucherModalLabel">Chọn Voucher</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="form-group d-flex">
                        <input type="text" class="form-control" id="voucherInp" placeholder="Nhập mã giảm giá">
                        <button class="btn btn-primary ml-2" id="btnCheckVoucher">Kiểm tra</button>
                    </div>


                    <ul class="list-group mt-3" id="voucherList">
                        <!-- Lặp qua danh sách voucher và tạo list item -->
                        <c:forEach var="voucher" items="${listVouchers}">
                            <li class="list-group-item" data-code="${voucher.code}" data-discount="${voucher.discount}"
                                data-type="${voucher.type}">
                                    ${voucher.name} -
                                <c:choose>
                                    <c:when test="${voucher.type == 'Giảm theo phần trăm'}">
                                        Giảm ${voucher.discount}%
                                    </c:when>
                                    <c:when test="${voucher.type == 'Giảm theo tiền'}">
                                        Giảm ${voucher.discount} VND
                                    </c:when>
                                    <c:when test="${voucher.type == 'Giảm phí vận chuyển'}">
                                        Miễn phí vận chuyển
                                    </c:when>
                                </c:choose>
                            </li>
                        </c:forEach>
                    </ul>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
                </div>
            </div>
        </div>
    </div>

    <script type="text/javascript"
            src='<c:url value="/user/js/payment.js" />'></script>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
    <script>
        $(document)
            .ready(function () {
                $('#btnSelectVoucher')
                    .click(function () {
                        $('#voucherModal')
                            .modal('show');
                    });

                // $('#voucherList')
                //     .on('click', '.list-group-item', function () {
                //         var voucherCode = $(this)
                //             .data('code');
                //         var discountValue = $(this)
                //             .data('discount');
                //         $('#currentVoucher')
                //             .text(voucherCode);
                //         $('#currentDiscount')
                //             .text(discountValue + ' VND');
                //
                //         // Update total amount
                //         var totalAmount = parseInt($('.payment-final-price')
                //             .text()
                //             .replace(/[^0-9]/g, ''));
                //         var newTotalAmount = totalAmount - discountValue;
                //         // check if newTotalAmount is less than 0, set it to 0
                //         newTotalAmount = newTotalAmount < 0 ? 0 : newTotalAmount;
                //         $('.payment-final-price')
                //             .text(newTotalAmount + 'đ');
                //
                //         $('#voucherModal')
                //             .modal('hide');
                //     });
                //
                // $('#btnCheckVoucher')
                //     .click(function () {
                //         var voucherCode = $('#voucherInp')
                //             .val();
                //         // Implement your voucher checking logic here
                //         // For demonstration, we'll just set the voucher code and discount value
                //         $('#currentVoucher')
                //             .text(voucherCode);
                //         $('#currentDiscount')
                //             .text('10,000 VND'); // Replace with actual discount value
                //
                //         // Update total amount
                //         var discountValue = 10000; // Replace with actual discount value
                //         var totalAmount = parseInt($('.payment-final-price')
                //             .text()
                //             .replace(/[^0-9]/g, ''));
                //         var newTotalAmount = totalAmount - discountValue;
                //         newTotalAmount = newTotalAmount < 0 ? 0 : newTotalAmount;
                //         $('.payment-final-price')
                //             .text(newTotalAmount + 'đ');
                //     });

                $('#voucherList')
                    .on('click', '.list-group-item', function () {
                        var voucherCode = $(this)
                            .data('code');
                        var discountValue = parseInt($(this)
                            .data('discount'));
                        var discountType = $(this)
                            .data('type'); // Assuming you have a data-type attribute for the type

                        console.log(discountType);
                        $('#currentVoucher')
                            .text(voucherCode);

                        var totalAmount = parseInt($('.payment-final-price')
                            .text()
                            .replace(/[^0-9]/g, ''));
                        var newTotalAmount;

                        if (discountType === 'Giảm theo phần trăm') {
                            newTotalAmount = totalAmount - (totalAmount * discountValue / 100);
                            $('#currentDiscount')
                                .text('Giảm ' + discountValue + '%');
                        } else if (discountType === 'Giảm theo tiền') {
                            newTotalAmount = totalAmount - discountValue;
                            $('#currentDiscount')
                                .text('Giảm ' + discountValue + ' VND');
                        } else if (discountType === 'Giảm phí vận chuyển') {
                            // Assuming shipping cost is a fixed value, e.g., 30000 VND
                            var shippingCost = 30000;
                            newTotalAmount = totalAmount - shippingCost;
                            $('#currentDiscount')
                                .text('Miễn phí vận chuyển');
                        } else {
                            newTotalAmount = totalAmount;
                            $('#currentDiscount')
                                .text('');
                        }

                        // Ensure newTotalAmount is not less than 0
                        newTotalAmount = newTotalAmount < 0 ? 0 : newTotalAmount;

                        // Update the final price without formatting
                        $('.payment-final-price')
                            .text(newTotalAmount + 'đ');

                        // Close the modal
                        $('#voucherModal')
                            .modal('hide');
                    });

                $('#btnCheckVoucher')
                    .click(function () {
                        var voucherCode = $('#voucherInp')
                            .val();
                        // Implement your voucher checking logic here
                        // For demonstration, we'll just set the voucher code and discount value
                        var discountValue = 10000; // Replace with actual discount value
                        var discountType = $(this)
                            .data('type'); // Assuming you have a data-type attribute for the type

                        $('#currentVoucher')
                            .text(voucherCode);

                        var totalAmount = parseInt($('.payment-final-price')
                            .text()
                            .replace(/[^0-9]/g, ''));
                        var newTotalAmount;

                        if (discountType === 'Giảm theo phần trăm') {
                            newTotalAmount = totalAmount - (totalAmount * discountValue / 100);
                            $('#currentDiscount')
                                .text('Giảm ' + discountValue + '%');
                        } else if (discountType === 'Giảm theo tiền') {
                            newTotalAmount = totalAmount - discountValue;
                            $('#currentDiscount')
                                .text('Giảm ' + discountValue + ' VND');
                        } else if (discountType === 'Giảm phí vận chuyển') {
                            // Assuming shipping cost is a fixed value, e.g., 30000 VND
                            var shippingCost = 30000;
                            newTotalAmount = totalAmount - shippingCost;
                            $('#currentDiscount')
                                .text('Miễn phí vận chuyển');
                        } else {
                            newTotalAmount = totalAmount;
                            $('#currentDiscount')
                                .text('');
                        }

                        // Ensure newTotalAmount is not less than 0
                        newTotalAmount = newTotalAmount < 0 ? 0 : newTotalAmount;

                        // Update the final price without formatting
                        $('.payment-final-price')
                            .text(newTotalAmount + 'đ');
                    });
            });
    </script>
</body>
</html>
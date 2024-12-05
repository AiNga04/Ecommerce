<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <title>Chỉnh sửa mã giảm giá</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link
            href="https://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all"
            rel="stylesheet"
            type="text/css"
        />
        <style>
            body {
                font-family: "Open Sans", sans-serif;
                background-color: #f8f9fa;
                margin: 0;
                padding: 0;
            }

            .container {
                margin-top: 50px;
            }

            .page-title {
                text-align: center;
                margin-bottom: 30px;
            }

            .breadcrumb {
                background-color: #e9ecef;
                padding: 10px;
                border-radius: 5px;
            }

            .breadcrumb a {
                color: #007bff;
                text-decoration: none;
            }

            .breadcrumb a:hover {
                text-decoration: underline;
            }

            .portlet {
                border: 1px solid #dee2e6;
                border-radius: 5px;
                background-color: #fff;
                padding: 20px;
            }

            .portlet-title {
                font-size: 18px;
                font-weight: bold;
                margin-bottom: 20px;
            }

            .form-horizontal .form-group {
                margin-bottom: 15px;
            }

            .form-horizontal .form-group label {
                text-align: right;
            }

            .form-horizontal .form-group .form-control {
                width: 100%;
            }

            .form-actions {
                text-align: center;
                margin-top: 20px;
            }

            .btn {
                padding: 10px 20px;
                border-radius: 5px;
            }

            .btn-success {
                background-color: #28a745;
                border-color: #28a745;
                color: #fff;
            }

            .btn-default {
                background-color: #6c757d;
                border-color: #6c757d;
                color: #fff;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h3 class="page-title">Quản Lý Chương Trình Khuyến Mãi</h3>
            <div class="breadcrumb">
                <a href="/Admin">Home</a> &gt;
                <a href="/Admin/voucher">Quản lý chương trình khuyến mãi</a> &gt;
                <span>Chỉnh sửa mã giảm giá</span>
            </div>
            <div class="portlet">
                <div class="portlet-title">CHỈNH SỬA MÃ GIẢM GIÁ</div>
                <div class="portlet-body form">
                    <form action="/Admin/voucher/save" method="post" class="form-horizontal">
                        <div class="form-body">
                            <h3 class="form-section">Thông tin mã giảm giá</h3>
                            <div class="form-group">
                                <label class="control-label col-md-3" for="voucherId">Mã voucher</label>
                                <div class="col-md-4">
                                    <input
                                        type="text"
                                        class="form-control"
                                        id="voucherId"
                                        name="voucherId"
                                        value="${voucher.voucherId}"
                                        readonly
                                    />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-3" for="voucherCode">Mã code</label>
                                <div class="col-md-4">
                                    <input
                                        type="text"
                                        class="form-control"
                                        id="voucherCode"
                                        name="voucherCode"
                                        value="${voucher.voucherCode}"
                                    />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-3" for="voucherType">Loại voucher</label>
                                <div class="col-md-4">
                                    <input
                                        type="text"
                                        class="form-control"
                                        id="voucherType"
                                        name="voucherType"
                                        value="${voucher.voucherType}"
                                    />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-3" for="voucherValue"
                                    >Giá trị voucher</label
                                >
                                <div class="col-md-4">
                                    <input
                                        type="text"
                                        class="form-control"
                                        id="voucherValue"
                                        name="voucherValue"
                                        value="${voucher.voucherValue}"
                                    />
                                    <span class="input-group-addon">VND</span>
                                </div>
                            </div>
                            <h3 class="form-section">Thời hạn của mã giảm giá</h3>
                            <div class="form-group">
                                <label class="control-label col-md-3" for="startDate">Ngày bắt đầu</label>
                                <div class="col-md-4">
                                    <input
                                        type="datetime-local"
                                        class="form-control"
                                        id="startDate"
                                        name="startDate"
                                        value="${voucher.startDate}"
                                    />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-3" for="endDate">Ngày kết thúc</label>
                                <div class="col-md-4">
                                    <input
                                        type="datetime-local"
                                        class="form-control"
                                        id="endDate"
                                        name="endDate"
                                        value="${voucher.endDate}"
                                    />
                                </div>
                            </div>
                            <h3 class="form-section">Trạng thái mã giảm giá</h3>
                            <div class="form-group">
                                <label class="control-label col-md-3">Tình trạng</label>
                                <div class="col-md-4">
                                    <div class="radio-list">
                                        <label
                                            ><input type="radio" id="active" name="active" value="1"
                                            ${voucher.active == 1 ? 'checked' : ''}> Chưa được sử dụng</label
                                        >
                                        <label
                                            ><input type="radio" id="inactive" name="active" value="0"
                                            ${voucher.active == 0 ? 'checked' : ''}> Đã được sử dụng</label
                                        >
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-actions">
                            <button type="submit" class="btn btn-success">Submit</button>
                            <button type="button" class="btn btn-default" onclick="window.history.back()">
                                Cancel
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                const form = document.querySelector("form");
                form.addEventListener("submit", function (event) {
                    // Add your form validation logic here
                    const voucherCode = document.getElementById("voucherCode").value;
                    const voucherType = document.getElementById("voucherType").value;
                    const voucherValue = document.getElementById("voucherValue").value;
                    const startDate = document.getElementById("startDate").value;
                    const endDate = document.getElementById("endDate").value;

                    if (!voucherCode || !voucherType || !voucherValue || !startDate || !endDate) {
                        alert("Please fill in all fields.");
                        event.preventDefault();
                    }
                });
            });
        </script>
    </body>
</html>

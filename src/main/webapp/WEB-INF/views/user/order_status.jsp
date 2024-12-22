<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Trạng thái đơn hàng</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5" style="padding: 60px">
  <h2 class="text-center">Trạng thái đơn hàng</h2>

  <!-- Dùng d-flex để căn chỉnh các phần tử trong một dòng -->
  <div class="d-flex justify-content-between align-items-center position-relative">
    <!-- Thanh tiến trình -->
    <div class="progress w-75 position-absolute" style="height: 5px;top: 33%;z-index: 0;left: 13%;right: 28%;">
      <div class="progress-bar bg-success" role="progressbar" style="width: ${userOrder.orderState * 33.33}%" aria-valuenow="${userOrder.orderState * 33.33}" aria-valuemin="0" aria-valuemax="100"></div>
    </div>

    <!-- Vòng tròn bước 1 -->
    <div class="position-relative text-center" style="width: 25%; display: flex; flex-direction: column; align-items: center;">
      <div class="rounded-circle ${userOrder.orderState >= 0 ? 'bg-success' : 'bg-secondary'} text-white d-flex justify-content-center align-items-center"
           style="width: 40px; height: 40px; z-index: 2;">
        1
      </div>
      <span>Chờ xử lý</span>
    </div>

    <!-- Vòng tròn bước 2 -->
    <div class="position-relative text-center" style="width: 25%; display: flex; flex-direction: column; align-items: center;">
      <div class="rounded-circle ${userOrder.orderState >= 1 ? 'bg-success' : 'bg-secondary'} text-white d-flex justify-content-center align-items-center"
           style="width: 40px; height: 40px; z-index: 2;">
        2
      </div>
      <span>Đã xác nhận</span>
    </div>

    <!-- Vòng tròn bước 3 -->
    <div class="position-relative text-center" style="width: 25%; display: flex; flex-direction: column; align-items: center;">
      <div class="rounded-circle ${userOrder.orderState >= 2 ? 'bg-success' : 'bg-secondary'} text-white d-flex justify-content-center align-items-center"
           style="width: 40px; height: 40px; z-index: 2;">
        3
      </div>
      <span>Đang giao hàng</span>
    </div>

    <!-- Vòng tròn bước 4 -->
    <div class="position-relative text-center" style="width: 25%; display: flex; flex-direction: column; align-items: center;">
      <div class="rounded-circle ${userOrder.orderState >= 3 ? 'bg-success' : 'bg-secondary'} text-white d-flex justify-content-center align-items-center"
           style="width: 40px; height: 40px; z-index: 2;">
        4
      </div>
      <span>Đã nhận hàng</span>
    </div>
  </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
</body>
</html>


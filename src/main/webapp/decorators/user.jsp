<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="icon" type="image/x-icon"
          href="https://raw.githubusercontent.com/ThaiVanHandSome/logo/master/icons8-bubble-tea-100.png">
    <title>ALOTRA</title>
    <link href='<c:url value="/user/css/header.css" />' rel="stylesheet"/>
    <link href='<c:url value="/user/css/footer.css" />' rel="stylesheet"/>
    <link
            href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
            rel="stylesheet" type="text/css">
    <link
            href="https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,300;0,400;0,500;0,600;0,700;1,400;1,500;1,600;1,700&display=swap"
            rel="stylesheet">
    <link href='<c:url value="/user/css/home.css" />' rel="stylesheet"/>
    <link href='<c:url value="/user/css/payment.css"/>' rel="stylesheet"
          type="text/css">
    <link href='<c:url value="/user/css/product_detail.css"/>'
          rel="stylesheet" type="text/css">
    <link href='<c:url value="/user/css/order.css"/>' rel="stylesheet"
          type="text/css">
    <link href='<c:url value="/user/css/branches.css"/>' rel="stylesheet"
          type="text/css">
    <link href='<c:url value="/user/css/branches_info.css"/>'
          rel="stylesheet" type="text/css">
    <link href='<c:url value="/user/css/cart.css"/>' rel="stylesheet"
          type="text/css">
    <link href='<c:url value="/user/css/user_infor.css"/>' rel="stylesheet"
          type="text/css">
    <link href='<c:url value="/user/css/products.css"/>' rel="stylesheet"
          type="text/css">
    <link href='<c:url value="/user/css/search.css"/>' rel="stylesheet"
          type="text/css">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"
          integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>
</head>
<body>
    <%@ include file="/common/user/header/header.jsp" %>
    <sitemesh:write property='body'/>
    <%@ include file="/common/user/footer/footer.jsp" %>

    <!-- JS -->
    <%--	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"--%>
    <%--		type="text/javascript"></script>--%>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"
            type="text/javascript"></script>
    <script
            src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            type="text/javascript"></script>
    <script
            src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            type="text/javascript"></script>
    <script type="text/javascript" src="https://popupsmart.com/freechat.js"></script>
    <script> window.start.init({
        title: "Hi there ✌️ welcome to ALOTRA",
        message: "How may we help you? Just send us a message now to get assistance.",
        color: "#eaef26",
        position: "right",
        placeholder: "Enter your message",
        withText: "Write with",
        viaWhatsapp: "Or write us directly via Whatsapp",
        gty: "Go to your",
        awu: "and write us",
        connect: "Connect now",
        button: "Write us",
        device: "everywhere",
        logo: "https://d2r80wdbkwti6l.cloudfront.net/eGM8mHgkE7WXSBi6wTRTUbVc380j9qy0.jpg",
        person: "https://d2r80wdbkwti6l.cloudfront.net/G6RH57yfCm7NvoBPaulrbqwjuVuCrsnR.jpg",
        services: [{"name": "messenger", "content": "61569707863238"}]
    })</script>
</body>
</html>
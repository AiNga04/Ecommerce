<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Payment Status</title>
    <link href='<c:url value="/css/payment.css"/>' rel="stylesheet" type="text/css">
</head>
<body>
    <div class="container m-5 p-4"></div>
        <div class="row justify-content-center">
            <div class="col-md-5">
                <!-- <c:out value="${status}" /> -->
                <c:choose>
                    <c:when test="${status == 'success'}">
                        <div class="message-box _success">
                            <i class="fa fa-check-circle" aria-hidden="true"></i>
                            <h2>Your payment was successful</h2>
                            <p>Thank you for your payment. We will<br>be in contact with more details shortly.</p>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="message-box _failed">
                            <i class="fa fa-times-circle" aria-hidden="true"></i>
                            <h2>Your payment failed</h2>
                            <p>Unfortunately, your payment could not be processed.<br>Please try again later.</p>
                        </div>
                    </c:otherwise>
                </c:choose>
                <div class="text-center m-4">
                    <a href="<c:url value='/home' />" class="btn btn-primary">Go to Home</a>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
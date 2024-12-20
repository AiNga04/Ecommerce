<%@page import="hcmute.service.impl.CookieServiceImpl" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="hcmute.entity.MilkTeaCategoryEntity" %>
<%@ page import="hcmute.entity.MilkTeaTypeEntity" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="hcmute.service.IMilkTeaCategoryService" %>
<%@ page import="hcmute.service.IMilkTeaTypeService" %>
<%@ page import="org.springframework.web.context.WebApplicationContext" %>
<%@ page
        import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%
    WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(application);
// Retrieve the beans
    IMilkTeaCategoryService milkTeaCategoryService = context.getBean(IMilkTeaCategoryService.class);
    IMilkTeaTypeService milkTeaTypeService = context.getBean(IMilkTeaTypeService.class);
    List<MilkTeaCategoryEntity> categories = milkTeaCategoryService.findAll();
    List<List<MilkTeaTypeEntity>> types = new ArrayList<>();
    request.setAttribute("categories", categories);
    for (MilkTeaCategoryEntity category : categories) {
        List<MilkTeaTypeEntity> typesForCategory = milkTeaTypeService.findAllByCategoryId(category.getIdCategory());
        types.add(typesForCategory);
    }
    request.setAttribute("types", types);
    Cookie[] cookies = request.getCookies();
    int idUser = 0;
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if ("USER_ID".equals(cookie.getName())) {
                idUser = Integer.parseInt(cookie.getValue());
                request.setAttribute("user_id", idUser);
                break;
            }
        }
    }
%>

<!DOCTYPE html>
<html>
<head>
    <link href='<c:url value="/user/css/header.css" />' rel="stylesheet"/>
</head>
<body>
    <header class="header">
        <div class="container-left">
            <a class="d-block" href="/home"> <img
                    src="https://raw.githubusercontent.com/ThaiVanHandSome/logo/master/alotra-high-resolution-logo-black-transparent.png"
                    class="logo"/>
            </a>
            <ul class="nav-list">
                <li class="nav-item"><a class="nav-item-link" href="/products">Menu</a>
                    <div class="header-menu">
                        <div class="row">
                            <c:set var="index" value="0"/>
                            <c:forEach var="category" items="${categories}">
                                <div class="col">
                                    <p class="header-category">${category.name}</p>
                                    <ul class="type-product-list">
                                        <c:forEach var="type" items="${types[index]}">
                                            <li class="type-product-item"><a
                                                    href="/products/type/${type.idType}"
                                                    class="type-product-link">${type.name}</a></li>
                                        </c:forEach>
                                    </ul>
                                </div>
                                <c:set var="index" value="${index + 1}"/>
                            </c:forEach>
                        </div>
                    </div>
                </li>
                <li class="nav-item"><a class="nav-item-link" href="/branches">Cửa
                    hàng</a></li>
            </ul>
        </div>
        <form action="/header/moveToSearchPage" method="get"
              accept-charset="UTF-8">
            <div class="search-container">
                <input type="text" class="search-inp" name="content"/>
                <button class="search-btn" type="submit">
                    <i class="fa-solid fa-magnifying-glass"></i>
                </button>
            </div>
        </form>
        <%--		notificaiton--%>
        <div class="dropdown">
            <i class="fa fa-bell notification-bell" id="notification-bell"></i>
            <ul class="dropdown-menu dropdown-menu-end" id="notification-list">
                <!-- Notifications will be appended here -->
            </ul>
        </div>
        <div class="container-right">
            <div class="header-info">
                <img
                        src="https://scontent.fsgn8-4.fna.fbcdn.net/v/t39.30808-6/241464176_1242056446291086_5810272849317935739_n.jpg?_nc_cat=111&ccb=1-7&_nc_sid=efb6e6&_nc_ohc=2tzcJr-V8XwAX_Jrr1h&_nc_ht=scontent.fsgn8-4.fna&oh=00_AfAmVlLJ6-h-sZlmoma56shb3pX1V4kcYdrmo3ytCNKJsg&oe=657653CD"
                        class="avatar"/>
                <p class="username">
                    <c:if test="${not empty pageContext.request.remoteUser}">
						<span class="fw-bold"><c:out
                                value="${pageContext.request.remoteUser}"/></span>
                    </c:if>
                </p>
                <ul class="header-action">
                    <c:if test="${not empty pageContext.request.remoteUser}">
                        <li class="header-action-item"><a
                                href="/user_infor/${user_id }" class="header-action-link"
                                href="#">Quản lý tài khoản</a></li>
                        <li class="header-action-item"><a class="header-action-link"
                                                          href="/cart">Giỏ hàng của tôi</a></li>
                        <li class="header-action-item"><a class="header-action-link"
                                                          href="/order">Đơn hàng của tôi</a></li>
                        <li class="header-action-item"><a class="header-action-link"
                                                          href="/help">Trợ giúp</a></li>
                        <li class="header-action-item"><a class="header-action-link"
                                                          href="/security/forgot-password">Quên mật khẩu</a></li>
                        <li class="header-action-item"><a class="header-action-link"
                                                          href="/admin/index">Quản trị</a></li>
                        <li class="header-action-item"><a class="header-action-link"
                                                          href="/manager/index">Manager</a></li>
                        <li class="header-action-item"><a class="header-action-link"
                                                          href="/security/logout">Đăng xuất</a></li>
                    </c:if>
                    <c:if test="${empty pageContext.request.remoteUser}">
                        <li class="header-action-item"><a class="header-action-link"
                                                          href="/security/login">Đăng nhập</a></li>
                        <li class="header-action-item"><a class="header-action-link"
                                                          href="/security/register">Đăng ký</a></li>
                        <li class="header-action-item"><a class="header-action-link"
                                                          href="/security/forgot-password">Quên mật khẩu</a></li>
                    </c:if>
                </ul>
            </div>
        </div>
    </header>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.min.js"></script>
    <script>
        const BASE_URL = "http://localhost:8081/ws";
        const CHAT_CHANNEL = "/app/notify";
        const NOTIFICATION_CHANNEL = "/topic/notification";
        const AUTHORIZATION_TOKEN = "<%= idUser %>"; // Get userId from server-side
        const connect = (url) => {
            const sockJS = new SockJS(url);
            return Stomp.over(sockJS, {protocol: ["v12.stomp"]});
        };
        const fetchNotifications = (AUTHORIZATION_TOKEN) => {
            const temp = `http://localhost:8081/notifications?Authorization=` + AUTHORIZATION_TOKEN;
            $.ajax({
                url: temp,
                method: "GET",
                success: function (response) {
                    const notifications = response.data.items;
                    notifications.forEach((notification) => addNotification(notification));
                },
                error: function (error) {
                    console.error("Error fetching notifications:", error);
                },
            });
        };
        const addNotification = (notification) => {
            const notificationList = document.getElementById("notification-list");
            if (!notificationList) {
                console.error("#notification-list không tồn tại");
                return;
            }
            // Tạo phần tử <li>
            const listItem = document.createElement('li');
            listItem.classList.add('list-group-item');
            // Tạo phần tử con cho status (chỉ Status in đậm)
            const statusElement = document.createElement('strong');
            statusElement.textContent = 'Title: ';  // 'Status' in đậm
            const statusText = document.createElement('span');
            statusText.textContent = notification.title;  // Phần giá trị status không in đậm
            // Tạo phần tử con cho message
            const messageElement = document.createElement('strong');
            messageElement.textContent = 'Content: ';  // 'Message' in đậm
            const messageText = document.createElement('span');
            messageText.textContent = notification.content;  // Phần giá trị message không in đậm
            // Tạo phần tử con cho order ID
            const orderIdElement = document.createElement('strong');
            orderIdElement.textContent = 'Data ';  // 'Order ID' in đậm
            const orderIdText = document.createElement('span');
            orderIdText.textContent = notification.data;  // Phần giá trị orderId không in đậm
            // Append các phần tử vào listItem
            listItem.appendChild(statusElement);
            listItem.appendChild(statusText);  // Giá trị của status không in đậm
            listItem.appendChild(document.createElement('br'));  // Thêm dòng mới
            listItem.appendChild(messageElement);
            listItem.appendChild(messageText);  // Giá trị của message không in đậm
            listItem.appendChild(document.createElement('br'));  // Thêm dòng mới
            listItem.appendChild(orderIdElement);
            listItem.appendChild(orderIdText);  // Giá trị của orderId không in đậm
            // Thêm <li> vào notificationList
            notificationList.insertBefore(listItem, notificationList.firstChild);
        };
        const saveNotificationToLocalStorage = (msg) => {
            let notifications = JSON.parse(localStorage.getItem("notifications")) || [];
            notifications.push(msg);
            localStorage.setItem("notifications", JSON.stringify(notifications));
        };
        const loadNotificationsFromLocalStorage = () => {
            let notifications = JSON.parse(localStorage.getItem("notifications")) || [];
            notifications.forEach((notification) => addNotification(notification));
        };
        $(document)
            .ready(() => {
                // loadNotificationsFromLocalStorage();
                fetchNotifications(AUTHORIZATION_TOKEN);
                const stompClient = connect(BASE_URL);
                stompClient.connect({}, (frame) => {
                    console.log("FRAME:", frame);
                    // user/user_id/notifications
                    const subUrl = '/user/' + AUTHORIZATION_TOKEN + '/notifications';
                    stompClient.subscribe(subUrl, (data) => {
                        console.log("Receive data: ", data);
                        const {body} = data;
                        const contents = JSON.parse(body);
                        // genChatMsg(contents);
                        addNotification(contents);
                        saveNotificationToLocalStorage(contents);
                    });
                    stompClient.subscribe(NOTIFICATION_CHANNEL, (data) => {
                        const {body} = data;
                        console.log("Notification: ", body);
                        // genChatMsg(body);
                        addNotification(body);
                        saveNotificationToLocalStorage(body);
                    });
                });
                $("#notification-bell")
                    .click((event) => {
                        event.stopPropagation();
                        const notificationList = $("#notification-list");
                        if (notificationList.is(":visible")) {
                            notificationList.hide();
                        } else {
                            notificationList.show();
                        }
                    });
                $(document)
                    .click((event) => {
                        if (!$(event.target)
                            .closest("#notification-bell").length) {
                            $("#notification-list")
                                .hide();
                        }
                    });
            });
    </script>
</body>
</html>
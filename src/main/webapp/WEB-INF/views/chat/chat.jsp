<%--
  Created by IntelliJ IDEA.
  User: Nga
  Date: 12/6/2024
  Time: 3:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="static/css/main.css">
    <title>Chat Application</title>
</head>
<body>

<h2>One to One Chat | Spring boot & Websocket | By Shop</h2>

<div class="user-form" id="username-page">
    <h2>Enter Chatroom</h2>
    <form id="usernameForm">
        <label for="username">User Name:</label>
        <input type="text" id="username" name="username" required>

        <label for="fullname">Real Name:</label>
        <input type="text" id="fullname" name="realname" required>

        <button type="submit">Enter Chatroom</button>
    </form>
</div>

<div class="chat-container hidden" id="chat-page">
    <div class="users-list">
        <div class="users-list-container">
            <h2>Online Users</h2>
            <ul id="connectedUsers">
            </ul>
        </div>
        <div>
            <p id="connected-user-fullname"></p>
            <a class="logout" href="javascript:void(0)" id="logout">Logout</a>
        </div>
    </div>

    <div class="chat-area">
        <div class="chat-area" id="chat-messages">
        </div>

        <form id="messageForm" name="messageForm" class="hidden">
            <div class="message-input">
                <input autocomplete="off" type="text" id="message" placeholder="Type your message...">
                <button>Send</button>
            </div>
        </form>
    </div>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.4/sockjs.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
<script src="static/js/main.js"></script>
</body>
</html>

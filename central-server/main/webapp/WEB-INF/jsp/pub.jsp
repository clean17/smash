<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Spring Integration GCP sample</title>
</head>
<body>
<div name="formDiv">
    <form action="/publishMessage" method="post">
        Publish message: <input type="text" name="message" /> <input type="submit" value="Publish!"/>
    </form>
</div>
</body>
</html>
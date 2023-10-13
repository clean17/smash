<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>

</head>
<body>
<%--    <c:set var="name" value="[{'id':1, 'name':'length'}, {'id':2, 'name':'toogn'}]" />--%>
    <c:set var="name" value='[{&quot;id&quot;:1, &quot;name&quot;:&quot;length&quot;}, {&quot;id&quot;:2, &quot;name&quot;:&quot;toogn&quot;}]'/>
    <div>
        여기에 표시됩니다. ${name}
    </div>

</body>
<script>

    let temp = JSON.parse('${name}');
    alert(temp);
    let temp2 = ${scoop}  '없어';

</script>
</html>
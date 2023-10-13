<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    테스트
</body>
<script>
    /**
     * 프로토타입 프로퍼티 추가
     * @param formatString
     * @returns {*}
     */
    Date.prototype.format = function (formatString) {
        const pad = (number, length = 2) => {
            let str = String(number);
            while (str.length < length) {
                str = '0' + str;
            }
            return str;
        };
        return formatString.replace('yyyy', this.getFullYear())
            .replace('mm', pad(this.getMonth() + 1)) // month: 0 = 1월
            .replace('dd', pad(this.getDate()));
    };
    const date = new Date();
    console.log(date.format('yyyy.mm.dd')); // 2023.04.01
</script>
</html>
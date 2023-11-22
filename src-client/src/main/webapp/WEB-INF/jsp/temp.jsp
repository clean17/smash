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
    비디오 테스트
    <div id="video">

    </div>
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

    function initPage() {

        fetch('/video/stream2', {
            method: 'GET',
        }).then(res => res.json())
            .then(res => {
                const videoBox = document.querySelector('#video');
                const videoElement = document.createElement('video');

                videoElement.setAttribute('width', '100%');
                videoElement.setAttribute('height', '100%');
                videoElement.setAttribute('controls', '');
                videoElement.setAttribute('autoplay', '');

                const videoSource = document.createElement('source');
                videoSource.setAttribute('src', res.url);
                videoSource.setAttribute('type', 'video/mp4');

                videoElement.appendChild(videoSource);
                videoBox.appendChild(videoElement);
            });

    }

    document.addEventListener("DOMContentLoaded", initPage);
</script>
</html>
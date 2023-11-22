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
    <div id="myVideo">

    </div>
    <button onclick="fncPlay()" style="width: 100px; height: 40px" >플레이</button>
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

    function fncPlay() {
        const videoBox = document.querySelector('#myVideo');

        // blob 구현
        fetch('/video/resion')
            .then(response => {
                if (response.ok) {
                    return response.blob();
                }
                throw new Error('Network response was not ok.');
            })
            .then(blob => {
                console.log('blob 성공')
                const videoUrl = URL.createObjectURL(blob);
                const videoElement = document.createElement('video');

                videoElement.src = videoUrl;
                videoElement.style.width = '100%'
                videoElement.style.height = '100%'

                videoElement.controls = true;
                videoElement.autoplay = true;

                videoBox.appendChild(videoElement);
            })
            .catch(error => {
                console.error('Fetch error:', error);
            });
    }

    function initPage() {

    }

    document.addEventListener("DOMContentLoaded", initPage);
</script>
</html>
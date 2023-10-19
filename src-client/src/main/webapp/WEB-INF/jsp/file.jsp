<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Drag & Drop</title>
    <style>
        #dropzone {
            width: 300px;
            height: 200px;
            border: 2px dashed #aaa;
            text-align: center;
            line-height: 180px;
        }
        #dropzone.dragover {
            border-color: #f00;
        }
    </style>
</head>
<body>
<div id="dropzone">여기에 파일을 드롭하세요</div>

<script>
    const dropzone = document.getElementById('dropzone');

    // 드래그 객체가 영역에 들어올 때
    dropzone.addEventListener('dragenter', (e) => {
        e.preventDefault();
    });

    // 영역 위
    dropzone.addEventListener('dragover', (e) => {
        e.preventDefault();
        dropzone.classList.add('dragover');
    });

    // 떠널 때
    dropzone.addEventListener('dragleave', () => {
        dropzone.classList.remove('dragover');
    });

    // 떨어드릴 때
    dropzone.addEventListener('drop', (e) => {
        e.preventDefault();
        dropzone.classList.remove('dragover');

        const files = e.dataTransfer.files;
        console.log(files); // 드롭된 파일들을 처리합니다.
    });
</script>
</body>
</html>

angular.module('demo', []) // 모듈 생성
.controller('Hello', function($scope, $http) {
    $http.get('http://localhost:8080/greeting').
        then(function(response) {
            $scope.greeting = response.data; // greeting 변수(스코프)에 응답받은 데이터 할당(json)
        });
});

/*
* 위 앵귤러의 컨트롤러를 html에서 앵귤러 문법으로 호출한다.
* <div ng-controller="Hello">
    <p>The ID is {{greeting.id}}</p>
    <p>The content is {{greeting.content}}</p>
</div>
* */
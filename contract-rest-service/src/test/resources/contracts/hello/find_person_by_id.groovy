// REST 서비스 계약
import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should return person by id=1"

    // 해당 요청이 들어오면
    request {
        url "/person/1"
        method GET()
    }

    // 아래의 결과를 리턴하도록 지정
    // 이 계약에 의해서 body 데이터가 현재 파일의 설정대로 변경되어야 함
    response {
        status OK()
        headers {
            contentType applicationJson()
        }
        body (
                id: 1,
                name: "foo",
                surname: "bee"
        )
    }
}
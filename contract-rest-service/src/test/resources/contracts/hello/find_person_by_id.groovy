/**
 *  REST 서비스 계약
 *
 * 계약 테스트를 위한 절차
 * - src/test/resources/contracts 디렉토리에 계약을 정의, Groovy 또는 yml 파일로
 * - 계약 테스트의 기본 동작을 정의하는 Base Class를 작성 - 모킹, 초기설정
 * - 터미널에서 ./gradlew contractTest 실행 ( 계약 테스트를 contractTest로 이름지었음 )
 */
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
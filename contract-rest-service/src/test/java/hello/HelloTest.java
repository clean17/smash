package hello;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class HelloTest extends BaseClass{

    @Test
    public void testFindPersonById() throws Exception{
        System.out.println("테스트");
    }
}

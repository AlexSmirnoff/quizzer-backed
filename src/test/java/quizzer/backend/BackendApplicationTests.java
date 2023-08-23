package quizzer.backend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class BackendApplicationTests {

	@Test
	void contextLoads(ApplicationContext applicationContext) {
        assert applicationContext != null;
	}

}

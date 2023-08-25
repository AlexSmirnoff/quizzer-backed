package quizzer.backend.api.http.auth.encoders;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.util.StopWatch;

class TestArgon2Tuning {
    @Test
    @Ignore //excludes test from automatic execution
    void tuneArgon2Params() { //NOSONAR this is a manual test for argon2 parameter tuning
        var memory = 60000;
        var iterations = 8;
        var encoder = new Argon2PasswordEncoder(
                128,
                128,
                1,
                memory,
                iterations
        );
        var watch = new StopWatch();
        watch.start("encode");
        encoder.encode("password");
        watch.stop();
        System.out.println(watch.getTotalTimeMillis());
    }

    private @interface Ignore {}
}

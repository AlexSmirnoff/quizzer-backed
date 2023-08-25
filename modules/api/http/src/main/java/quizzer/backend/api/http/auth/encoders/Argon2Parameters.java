package quizzer.backend.api.http.auth.encoders;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "argon2")
public class Argon2Parameters {
    private final Integer saltLength;
    private final Integer hashLength;
    private final Integer parallelism;
    private final Integer memory;
    private final Integer iterations;
}

package quizzer.backend.spi.database.auth.model;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;
import quizzer.backend.auth.model.GrantedAuthority;

@Data
@Table(name = "granted_authorities", schema = "auth")
public class StoredGrantedAuthority implements GrantedAuthority {
    private Long userId;
    private String grantedAuthority;

    @Override
    public String get() {
        return grantedAuthority;
    }
}

package quizzer.backend.spi.database.auth.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;
import quizzer.backend.auth.model.GrantedAuthority;
import quizzer.backend.auth.model.User;

import java.util.List;
import java.util.Set;

@Data
@Table(name = "users", schema = "auth")
public class StoredUser implements User {
    @Id
    private Long id;
    private String username;
    private String password;
    @MappedCollection(idColumn = "user_id")
    private Set<StoredGrantedAuthority> grantedAuthorities;

    @Override
    public List<GrantedAuthority> getGrantedAuthorities() {
        return grantedAuthorities.stream()
                .map(a -> (GrantedAuthority) a)
                .toList();
    }
}

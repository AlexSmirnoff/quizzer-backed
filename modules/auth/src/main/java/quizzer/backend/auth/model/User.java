package quizzer.backend.auth.model;

import java.util.Collection;

public interface User {
    String getUsername();
    String getPassword();
    Collection<GrantedAuthority> getGrantedAuthorities();
}

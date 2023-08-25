package quizzer.backend.api.http.auth.userdetails.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import quizzer.backend.auth.model.GrantedAuthority;
import quizzer.backend.auth.model.User;

import java.util.Collection;

@Getter
@RequiredArgsConstructor
public class UserAdapter implements User {
    private final UserDetails userDetails;

    @Override
    public String getUsername() {
        return userDetails.getUsername();
    }

    @Override
    public String getPassword() {
        return userDetails.getPassword();
    }

    @Override
    public Collection<GrantedAuthority> getGrantedAuthorities() {
        return userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthorityAdapter::new)
                .map(ga -> (GrantedAuthority) ga)
                .toList();
    }

    @RequiredArgsConstructor
    private static class GrantedAuthorityAdapter implements GrantedAuthority {
        private final org.springframework.security.core.GrantedAuthority grantedAuthority;

        @Override
        public String get() {
            return grantedAuthority.getAuthority();
        }
    }
}

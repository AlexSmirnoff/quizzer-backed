package quizzer.backend.api.http.auth.userdetails.model;

import lombok.Getter;
import org.springframework.security.core.userdetails.UserDetails;
import quizzer.backend.auth.model.GrantedAuthority;
import quizzer.backend.auth.model.User;

import java.util.Collection;

@Getter
public class UserDetailsAdapter implements UserDetails {
    private final String username;
    private final String password;
    private final Collection<? extends org.springframework.security.core.GrantedAuthority> authorities;

    public UserDetailsAdapter(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.authorities = user.getGrantedAuthorities()
                .stream()
                .map(GrantedAuthorityAdapter::new)
                .toList();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Getter
    private static class GrantedAuthorityAdapter implements org.springframework.security.core.GrantedAuthority {
        private final String authority;

        public GrantedAuthorityAdapter(GrantedAuthority grantedAuthority) {
            this.authority = grantedAuthority.get();
        }
    }
}

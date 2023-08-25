package quizzer.backend.api.http.auth.userdetails.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import quizzer.backend.api.http.auth.userdetails.model.UserAdapter;
import quizzer.backend.api.http.auth.userdetails.model.UserDetailsAdapter;
import quizzer.backend.auth.api.UserStorageApi;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StoredUserDetailsPasswordService implements UserDetailsPasswordService {
    private final UserStorageApi userStorageApi;

    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        return Optional.of(user)
                .map(UserAdapter::new)
                .map(u -> userStorageApi.updatePassword(u, newPassword))
                .map(UserDetailsAdapter::new)
                .orElseThrow(() -> new UsernameNotFoundException(user.getUsername()));
    }
}

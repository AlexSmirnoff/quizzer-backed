package quizzer.backend.api.http.auth.userdetails.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import quizzer.backend.api.http.auth.userdetails.model.UserDetailsAdapter;
import quizzer.backend.auth.api.UserStorageApi;

@Service
@RequiredArgsConstructor
public class StoredUserDetailsService implements UserDetailsService {
    private final UserStorageApi userStorageApi;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userStorageApi.getUser(username)
                .map(UserDetailsAdapter::new)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}

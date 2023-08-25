package quizzer.backend.spi.database.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import quizzer.backend.auth.api.UserStorageApi;
import quizzer.backend.auth.model.User;
import quizzer.backend.spi.database.auth.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserStorageImpl implements UserStorageApi {
    private final UserRepository userRepository;

    @Override
    public Optional<User> getUser(String username) {
        return userRepository.getUserByUsername(username)
                .map(u -> u);
    }

    @Override
    public User updatePassword(User user, String newPassword) {
        var existingUser = userRepository.getUserByUsername(user.getUsername())
                .orElseThrow();
        existingUser.setPassword(newPassword);
        return userRepository.save(existingUser);
    }
}

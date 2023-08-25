package quizzer.backend.auth.api;

import quizzer.backend.auth.model.User;

import java.util.Optional;

public interface UserStorageApi {
    Optional<User> getUser(String username);
    User updatePassword(User user, String newPassword);
}

package quizzer.backend.spi.database.auth.repository;

import org.springframework.data.repository.CrudRepository;
import quizzer.backend.spi.database.auth.model.StoredUser;

import java.util.Optional;

public interface UserRepository extends CrudRepository<StoredUser, Long> {
    Optional<StoredUser> getUserByUsername(String username);
}

package patient.login.signup;

import java.util.Optional;

import patient.login.domain.User;
import patient.login.domain.UserIdentifier;
import patient.login.domain.VerificationCode;

public interface SignUpRepository {
    Optional<User> findUser(UserIdentifier userIdentifier);

    Optional<VerificationCode> findVerificationCode(UserIdentifier userIdentifier);

    Optional<User> createUser(User user);
}

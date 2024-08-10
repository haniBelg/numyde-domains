package patient.login.signin;

import java.util.Optional;

import patient.login.domain.Account;
import patient.login.domain.UserIdentifier;
import patient.login.domain.VerificationCode;

public interface SignInRepository {
    Optional<Account> findAccount(UserIdentifier userIdentifier);

    Optional<VerificationCode> findVerificationCode(UserIdentifier userIdentifier);
}

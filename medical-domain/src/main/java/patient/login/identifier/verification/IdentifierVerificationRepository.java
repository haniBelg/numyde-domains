package patient.login.identifier.verification;

import java.util.Optional;

import patient.login.domain.Account;
import patient.login.domain.UserIdentifier;

public interface IdentifierVerificationRepository {
    Optional<Account> findAccount(UserIdentifier identifier);
}

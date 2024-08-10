package patient.login.identifier.verification;

import java.time.Duration;
import java.util.Optional;

import patient.login.domain.UserIdentifier;
import patient.login.domain.Verification;

public interface VerificationCodeSender {
    Optional<Verification> sendVerificationCode(UserIdentifier userIdentifier,Duration preemptionDuration);
}

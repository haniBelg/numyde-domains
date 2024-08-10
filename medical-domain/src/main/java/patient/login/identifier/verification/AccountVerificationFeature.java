package patient.login.identifier.verification;

import java.time.Duration;
import java.util.Optional;

import patient.login.domain.AccountVerification;
import patient.login.domain.UserIdentifier;

public class AccountVerificationFeature {
    private final VerificationCodeSender verificationCodeSender;
    private final AccountVerificationRepository accountVerificationRepository;

    public AccountVerificationFeature(VerificationCodeSender verificationCodeSender,
            AccountVerificationRepository accountVerificationRepository) {
        this.verificationCodeSender = verificationCodeSender;
        this.accountVerificationRepository = accountVerificationRepository;
    }

    public Optional<AccountVerification> verifyAccount(UserIdentifier identifier) {
        return verificationCodeSender
                .sendVerificationCode(identifier, Duration.ofMinutes(15))
                .flatMap(verification -> Optional.ofNullable(accountVerificationRepository.findAccount(identifier)
                        .map(account -> new AccountVerification(account, verification))
                        .orElse(new AccountVerification(null, verification))));
    }
}

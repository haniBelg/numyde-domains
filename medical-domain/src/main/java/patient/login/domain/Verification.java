package patient.login.domain;

import java.time.LocalDateTime;

public record Verification(
        UserIdentifier userIdentifier,
        VerificationCode code,
        LocalDateTime preemptionsDate) {
}

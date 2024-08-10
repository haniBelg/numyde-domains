package patient.login.signin;

import java.util.Optional;

import patient.login.domain.Account;
import patient.login.domain.UserIdentifier;
import patient.login.domain.VerificationCode;

public class SignInFeature {

        private final SignInRepository signInRepository;

        public SignInFeature(SignInRepository signInRepository) {
                this.signInRepository = signInRepository;
        }

        public Optional<Account> signin(UserIdentifier userIdentifier, VerificationCode givenCode) {
                return signInRepository
                                .findVerificationCode(userIdentifier)
                                .filter(verificationCode -> verificationCode.equals(givenCode))
                                .flatMap(verificationCode -> signInRepository.findAccount(userIdentifier));
        }

}

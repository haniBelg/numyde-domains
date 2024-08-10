package patient.login.signup;

import java.util.Optional;

import patient.login.domain.User;
import patient.login.domain.VerificationCode;

public class SignUpFeature {
        private final SignUpRepository signUpRepository;

        public SignUpFeature(SignUpRepository signUpRepository) {
                this.signUpRepository = signUpRepository;
        }

        public Optional<User> create(User user, VerificationCode givenCode) {
                Optional<User> maybeExistingUser = signUpRepository.findUser(user.identifier());
                if (maybeExistingUser.isPresent()) {
                        return Optional.empty();
                }
                boolean verificationCodeIsValid = signUpRepository
                                .findVerificationCode(user.identifier())
                                .map(verificationCode -> verificationCode.equals(givenCode))
                                .orElse(false);

                if (verificationCodeIsValid) {
                        return signUpRepository.createUser(user);
                }
                return Optional.empty();
        }

}

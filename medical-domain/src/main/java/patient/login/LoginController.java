package patient.login;

import java.util.Optional;

import api.HttpAction;
import api.Input;
import api.Method;
import api.Response;
import patient.login.domain.Account;
import patient.login.domain.UserIdentifier;
import patient.login.identifier.verification.AccountVerificationFeature;
import patient.login.signin.SignInFeature;

public class LoginController {

    SignInFeature signInFeature;
    AccountVerificationFeature accountVerificationFeature;

    public Response<Void> index() {
        HttpAction action = new HttpAction("sendVerificationCode",
                Method.GET, "/account/:id",
                Input.builder()
                        // .pathParam(":id")
                        // .setting("regex", "[a-z]")
                        .build(),
                true);
        return Response.of(action);
    }

    public Response<Account> verify(String id) {
        return accountVerificationFeature
                .verifyAccount(UserIdentifier.of(id))
                .flatMap(accountVerification -> Optional.ofNullable(accountVerification.account()))
                .map(account -> Response.of(account, new HttpAction("verifyCode",
                        Method.GET, "/account/" + id,
                        Input.builder()
                                // .pathParam(":code")
                                // .setting("regex", "[0-9]{4}")
                                .build(),
                        true)))
                .orElse(Response.of(new HttpAction("verify",
                        Method.PUT, "/account/" + id,
                        Input.builder()
                                .body(":id")
                                .setting("regex", "[a-z]")
                                .build(),
                        true)));
    }

}

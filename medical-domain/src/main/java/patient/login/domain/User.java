package patient.login.domain;

import java.time.LocalDate;

public record User(UserIdentifier identifier,
        String natalLastName,
        String lastName,
        String firstName,
        Gender gender,
        LocalDate birthDate,
        String email,
        String phoneNumber) {

}

package patient.login.domain;

public record UserIdentifier(String value, IdentifierType identifierType) {
    public UserIdentifier {
        identifierType = verifyString(value);
    }

    // Regular expression for validating email
    private static final String EMAIL_REGEX = "^[\\w-\\.]+@[\\w-]+(\\.[\\w-]+)+$";

    // Regular expression for validating phone number (simple example, adjust for
    // your needs)
    private static final String PHONE_REGEX = "^\\+?[0-9. ()-]{7,}$";

    private static IdentifierType verifyString(String input) {
        if (input == null || input.isEmpty()) {
            return null;
        }

        if (input.matches(EMAIL_REGEX)) {
            return IdentifierType.EMAIL;
        } else if (input.matches(PHONE_REGEX)) {
            return IdentifierType.PHONE;
        } else {
            return null;
        }
    }

    public static UserIdentifier of(String value) {
        return new UserIdentifier(value, null);
    }
}

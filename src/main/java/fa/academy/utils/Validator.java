package fa.academy.utils;

import java.time.LocalDate;
import org.apache.commons.validator.routines.DateValidator;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.IntegerValidator;

public class Validator {

    private static final String CDD_ID_REGEX = "^(CDD)\\d{3}";

    public static int validateNumber(String input) throws Exception {
        return IntegerValidator.getInstance().validate(input);
    }

    public static boolean validateCandidateId(String input) {
        return input.matches(CDD_ID_REGEX);
    }

    public static boolean validateCandidateName(String input) {
        return false;
    }

    public static boolean validateCandidateBirthday(String dob) {
        if (
            !(
                DateValidator.getInstance().isValid(dob, "yyyy-MM-dd") ||
                DateValidator.getInstance().isValid(dob, "dd/MM/yyyy")
            )
        ) return false;
        return true;
    }

    public static boolean validateCandidatePhone(String phone) {
        return false;
    }

    public static boolean validateCandidateEmail(String email) {
        return EmailValidator.getInstance().isValid(email);
    }
}

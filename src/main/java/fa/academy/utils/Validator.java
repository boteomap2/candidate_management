package fa.academy.utils;

import fa.academy.custom.DateException;
import fa.academy.custom.EmailException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.apache.commons.validator.routines.DateValidator;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.IntegerValidator;

public class Validator {

    private static final String CDD_ID_REGEX = "^(CDD)\\d{3}";
    private static final String CERF_ID_REGEX = "^(CERF)\\d{3}";

    public static int validateNumber(String input) throws Exception {
        return IntegerValidator.getInstance().validate(input);
    }

    public static String validateCandidateId(String input) throws Exception {
        if (!input.matches(CDD_ID_REGEX)) throw new Exception(
            "Invalid Id format"
        );
        return input;
    }

    public static String validateCerfId(String input) throws Exception {
        if (!input.matches(CERF_ID_REGEX)) throw new Exception(
            "Invalid Id format"
        );
        return input;
    }

    public static String validateCandidateName(String input) throws Exception {
        return input;
    }

    public static LocalDate validateDate(String dob) throws DateException {
        LocalDate localDate;

        if (DateValidator.getInstance().isValid(dob, "yyyy-MM-dd")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                "yyyy-MM-dd"
            );
            localDate = LocalDate.parse(dob, formatter);
        } else if (DateValidator.getInstance().isValid(dob, "dd/MM/yyyy")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                "dd/MM/yyyy"
            );
            localDate = LocalDate.parse(dob, formatter);
        } else throw new DateException("Invalid date format.");

        if (localDate.isBefore(LocalDate.of(1900, 1, 1))) {
            throw new DateException("Date must be from 01/01/1900 toward.");
        }
        return localDate;
    }

    public static String validateCandidatePhone(String phone) throws Exception {
        return phone;
    }

    public static String validateCandidateEmail(String email)
        throws EmailException {
        if (
            !EmailValidator.getInstance().isValid(email)
        ) throw new EmailException("Invalid Email.");
        return email;
    }
}

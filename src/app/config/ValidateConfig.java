package app.config;

import java.util.regex.Pattern;

public final class ValidateConfig {
    /*Parameters*/
    private static final String MIN_LENGTH = "1";
    private static final String MAX_LENGTH = "10";
    private static final boolean SPECIAL_CHAR_NEEDED = true;
    private static final String SPECIAL_CHAR = SPECIAL_CHAR_NEEDED ? "(?=.*[@#$%^&+=])" : "";
    /*Modules*/
    private static final String ONE_DIGIT = "(?=.*[0-9])";// or "(?=.*[\\d])" = requires at least
    private static final String LOWER_CASE = "(?=.*[a-z])";// or "(?=.*[\\w])" = requires at least
    private static final String UPPER_CASE = "(?=.*[A-Z])";// or "(?=.*[\\w])" = requires at least
    private static final String NO_SPACE = "(?=\\S+$)";// or with space "(?=.*[\\s])" = requires at least
    private static final String MIN_MAX_CHAR = ".{" + MIN_LENGTH + "," + MAX_LENGTH + "}";
    /*Pattern Regex*/
    private static final String PASSWORD_REGEX = ONE_DIGIT + LOWER_CASE + UPPER_CASE + SPECIAL_CHAR + NO_SPACE + MIN_MAX_CHAR;
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$";
    private static final String ACCOUNT_REGEX = "^[.\\S]{1,30}$";
    private static final String NAME_REGEX = ".{1,40}";
    private static final String POST_COMMENT_REGEX = ".{1,200}";


    public static boolean validateName(String name) {
        return Pattern.matches(NAME_REGEX, name);
    }

    public static boolean validateUserName(String account) {
        return Pattern.matches(ACCOUNT_REGEX, account);
    }

    public static boolean validateEmail(String email) {
        return Pattern.matches(EMAIL_REGEX, email);
    }

    public static boolean validatePassword(String password) {
        return Pattern.matches(PASSWORD_REGEX, password);
    }

    public static boolean validatePostComment(String comment) {
        return Pattern.matches(POST_COMMENT_REGEX, comment);
    }
}
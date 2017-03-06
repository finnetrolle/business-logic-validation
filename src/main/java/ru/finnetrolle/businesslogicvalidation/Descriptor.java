package ru.finnetrolle.businesslogicvalidation;

/**
 * Business Logic Validation
 * Created by finnetrolle on 07.03.2017.
 */

/**
 * This is a descriptor of rule.
 */
public class Descriptor {

    /**
     * Rule code
     */
    private final int code;
    /**
     * Rule message (if fails)
     */
    private final String message;

    public static Descriptor rule(int code, String message) {
        return new Descriptor(code, message);
    }

    public Descriptor(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

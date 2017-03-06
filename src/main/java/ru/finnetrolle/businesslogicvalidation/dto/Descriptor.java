package ru.finnetrolle.businesslogicvalidation.dto;

/**
 * Created by finnetrolle on 06.03.2017.
 */
public class Descriptor {

    private final int code;
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

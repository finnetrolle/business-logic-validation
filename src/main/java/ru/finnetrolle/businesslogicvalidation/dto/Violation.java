package ru.finnetrolle.businesslogicvalidation.dto;

import java.util.Objects;

/**
 * Business Logic Validation
 * Created by finnetrolle on 25.11.2015.
 */

/**
 * Class holds violation info
 */
public class Violation {

    private final String message;
    private final Integer code;
    private final ViolationLevel violationLevel;

    private Violation(String message, Integer code, ViolationLevel violationLevel) {
        this.message = message;
        this.code = code;
        this.violationLevel = violationLevel;
    }

    /**
     * fabric method to construct object
     * @param message violation message
     * @param code violation code
     * @param violationLevel violation level
     * @return ready object
     */
    public static Violation create(String message, Integer code, ViolationLevel violationLevel) {
        return new Violation(message, code, violationLevel);
    }

    /**
     * @return violation message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @return violation code
     */
    public Integer getCode() {
        return code;
    }

    /**
     * @return violation level
     */
    public ViolationLevel getViolationLevel() {
        return violationLevel;
    }

    @Override
    public String toString() {
        return "Violation{" +
                "message='" + message + '\'' +
                ", code=" + code +
                ", violationLevel=" + violationLevel +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Violation)) return false;
        Violation violation = (Violation) o;
        return
                Objects.equals(message, violation.message) &&
                Objects.equals(code, violation.code) &&
                violationLevel == violation.violationLevel;
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, code, violationLevel);
    }
}

package ru.finnetrolle.businesslogicvalidation;

/**
 * Business Logic Validation
 * Created by finnetrolle on 07.03.2017.
 */
public class SimpleRule<V> extends Rule<V> {

    private final Validating<V> validatingObject;
    private final String message;
    private final ViolationLevel violationLevel;
    private final int code;

    private SimpleRule(Validating<V> validatingObject, String message, ViolationLevel violationLevel, int code) {
        this.validatingObject = validatingObject;
        this.message = message;
        this.violationLevel = violationLevel;
        this.code = code;
    }

    public static <IN> Rule<IN> notice(Descriptor descriptor, Validating<IN> validatingObject) {
        return new SimpleRule<>(validatingObject, descriptor.getMessage(), ViolationLevel.NOTICE, descriptor.getCode());
    }

    public static <IN> Rule<IN> permissible(Descriptor descriptor, Validating<IN> validatingObject) {
        return new SimpleRule<>(validatingObject, descriptor.getMessage(), ViolationLevel.PERMISSIBLE, descriptor.getCode());
    }

    public static <IN> Rule<IN> error(Descriptor descriptor, Validating<IN> validatingObject) {
        return new SimpleRule<>(validatingObject, descriptor.getMessage(), ViolationLevel.ERROR, descriptor.getCode());
    }

    public static <IN> Rule<IN> critical(Descriptor descriptor, Validating<IN> validatingObject) {
        return new SimpleRule<>(validatingObject, descriptor.getMessage(), ViolationLevel.CRITICAL, descriptor.getCode());
    }

    @Override
    protected boolean validate(V value) {
        return validatingObject.validateObject(value);
    }

    @Override
    protected String getMessage(V value) {
        return message;
    }

    @Override
    protected ViolationLevel getViolationLevel() {
        return violationLevel;
    }

    @Override
    protected Integer getCode() {
        return code;
    }
}

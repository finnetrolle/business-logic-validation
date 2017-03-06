package ru.finnetrolle.businesslogicvalidation;

import java.util.function.Predicate;

/**
 * Business Logic Validation
 * Created by finnetrolle on 07.03.2017.
 */
public final class LambdaRule<IN> extends Rule<IN> {

    public static <IN> Rule<IN> notice(Descriptor descriptor, Predicate<IN> predicate) {
        return new LambdaRule<>(predicate, descriptor.getMessage(), ViolationLevel.NOTICE, descriptor.getCode());
    }

    public static <IN> Rule<IN> permissible(Descriptor descriptor, Predicate<IN> predicate) {
        return new LambdaRule<>(predicate, descriptor.getMessage(), ViolationLevel.PERMISSIBLE, descriptor.getCode());
    }

    public static <IN> Rule<IN> error(Descriptor descriptor, Predicate<IN> predicate) {
        return new LambdaRule<>(predicate, descriptor.getMessage(), ViolationLevel.ERROR, descriptor.getCode());
    }

    public static <IN> Rule<IN> critical(Descriptor descriptor, Predicate<IN> predicate) {
        return new LambdaRule<>(predicate, descriptor.getMessage(), ViolationLevel.CRITICAL, descriptor.getCode());
    }

    private final Predicate<IN> predicate;
    private final String message;
    private final ViolationLevel violationLevel;
    private final int code;

    LambdaRule(Predicate<IN> predicate, String message, ViolationLevel violationLevel, int code) {
        this.predicate = predicate;
        this.message = message;
        this.violationLevel = violationLevel;
        this.code = code;
    }

    @Override
    protected boolean validate(IN value) {
        return predicate.test(value);
    }

    @Override
    protected String getMessage(IN value) {
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

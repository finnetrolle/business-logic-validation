package ru.finnetrolle.businesslogicvalidation;

/**
 * Business Logic Validation
 * Created by finnetrolle on 07.03.2017.
 */
public abstract class ExtendableRule<IN> extends Rule<IN> implements Validating<IN> {

    private final Rule<IN> proxy;

    public ExtendableRule(RuleBuilder builder) {
         proxy = builder.requires(this);
    }

    @Override
    public boolean validate(IN object) {
        return proxy.validate(object);
    }

    @Override
    protected String getMessage(IN value) {
        return proxy.getMessage(value);
    }

    @Override
    protected ViolationLevel getViolationLevel() {
        return proxy.getViolationLevel();
    }

    @Override
    protected Integer getCode() {
        return proxy.getCode();
    }

}

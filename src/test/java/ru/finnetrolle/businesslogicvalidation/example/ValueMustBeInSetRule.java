package ru.finnetrolle.businesslogicvalidation.example;

import ru.finnetrolle.businesslogicvalidation.specific.PermissibleRule;

import java.util.Set;

/**
 * Business Logic Validation
 * Created by finnetrolle on 25.11.2015.
 */
public class ValueMustBeInSetRule extends PermissibleRule<String, Set<String>> {

    public ValueMustBeInSetRule(Set<String> data) {
        super(data);
    }

    public static ValueMustBeInSetRule create(Set<String> data) {
        return new ValueMustBeInSetRule(data);
    }

    @Override
    protected boolean validate(String value) {
        return data.contains(value);
    }

    @Override
    protected String getName() {
        return "Set checker";
    }

    @Override
    protected String getMessage(String value) {
        return "value " + value + " must be in set";
    }

    @Override
    protected Integer getCode() {
        return 10;
    }


}
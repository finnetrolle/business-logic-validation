package ru.finnetrolle.businesslogicvalidation.example;

import ru.finnetrolle.businesslogicvalidation.dto.ViolationLevel;
import ru.finnetrolle.businesslogicvalidation.specific.NoDataRule;

/**
 * Business Logic Validation
 * Created by finnetrolle on 25.11.2015.
 */
public class StringMustBeBigRule extends NoDataRule<String> {
    @Override
    protected boolean validate(String value) {
        return value.length() > 3;
    }

    private StringMustBeBigRule() {
    }

    public static StringMustBeBigRule create() {
        return new StringMustBeBigRule();
    }

    @Override
    protected String getName() {
        return "String must contains more than 3 chars (NPE WARNING)";
    }

    @Override
    protected String getMessage(String value) {
        return "String " + value + " consist of less than 3 chars";
    }

    @Override
    protected ViolationLevel getViolationLevel() {
        return ViolationLevel.PERMISSIBLE;
    }

    @Override
    protected Integer getCode() {
        return 20;
    }
}

package ru.finnetrolle.businesslogicvalidation;

import org.junit.Test;

public class StopperTest {

    @Test
    public void testStopperInterface() {
        ValidationResult result = ValidationResult
                .validate("Jameson").by(RuleGroup.common().validating(
                        SimpleRule.error("length > 1", s -> s.length() > 2),
                        new IAlwaysStop(),
                        SimpleRule.critical("length > 100", s -> s.length() > 100)));
        assert result.getViolationsList().size() == 1;
        assert result.getViolationLevel() == ViolationLevel.ERROR;

        result = ValidationResult
                .validate("Jameson").by(RuleGroup.common().validating(
                        SimpleRule.error("length > 1", s -> s.length() > 2),
                        SimpleRule.critical("length > 100", s -> s.length() > 100)));

        assert result.getViolationsList().size() == 1;
        assert result.getViolationLevel() == ViolationLevel.CRITICAL;
    }

    public static class IAlwaysStop extends Rule<String> implements Stopper {

        public IAlwaysStop() {
            super("You shall not pass");
        }

        @Override
        protected boolean validate(String s) {
            return false;
        }
    }

}

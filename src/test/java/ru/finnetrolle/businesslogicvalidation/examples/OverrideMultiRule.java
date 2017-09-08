package ru.finnetrolle.businesslogicvalidation.examples;

import org.junit.Test;
import ru.finnetrolle.businesslogicvalidation.*;

import java.util.ArrayList;
import java.util.List;

public class OverrideMultiRule {

    @Test
    public void overrideMultiRuleUsage() {
        ValidationResult result = ValidationResult
                .validate("jack").by(new OverridedMultiRule());

        assert result.getViolationsList().size() == 2;
    }

    public static class OverridedMultiRule extends MultiRule<String> {

        public OverridedMultiRule() {
            super(delegate);
        }

        private static MultiValidating<String> delegate = object -> {
            List<Violation> violationList = new ArrayList<>();
            violationList.add(Violation.build("First violation", ViolationLevel.ERROR));
            violationList.add(Violation.build("Second violation", ViolationLevel.PERMISSIBLE));
            return violationList;
        };

    }
}

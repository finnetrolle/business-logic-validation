package ru.finnetrolle.businesslogicvalidation.examples;

import org.junit.Test;
import ru.finnetrolle.businesslogicvalidation.*;

import java.util.ArrayList;
import java.util.List;

public class MultiRuleExample {

    @Test
    public void applyMultipleRule() {
        int[] array = {1, 2, 3, 4, 6, 5};

        ValidationResult result = ValidationResult
                .validate(array)
                .by(MultiRule.create(a -> {
                    List<Violation> violations = new ArrayList<>();
                    for (int i = 0; i < a.length - 1; ++i) {
                        if (a[i] + 1 != a[i + 1]) {
                            violations.add(Violation.build("Bad order in element " + (i + 1), ViolationLevel.ERROR));
                        }
                    }
                    return violations;
                }));

        assert !result.passed();
        assert result.getViolationsList().get(0).getMessage().equals("Bad order in element 4");
        assert result.getViolationsList().size() == 2;
    }

}

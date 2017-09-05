package ru.finnetrolle.businesslogicvalidation.extendedRules;

import ru.finnetrolle.businesslogicvalidation.Rule;
import ru.finnetrolle.businesslogicvalidation.Violation;
import ru.finnetrolle.businesslogicvalidation.ViolationLevel;

import java.util.ArrayList;
import java.util.List;

public class MultipleViolationsRule extends Rule<String> {

    public MultipleViolationsRule() {
        super("This rule can produce multiple violations");
    }

    /*
    There is no reason to override this method when we override next method
     */
    @Override
    protected boolean validate(String s) {
        return false;
    }

    /*
    When this method is overridden - we can validate here and return multiple violations
     */
    @Override
    protected List<Violation> check(String s) {
        List<Violation> violations = new ArrayList<>();
        if (s.length() > 10) {
            violations.add(Violation.error("Length is larger than 10"));
        }
        if (s.contains("hello")) {
            violations.add(Violation.build("String contains HELLO", ViolationLevel.PERMISSIBLE));
        }
        return violations;
    }
}

package ru.finnetrolle.businesslogicvalidation;

import java.util.List;

/**
 * Business Logic Validation
 * Created by finnetrolle on 25.11.2015.
 */

/**
 * Engine class
 */
public final class ValidationEngine {

    private ValidationResult result = new ValidationResult();

    public <ELEMENT> ValidationEngine validateGroup(RuleGroup<ELEMENT> ruleGroup, List<ELEMENT> elements) {
        result.addViolations(ruleGroup.validate(elements));
        return this;
    }

    public ValidationResult getResult() {
        return result;
    }

}

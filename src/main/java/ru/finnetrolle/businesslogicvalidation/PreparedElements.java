package ru.finnetrolle.businesslogicvalidation;

import java.util.List;

public class PreparedElements<T> {

    private final List<T> elements;
    private final ValidationResult result;

    PreparedElements(List<T> elements) {
        this.elements = elements;
        this.result = null;
    }

    PreparedElements(List<T> elements, ValidationResult result) {
        this.elements = elements;
        this.result = result;
    }

    public ValidationResult by(RuleGroup<T> ruleGroup) {
        if (result == null) {
            return new ValidationResult().addViolations(ruleGroup.validate(elements));
        } else {
            return result.addViolations(ruleGroup.validate(elements));
        }
    }

    public ValidationResult by(Rule<T> rule) {
        if (result == null) {
            return new ValidationResult().addViolations(RuleGroup.common().validating(rule).validate(elements));
        } else {
            return result.addViolations(RuleGroup.common().validating(rule).validate(elements));
        }
    }

    public ValidationResult with(String description, Validating<T> validating) {
        return by(SimpleRule.error(description, validating));
    }

    public ValidationResult with(Validating<T> validating, MessageGenerator<T> messageGenerator) {
        return by(SimpleRule.error(validating, messageGenerator));
    }
}

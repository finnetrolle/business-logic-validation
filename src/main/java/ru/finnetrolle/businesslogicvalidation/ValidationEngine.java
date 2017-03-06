package ru.finnetrolle.businesslogicvalidation;

import ru.finnetrolle.businesslogicvalidation.dto.Violation;
import ru.finnetrolle.businesslogicvalidation.dto.ViolationLevel;
import ru.finnetrolle.businesslogicvalidation.dto.ValidationResult;

import java.util.ArrayList;

import java.util.Collection;
import java.util.List;

/**
 * Business Logic Validation
 * Created by finnetrolle on 25.11.2015.
 */

/**
 * Engine class
 * @param <V> type of validating value
 */
public class ValidationEngine<V> {

    private List<Rule<V>> rules = new ArrayList<>();

    private ValidationEngine() {
    }

    /**
     * Fabric method to create engine
     * @param <V> type of value
     * @return ready object
     */
    public static <V> ValidationEngine<V> create() {
        return new ValidationEngine<>();
    }

    /**
     * Fabric method to create engine
     * @param rules rules to validate
     * @param <V> type of value
     * @return ready object
     */
    @SafeVarargs
    public static <V> ValidationEngine<V> create(Rule<V>... rules) {
        ValidationEngine<V> engine = new ValidationEngine<>();
        for (Rule<V> rule : rules) {
            engine.addRule(rule);
        }
        return engine;
    }

    /**
     * Use this method to add your rules into validation chain
     * @param rule rule
     * @return engine (to make event chain)
     */
    public ValidationEngine<V> addRule(Rule<V> rule) {
        rules.add(rule);
        return this;
    }

    private List<Violation> getViolations(V value) {
        List<Violation> violations = new ArrayList<>();
        for (Rule<V> rule : rules) {
            Violation violation = rule.check(value);
            if (violation != null) {
                violations.add(violation);
                if (violation.getViolationLevel() == ViolationLevel.CRITICAL) {
                    break;
                }
            }
        }
        return violations;
    }

    /**
     * Validation method
     * @param value value to validate
     * @return validation result
     */
    public ValidationResult validate(V value) {
        return ValidationResult.create(getViolations(value));
    }

    /**
     * Validation method
     * @param values collection of value to validate
     * @return validation result
     */
    public ValidationResult validate(Collection<V> values) {
        List<Violation> violations = new ArrayList<>();
        for (V value : values) {
            violations.addAll(getViolations(value));
        }
        return ValidationResult.create(violations);
    }
}

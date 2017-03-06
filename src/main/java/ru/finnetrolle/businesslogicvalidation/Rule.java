package ru.finnetrolle.businesslogicvalidation;

import ru.finnetrolle.businesslogicvalidation.dto.Descriptor;
import ru.finnetrolle.businesslogicvalidation.dto.Violation;
import ru.finnetrolle.businesslogicvalidation.dto.ViolationLevel;

import java.util.function.Predicate;

/**
 * Business Logic Validation
 * Created by finnetrolle on 25.11.2015.
 */

/**
 * Parent of all rules
 * @param <V> type of value
 */
public abstract class Rule<V> {

    public static RuleBuilder notice(Descriptor descriptor) {
        return RuleBuilder.getInstance(descriptor, ViolationLevel.NOTICE);
    }

    public static RuleBuilder permissible(Descriptor descriptor) {
        return RuleBuilder.getInstance(descriptor, ViolationLevel.PERMISSIBLE);
    }

    public static RuleBuilder error(Descriptor descriptor) {
        return RuleBuilder.getInstance(descriptor, ViolationLevel.ERROR);
    }

    public static RuleBuilder critical(Descriptor descriptor) {
        return RuleBuilder.getInstance(descriptor, ViolationLevel.CRITICAL);
    }

    /**
     * Main validate method. Can not be overriden
     * @param value value to validate
     * @return null for success validation or violation otherwise
     */
    final Violation check(V value) {
        if (validate(value)) {
            return null;
        } else {
            return Violation.create(getMessage(value), getCode(), getViolationLevel());
        }
    }

    /**
     * Override this method to implement your validation process
     * @param value value to validate
     * @return boolean result of validation
     */
    protected abstract boolean validate(V value);

    /**
     * Override this method to define fail validation message
     * You can use value to make message more clear
     * @param value value
     * @return fail validation description
     */
    protected abstract String getMessage(V value);

    /**
     * Override this method to define violation level of your derived rule
     * @return validation level
     */
    protected abstract ViolationLevel getViolationLevel();

    /**
     * Override this method to define code of rule
     * @return code of rule
     */
    protected abstract Integer getCode();




}

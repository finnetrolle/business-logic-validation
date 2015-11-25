package ru.finnetrolle.businesslogicvalidation;

import ru.finnetrolle.businesslogicvalidation.dto.Violation;
import ru.finnetrolle.businesslogicvalidation.dto.ViolationLevel;

/**
 * Business Logic Validation
 * Created by finnetrolle on 25.11.2015.
 */

/**
 * Parent of all rules
 * @param <V> type of value
 * @param <D> type of data
 */
public abstract class Rule<V, D> {

    protected final D data;

    /**
     * Make constructor matching super in derived rules
     * @param data data to validate values with
     */
    public Rule(D data) {
        this.data = data;
    }

    /**
     * Main validate method. Can not be overriden
     * @param value value to validate
     * @return null for success validation or violation otherwise
     */
    public final Violation check(V value) {
        if (validate(value)) {
            return null;
        } else {
            return Violation.create(getName(), getMessage(value), getCode(), getViolationLevel());
        }
    }

    /**
     * Override this method to implement your validation process
     * @param value value to validate
     * @return boolean result of validation
     */
    protected abstract boolean validate(V value);

    /**
     * Override this method to define name of your derived rule
     * @return name of the rule
     */
    protected abstract String getName();

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

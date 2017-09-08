package ru.finnetrolle.businesslogicvalidation;

/**
 * Created by finnetrolle on 06.03.2017.
 */
@FunctionalInterface
public interface Validating<IN> {

    /**
     * Validation rule
     * @param object - object to validate
     * @return true if validation passed
     */
    boolean validateObject(IN object);

}

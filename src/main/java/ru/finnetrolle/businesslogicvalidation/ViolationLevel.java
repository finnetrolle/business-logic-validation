package ru.finnetrolle.businesslogicvalidation;

/**
 * Business Logic Validation
 * Created by finnetrolle on 25.11.2015.
 */
public enum ViolationLevel {

    /**
     * This level is for info messages
     * Chain not affected
     */
    NOTICE,

    /**
     * This level is for permissible violations
     * Chain not affected
     */
    PERMISSIBLE,

    /**
     * This level is for not permissible violations
     * Chain not affected
     */
    ERROR,

    /**
     * This level is for not permissible violations
     * Stops propagation
     */
    CRITICAL
}

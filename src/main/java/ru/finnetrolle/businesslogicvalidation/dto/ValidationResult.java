package ru.finnetrolle.businesslogicvalidation.dto;

import ru.finnetrolle.businesslogicvalidation.ViolationLevel;

import java.util.List;
import java.util.Objects;

/**
 * Business Logic Validation
 * Created by finnetrolle on 25.11.2015.
 */

/**
 * Class holds result of validation
 */
public class ValidationResult {

    private final List<Violation> violations;
    private final ViolationLevel level;

    private ValidationResult(List<Violation> violations) {
        ViolationLevel level = ViolationLevel.NOTICE;
        for (Violation violation : violations) {
            if (violation.getViolationLevel().compareTo(level) > 0) {
                level = violation.getViolationLevel();
            }
        }
        this.violations = violations;
        this.level = level;
    }

    /**
     * Use this fabric method to create validation results
     * @param violations list of violations
     * @return ready object
     */
    public static ValidationResult create(List<Violation> violations) {
        return new ValidationResult(violations);
    }

    /**
     * @return list of violations
     */
    public List<Violation> getViolations() {
        return violations;
    }

    /**
     * @return level of the worst violation
     */
    public ViolationLevel getLevel() {
        return level;
    }

    /**
     * @return true if worst violation level is NOTICE or PERMISSIBLE, false otherwise
     */
    public boolean passed() {
        return level == ViolationLevel.NOTICE || level == ViolationLevel.PERMISSIBLE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ValidationResult)) return false;
        ValidationResult result = (ValidationResult) o;
        return Objects.equals(violations, result.violations) &&
                level == result.level;
    }

    @Override
    public int hashCode() {
        return Objects.hash(violations, level);
    }

    @Override
    public String toString() {
        return "ValidationResult {level = " + level +
                "\nviolations=" + violations +
                '}';
    }
}

package ru.finnetrolle.businesslogicvalidation;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Business Logic Validation
 * Created by finnetrolle on 25.11.2015.
 */

/**
 * Class holds result of validation
 */
public class ValidationResult {

    /**
     * violation storage
     */
    private final Map<String, List<Violation>> violationGroups = new HashMap<>();
    /**
     * Current violation level
     */
    private ViolationLevel violationLevel = ViolationLevel.NOTICE;

    /**
     * Start validation for one element
     * @param element
     * @param <T> type of element
     * @return builder chain
     */
    public static <T> PreparedElements<T> validate(T element) {
        return new PreparedElements<>(Collections.singletonList(element));
    }

    /**
     * Start validation for elements
     * @param elements
     * @param <T> type if elements
     * @return builder chain
     */
    public static <T> PreparedElements<T> validate(List<T> elements) {
        return new PreparedElements<>(elements);
    }

    /**
     * @return true if violation level is above ERROR
     */
    public boolean passed() {
        return violationLevel == ViolationLevel.NOTICE || violationLevel == ViolationLevel.PERMISSIBLE;
    }

    /**
     * @return violation level for all groups
     */
    public ViolationLevel getViolationLevel() {
        return violationLevel;
    }

    /**
     * Add custom violation to validation result
     * @param violation
     */
    public ValidationResult addViolation(Violation violation) {
        List<Violation> violations = violationGroups
                .computeIfAbsent(violation.getShardName(), k -> new ArrayList<>());
        violations.add(violation);
        if (violation.getViolationLevel().compareTo(violationLevel) > 0) {
            violationLevel = violation.getViolationLevel();
        }
        return this;
    }

    /**
     * Continue validation process with new group of elements
     * @return builder chain
     */
    public <T> PreparedElements<T> and(List<T> elements) {
        return new PreparedElements<>(elements, this);
    }

    /**
     * Continue validation process with new element
     * @param element
     * @param <T>
     * @return
     */
    public <T> PreparedElements<T> and(T element) {
        return new PreparedElements<>(Collections.singletonList(element), this);
    }

    public ValidationResult andNothingBy(Rule<Object> rule) {
        this.addViolations(RuleGroup.common().validating(rule).validate(Collections.singletonList(null)));
        return this;
    }

    /**
     * Add some custom violations
     */
    public ValidationResult addViolations(List<Violation> violations) {
        for (Violation violation : violations) {
            addViolation(violation);
        }
        return this;
    }

    /**
     * Get violations in one list
     * @return
     */
    public List<Violation> getViolationsList() {
        return violationGroups.values().stream().flatMap(Collection::stream).collect(Collectors.toList());
    }

    /**
     * Get grouped violations
     * @return
     */
    public Map<String, List<Violation>> getViolationMap() {
        return violationGroups;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ValidationResult that = (ValidationResult) o;
        return Objects.equals(violationGroups, that.violationGroups) &&
                violationLevel == that.violationLevel;
    }

    @Override
    public int hashCode() {
        return Objects.hash(violationGroups, violationLevel);
    }

    @Override
    public String toString() {
        return "ValidationResult{" +
                "violationGroups=" + violationGroups +
                ", violationLevel=" + violationLevel +
                '}';
    }
}

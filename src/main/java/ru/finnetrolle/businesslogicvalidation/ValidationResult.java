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

    private final Map<String, List<Violation>> violationGroups = new HashMap<>();
    private ViolationLevel violationLevel = ViolationLevel.NOTICE;

    /**
     * @return violation level for all groups
     */
    public ViolationLevel getViolationLevel() {
        return violationLevel;
    }

    public ValidationResult addViolation(Violation violation) {
        List<Violation> violations = violationGroups
                .computeIfAbsent(violation.getShardName(), k -> new ArrayList<>());
        violations.add(violation);
        ViolationLevel maxVio = violations.stream()
                .map(Violation::getViolationLevel)
                .max(Comparator.naturalOrder())
                .orElse(ViolationLevel.NOTICE);
        if (maxVio.compareTo(violationLevel) > 0) {
            violationLevel = maxVio;
        }
        return this;
    }

    public ValidationResult addViolations(List<Violation> violations) {
        for (Violation violation : violations) {
            addViolation(violation);
        }
        return this;
    }

    public <ELEMENT> ValidationResult applyRule(Rule<ELEMENT> rule, ELEMENT element) {
        addViolations(rule.check(element));
        return this;
    }

    public <ELEMENT> ValidationResult applyRule(Rule<ELEMENT> rule) {
        addViolations(rule.check(null));
        return this;
    }

    public <ELEMENT> ValidationResult applyRuleGroup(RuleGroup<ELEMENT> ruleGroup) {
        addViolations(ruleGroup.validate(Collections.singletonList(null)));
        return this;
    }

    public <ELEMENT> ValidationResult applyRuleGroup(RuleGroup<ELEMENT> ruleGroup, Collection<ELEMENT> elements) {
        addViolations(ruleGroup.validate(elements));
        return this;
    }

    public List<Violation> getViolationsList() {
        return violationGroups.values().stream().flatMap(Collection::stream).collect(Collectors.toList());
    }

    public Map<String, List<Violation>> getViolationMap() {
        return violationGroups;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ValidationResult that = (ValidationResult) o;
        return Objects.equals(violationGroups, that.violationGroups);
    }

    @Override
    public String toString() {
        return "ValidationResult{" +
                "violationGroups=" + violationGroups +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(violationGroups);
    }
}

package ru.finnetrolle.businesslogicvalidation;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ValidationV3Tutorial {

    @Test
    public void example() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<String> strings = Arrays.asList("abc", "aaa", "lambda");

        RuleGroup<Integer> oddity = RuleGroup.named("Oddity").validating(
                SimpleRule.create("Must be odd", a -> a % 2 == 0),
                SimpleRule.create("Must not be odd", a -> a % 2 == 1));

        RuleGroup<String> length = RuleGroup.named("String length").validating(
                SimpleRule.create("Must be lesser that 4 symbols", a -> a.length() < 4));

        ValidationResult result = new ValidationEngine()
                .validateGroup(oddity, numbers)
                .validateGroup(length, strings)
                .getResult();

        result.applyRule(SimpleRule.create("2 * 2 != 5", a -> 2 * 2 != 5));

        assert result.getViolationsList().size() == 11;
    }

    @Test
    public void simpleValidation() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        ValidationResult result = new ValidationEngine()
                .validateGroup(RuleGroup.common().validating(
                        SimpleRule.create("Must be odd", a -> a % 2 == 0)
                ), numbers)
                .getResult();

        assert result.getViolationsList().size() == 5;
    }

    @Test
    public void simpleRuleExtended() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        ValidationResult result = new ValidationResult();
        result.applyRuleGroup(RuleGroup.named("test").validating(
                SimpleRule.create(s -> s % 2 == 0, s -> "Element " + s.toString() + " is bad")
        ), numbers);

        assert result.getViolationsList().size() == 5;
        Set<Violation> violationSet = new HashSet<>(result.getViolationsList());
        assert violationSet.contains("Element 1 is bad");
        assert violationSet.contains("Element 3 is bad");
        assert violationSet.contains("Element 5 is bad");
        assert violationSet.contains("Element 7 is bad");
        assert violationSet.contains("Element 9 is bad");
    }

}

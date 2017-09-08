package ru.finnetrolle.businesslogicvalidation.examples;

import org.junit.Test;
import ru.finnetrolle.businesslogicvalidation.RuleGroup;
import ru.finnetrolle.businesslogicvalidation.SimpleRule;
import ru.finnetrolle.businesslogicvalidation.ValidationResult;
import ru.finnetrolle.businesslogicvalidation.Violation;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ValidationV3Tutorial {

    @Test
    public void example() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<String> strings = Arrays.asList("abc", "aaa", "lambda");

        RuleGroup<Integer> oddity = RuleGroup.named("Oddity").validating(
                SimpleRule.error("Must be odd", a -> a % 2 == 0),
                SimpleRule.error("Must not be odd", a -> a % 2 == 1));

        RuleGroup<String> length = RuleGroup.named("String length").validating(
                SimpleRule.error("Must be lesser that 4 symbols", a -> a.length() < 4));

        ValidationResult result = ValidationResult
                .validate(numbers).by(oddity)
                .and(strings).by(length)
                .andNothingBy(SimpleRule.error("2 * 2 != 5", a -> 2 * 2 != 5));

        assert result.getViolationsList().size() == 11;
    }

    @Test
    public void simpleValidation() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        ValidationResult result = ValidationResult
                .validate(numbers).by(RuleGroup.common().validating(
                        SimpleRule.error("Must be odd", a -> a % 2 == 0)));

        assert result.getViolationsList().size() == 5;
    }

    @Test
    public void simpleRuleExtended() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        ValidationResult result = new ValidationResult();
        result.and(numbers).by(RuleGroup.named("test").validating(
                SimpleRule.error(s -> s % 2 == 0, s -> "Element " + s.toString() + " is bad")
        ));

        assert result.getViolationsList().size() == 5;
        Set<String> violationSet = result.getViolationsList().stream()
                .map(Violation::getMessage)
                .collect(Collectors.toSet());

        assert violationSet.contains("Element 1 is bad");
        assert violationSet.contains("Element 3 is bad");
        assert violationSet.contains("Element 5 is bad");
        assert violationSet.contains("Element 7 is bad");
        assert violationSet.contains("Element 9 is bad");
    }

}

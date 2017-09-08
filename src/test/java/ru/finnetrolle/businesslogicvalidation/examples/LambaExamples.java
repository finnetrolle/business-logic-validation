package ru.finnetrolle.businesslogicvalidation.examples;

import org.junit.Test;
import ru.finnetrolle.businesslogicvalidation.RuleGroup;
import ru.finnetrolle.businesslogicvalidation.SimpleRule;
import ru.finnetrolle.businesslogicvalidation.ValidationResult;

import java.util.Arrays;
import java.util.List;

public class LambaExamples {

    List<String> NAMES = Arrays.asList("James", "Bill", "Io", "J");
    List<String> PASSPORTS = Arrays.asList(
            "1234567890",
            "123",
            "12313864576",
            "6574837562");

    /**
     * Validate
     *      "James" with
     *          length > 3
     */
    @Test
    public void minimalisticValidation() {
        ValidationResult result = ValidationResult.validate("James")
                .with("length > 3", name -> name.length() > 3);
        assert result.passed();

        assert !ValidationResult.validate("Io").with("length > 3", s -> s.length() > 3).passed();
    }

    /**
     * Validate
     *      passport with
     *          length = 10
     */
    @Test
    public void minimalisticListValidation() {
        ValidationResult result = ValidationResult.validate(PASSPORTS)
                .with(p -> p.length() == 10, p -> p + " is not equal 10");
        assert !result.passed();
    }

    /**
     * Validate
     *      passports by
     *          length must be 10,
     *          contains '6'
     *      names by
     *          length must be > 2
     */
    @Test
    public void minimalisticValidationWithGroup() {
        ValidationResult result = ValidationResult
                .validate(PASSPORTS).by(RuleGroup.named("Passport Validation").validating(
                        SimpleRule.error("length = 10", p -> p.length() == 10),
                        SimpleRule.error("contains digit 6", p -> p.contains("6"))))
                .and(NAMES).by(RuleGroup.named("Name Validation").validating(
                        SimpleRule.error("Name length > 2", p -> p.length() > 2)))
                .and("hello").by(
                        SimpleRule.error("Must be equal to hello", p -> p.equals("hello")));
        assert !result.passed();
    }

}

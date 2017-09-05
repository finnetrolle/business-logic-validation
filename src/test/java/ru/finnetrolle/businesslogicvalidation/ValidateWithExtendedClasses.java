package ru.finnetrolle.businesslogicvalidation;

import org.junit.*;
import ru.finnetrolle.businesslogicvalidation.extendedRules.MultipleViolationsRule;
import ru.finnetrolle.businesslogicvalidation.extendedRules.MustBeMaleIfOldRule;
import ru.finnetrolle.businesslogicvalidation.extendedRules.Person;

import java.util.Arrays;
import java.util.List;

public class ValidateWithExtendedClasses {

    @Test
    public void example() {
        List<Person> persons = Arrays.asList(
                new Person("Mike", "Piters", "Jakob", 12, true),
                new Person("Alice", "Waters", "Anna", 30, false),
                new Person("Johnson", "Bark", "Pete", 26, true)
        );

        List<String> strings = Arrays.asList(
                "John says hello",
                "alabama"
        );

        ValidationResult result = new ValidationEngine()
                .validateGroup(RuleGroup.named("Person").validating(new MustBeMaleIfOldRule()), persons)
                .validateGroup(RuleGroup.named("String").validating(new MultipleViolationsRule()), strings)
                .getResult();

        System.out.println(result);
    }

}

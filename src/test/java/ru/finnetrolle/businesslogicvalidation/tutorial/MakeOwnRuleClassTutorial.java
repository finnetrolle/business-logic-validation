package ru.finnetrolle.businesslogicvalidation.tutorial;

import org.junit.Test;
import ru.finnetrolle.businesslogicvalidation.*;
import ru.finnetrolle.businesslogicvalidation.dto.ValidationResult;

/**
 * Created by finnetrolle on 07.03.2017.
 */
public class MakeOwnRuleClassTutorial {

    @Test
    public void makeOwnRuleClassTutorial() {
        ValidationResult result = Engine.validate("some good string").forRules(
                new MyOwnRule("some good string"),
                new MyOwnRule("another standard")
        );
        assert result.getViolations().size() == 1;
        assert result.getLevel() == ViolationLevel.PERMISSIBLE;
        assert result.passed();
    }

    public static class MyOwnRule extends ExtendableRule<String> {

        private final String standard;

        // You must make your own constructor, where you can describe rule' code, level and message
        // Feel free to insert some data into rule
        public MyOwnRule(String standard) {
            // use DSL to make your parent constructor call simple and readable
            super(permissible(Descriptor.rule(101, "rule message")));
            this.standard = standard;
        }

        // This is a method you must implement
        @Override
        public boolean validateObject(String object) {
            return object.equals(standard);
        }
    }

}

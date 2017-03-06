package ru.finnetrolle.businesslogicvalidation.tutorial;

import org.junit.Test;
import ru.finnetrolle.businesslogicvalidation.Engine;
import ru.finnetrolle.businesslogicvalidation.Rule;
import ru.finnetrolle.businesslogicvalidation.ViolationLevel;
import ru.finnetrolle.businesslogicvalidation.dto.ValidationResult;

/**
 * Created by finnetrolle on 07.03.2017.
 */
public class VerySpecificOwnRuleTutorial {

    @Test
    public void makeYourOwnSpecificRule() {
        ValidationResult result = Engine.validate("this string can't pass rule").forRule(new SpecificRule());
        assert !result.passed();
        assert result.getViolations().get(0).getMessage().equals("Failed string:this string can't pass rule");
    }

    public static class SpecificRule extends Rule<String> {

        @Override
        protected boolean validate(String value) {
            return value.length() > 1000;
        }

        @Override
        protected String getMessage(String value) {
            return "Failed string:" + value;
        }

        @Override
        protected ViolationLevel getViolationLevel() {
            return ViolationLevel.CRITICAL;
        }

        @Override
        protected Integer getCode() {
            return 600;
        }
    }
}

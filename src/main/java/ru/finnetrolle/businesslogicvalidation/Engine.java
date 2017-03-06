package ru.finnetrolle.businesslogicvalidation;

import ru.finnetrolle.businesslogicvalidation.dto.ValidationResult;

import java.util.Collections;
import java.util.List;

/**
 * Business Logic Validation
 * Created by finnetrolle on 07.03.2017.
 */

/**
 * This is an entry point class
 */
public final class Engine {

    public static <IN> ValidationBuilder<IN> validate(IN value) {
        return new ValidationBuilder<>(value);
    }

    public static <IN> ValidationBuilder<IN> validate(List<IN> values) {
        return new ValidationBuilder<>(values);
    }

    public final static class ValidationBuilder<IN> {
        private final List<IN> objects;

        private ValidationBuilder(List<IN> objects) {
            this.objects = objects;
        }

        private ValidationBuilder(IN object) {
            this.objects = Collections.singletonList(object);
        }

        @SafeVarargs
        public final ValidationResult forRules(Rule<IN>... rules) {
            return ValidationEngine.create(rules).validate(objects);
        }

        public ValidationResult forRule(Rule<IN> rule) {
            return ValidationEngine.create(rule).validate(objects);
        }
    }

}

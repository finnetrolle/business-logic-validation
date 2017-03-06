package ru.finnetrolle.businesslogicvalidation;

import ru.finnetrolle.businesslogicvalidation.dto.Descriptor;
import ru.finnetrolle.businesslogicvalidation.dto.ViolationLevel;

import java.util.function.Predicate;

/**
 * Created by finnetrolle on 06.03.2017.
 */
public class RuleBuilder {

    private final Descriptor descriptor;
    private final ViolationLevel level;

    public static RuleBuilder getInstance(Descriptor descriptor, ViolationLevel level) {
        return new RuleBuilder(descriptor, level);
    }

    private RuleBuilder(Descriptor descriptor, ViolationLevel level) {
        this.descriptor = descriptor;
        this.level = level;
    }

    public <IN> Rule<IN> requires(Predicate<IN> predicate) {
        switch (level) {
            case ERROR: return LambdaRule.error(descriptor, predicate);
            case NOTICE: return LambdaRule.notice(descriptor, predicate);
            case CRITICAL: return LambdaRule.critical(descriptor, predicate);
            case PERMISSIBLE: return LambdaRule.permissible(descriptor, predicate);
        }
        throw new IllegalArgumentException("Bad violation level");
    }

    public <IN> Rule<IN> requires(Validating<IN> predicate) {
        switch (level) {
            case ERROR: return SimpleRule.error(descriptor, predicate);
            case NOTICE: return SimpleRule.notice(descriptor, predicate);
            case CRITICAL: return SimpleRule.critical(descriptor, predicate);
            case PERMISSIBLE: return SimpleRule.permissible(descriptor, predicate);
        }
        throw new IllegalArgumentException("Bad violation level");
    }
}

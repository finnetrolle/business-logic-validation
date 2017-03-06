package ru.finnetrolle.businesslogicvalidation;

/**
 * Business Logic Validation
 * Created by finnetrolle on 07.03.2017.
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

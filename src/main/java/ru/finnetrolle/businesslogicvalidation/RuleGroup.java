package ru.finnetrolle.businesslogicvalidation;

import java.util.*;

public class RuleGroup<ELEMENT> {

    private final List<Rule<ELEMENT>> orderedListOfRules;
    private final String name;

    private RuleGroup(List<Rule<ELEMENT>>orderedListOfRules, String name) {
        this.orderedListOfRules = orderedListOfRules;
        this.name = name;
    }

    public static RuleGroupBuilder named(String name) {
        return new RuleGroupBuilder(name);
    }

    public static RuleGroupBuilder common() {
        return new RuleGroupBuilder(Violation.DEFAULT_SHARD);
    }

    public static final class RuleGroupBuilder {
        private final String name;

        RuleGroupBuilder(String name) {
            this.name = name;
        }

        public final <ELEMENT> RuleGroup<ELEMENT> validating(List<Rule<ELEMENT>> rules) {
            return new RuleGroup<>(rules, name);
        }

        @SafeVarargs
        public final <ELEMENT> RuleGroup<ELEMENT> validating(Rule<ELEMENT>... rules) {
            return new RuleGroup<>(Arrays.asList(rules), name);
        }

    }

    public List<Violation> validate(Collection<ELEMENT> elements) {
        List<Violation> violations = new ArrayList<>();
        for (ELEMENT element : elements) {
            for (Rule<ELEMENT> rule : orderedListOfRules) {
                List<Violation> valRes = rule.check(element, name);
                violations.addAll(valRes);
                if (!valRes.isEmpty() && (rule.getLevel() == ViolationLevel.CRITICAL || rule instanceof Stopper)) {
                    break;
                }
            }
        }
        return violations;
    }


}

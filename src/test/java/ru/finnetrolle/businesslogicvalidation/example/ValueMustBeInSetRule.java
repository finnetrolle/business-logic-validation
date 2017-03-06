package ru.finnetrolle.businesslogicvalidation.example;

import ru.finnetrolle.businesslogicvalidation.ExtendableRule;

import java.util.Set;

import static ru.finnetrolle.businesslogicvalidation.Descriptor.rule;

/**
 * Business Logic Validation
 * Created by finnetrolle on 25.11.2015.
 */
public class ValueMustBeInSetRule extends ExtendableRule<String> {

    private final Set<String> set;

    public ValueMustBeInSetRule(Set<String> set) {
        super(permissible(rule(101, "Value must be in set")));
        this.set = set;
    }

    @Override
    public boolean validateObject(String object) {
        return set.contains(object);
    }

}
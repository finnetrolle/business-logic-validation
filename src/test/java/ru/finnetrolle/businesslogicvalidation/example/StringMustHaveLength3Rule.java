package ru.finnetrolle.businesslogicvalidation.example;

import ru.finnetrolle.businesslogicvalidation.ExtendableRule;

import static ru.finnetrolle.businesslogicvalidation.Descriptor.rule;

/**
 * Created by finnetrolle on 07.03.2017.
 */
public class StringMustHaveLength3Rule extends ExtendableRule<String> {

    public StringMustHaveLength3Rule() {
        super(critical(rule(100, "String must have length = 3")));
    }

    @Override
    public boolean validateObject(String object) {
        return object.length() > 3;
    }
}

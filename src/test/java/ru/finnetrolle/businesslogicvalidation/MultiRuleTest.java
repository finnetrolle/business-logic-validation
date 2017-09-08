package ru.finnetrolle.businesslogicvalidation;

import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.*;

public class MultiRuleTest {

    @Test
    public void alwaysReturnTrueValidation() throws Exception {
        Rule<String> rule = MultiRule.create(a ->
                Collections.singletonList(Violation.build("bad", ViolationLevel.ERROR)));
        assert rule.validate("some");
    }



}
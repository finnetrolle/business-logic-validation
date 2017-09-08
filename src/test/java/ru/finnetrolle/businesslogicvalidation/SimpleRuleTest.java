package ru.finnetrolle.businesslogicvalidation;

import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleRuleTest {
    @Test
    public void error() throws Exception {
        SimpleRule rule = SimpleRule.error("Hello", a -> a.toString().equals("a"));
        assert rule.getLevel() == ViolationLevel.ERROR;

        rule = SimpleRule.error(a -> true, Object::toString);
        assert rule.validate(95);
        assert rule.getMessage(95).equals("95");
    }

    @Test
    public void permissible() throws Exception {
        SimpleRule rule = SimpleRule.permissible("Hello", a -> a.toString().equals("a"));
        assert rule.getLevel() == ViolationLevel.PERMISSIBLE;

        rule = SimpleRule.permissible(a -> true, Object::toString);
        assert rule.validate(95);
        assert rule.getMessage(95).equals("95");
    }

    @Test
    public void critical() throws Exception {
        SimpleRule rule = SimpleRule.critical("Hello", a -> a.toString().equals("a"));
        assert rule.getLevel() == ViolationLevel.CRITICAL;

        rule = SimpleRule.critical(a -> true, Object::toString);
        assert rule.validate(95);
        assert rule.getMessage(95).equals("95");
    }

    @Test
    public void notice() throws Exception {
        SimpleRule rule = SimpleRule.notice("Hello", a -> a.toString().equals("a"));
        assert rule.getLevel() == ViolationLevel.NOTICE;

        rule = SimpleRule.notice(a -> true, Object::toString);
        assert rule.validate(95);
        assert rule.getMessage(95).equals("95");
    }

}
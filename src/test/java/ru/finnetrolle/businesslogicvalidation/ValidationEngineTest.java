package ru.finnetrolle.businesslogicvalidation;

import org.junit.Before;
import org.junit.Test;
import ru.finnetrolle.businesslogicvalidation.dto.ValidationResult;
import ru.finnetrolle.businesslogicvalidation.example.StringMustHaveLength3Rule;
import ru.finnetrolle.businesslogicvalidation.example.ValueMustBeInSetRule;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Business Logic Validation
 * Created by finnetrolle on 25.11.2015.
 */
public class ValidationEngineTest {

    private final List<String> VALUES = new ArrayList<>();
    private final Set<String> SET = new HashSet<>();

    @Before
    public void setUp() throws Exception {
        VALUES.add("One");
        VALUES.add("Two");
        VALUES.add("Three");
        VALUES.add(null);

        SET.add("Three");
    }

    @Test
    public void testValidateCollection() throws Exception {
        ValidationResult result = Engine.validate(VALUES)
                .forRule(new ValueMustBeInSetRule(SET));

        assertEquals(ViolationLevel.PERMISSIBLE, result.getLevel());
        assertEquals(3, result.getViolations().size());
        assertEquals(true, result.passed());
    }

    @Test
    public void testValidateSingle() throws Exception {
        ValidationResult result = Engine.validate("Three")
                .forRule(new ValueMustBeInSetRule(SET));

        assertEquals(true, result.passed());
        assertEquals(0, result.getViolations().size());
        assertEquals(ViolationLevel.NOTICE, result.getLevel());
    }

    @Test
    public void testStopPropagationRule() throws Exception {
        ValidationResult result = Engine.validate(VALUES).forRules(
                LambdaRule.critical(Descriptor.rule(404, "null value"), Objects::nonNull),
                new StringMustHaveLength3Rule());

        assertFalse(result.passed());
        assertEquals(ViolationLevel.CRITICAL, result.getLevel());
        assertEquals(3, result.getViolations().size());
    }

    @Test(expected = NullPointerException.class)
    public void testNPE() throws Exception {
        ValidationResult result = Engine.validate(VALUES)
                .forRule(new StringMustHaveLength3Rule());
    }

}
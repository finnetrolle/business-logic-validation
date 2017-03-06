package ru.finnetrolle.businesslogicvalidation.tutorial;

import org.junit.Test;
import ru.finnetrolle.businesslogicvalidation.Engine;
import ru.finnetrolle.businesslogicvalidation.Rule;
import ru.finnetrolle.businesslogicvalidation.ViolationLevel;
import ru.finnetrolle.businesslogicvalidation.dto.ValidationResult;

import java.util.Arrays;
import java.util.List;

import static ru.finnetrolle.businesslogicvalidation.Descriptor.rule;
import static ru.finnetrolle.businesslogicvalidation.Rule.notice;
import static ru.finnetrolle.businesslogicvalidation.SimpleRule.critical;

/**
 * Created by finnetrolle on 07.03.2017.
 */
public class UsingLambdaTutorial {

    @Test
    public void useLambdaToValidateEntity() {
        Entity goodEntity = new Entity("good entity", "777");
        Entity badEntity = new Entity("bad entity", "666");

        ValidationResult result = Engine.validate(goodEntity)
                .forRule(critical(rule(101, "Code must not be 666"), e -> !e.getCode().equals("666")));
        assert result.passed();

        result = Engine.validate(badEntity)
                .forRule(critical(rule(101, "Code must not be 666")).requires(s -> !s.getCode().equals("666")));
        assert !result.passed();

        List<Entity> entities = Arrays.asList(goodEntity, badEntity);

        Rule<Entity> oneRule = critical(rule(101, "Code must not be 666")).requires(e -> !e.getCode().equals("666"));

        result = Engine.validate(entities)
                .forRules(
                        notice(rule(100, "Code is lucky!")).requires(e -> e.getCode().equals("777")),
                        notice(rule(102, "Code is from devil")).requires(e -> e.getCode().equals("666")),
                        oneRule
                );
        assert !result.passed();
        assert result.getLevel() == ViolationLevel.CRITICAL;
        assert result.getViolations().size() == 3;
    }

    public static class Entity {
        private final String name;
        private final String code;

        public Entity(String name, String code) {
            this.name = name;
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public String getCode() {
            return code;
        }
    }

}

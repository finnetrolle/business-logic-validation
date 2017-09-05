package ru.finnetrolle.businesslogicvalidation;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

/**
 * Business Logic Validation
 * Created by finnetrolle on 25.11.2015.
 */

/**
 * Class holds violation info
 */
public class Violation {

    public static final String DEFAULT_SHARD = "default";

    private final Map<String, Object> arguments;
    private final String message;
    private final ViolationLevel level;
    private final String shardName;

    private Violation(Map<String, Object> arguments, String message, ViolationLevel level, String shardName) {
        this.arguments = arguments;
        this.message = message;
        this.level = level;
        this.shardName = shardName;
    }

    public static Violation build(Map<String, Object> arguments, String message, ViolationLevel level, String shardName) {
        return new Violation(arguments, message, level, shardName);
    }

    public static Violation build(String message, ViolationLevel level) {
        return new Violation(Collections.emptyMap(), message, level, Violation.DEFAULT_SHARD);
    }

    public static Violation notice(String message) {
        return new Violation(Collections.emptyMap(), message, ViolationLevel.NOTICE, Violation.DEFAULT_SHARD);
    }

    public static Violation permissible(String message) {
        return new Violation(Collections.emptyMap(), message, ViolationLevel.PERMISSIBLE, Violation.DEFAULT_SHARD);
    }

    public static Violation error(String message) {
        return new Violation(Collections.emptyMap(), message, ViolationLevel.ERROR, Violation.DEFAULT_SHARD);
    }

    public static Violation critical(String message) {
        return new Violation(Collections.emptyMap(), message, ViolationLevel.CRITICAL, Violation.DEFAULT_SHARD);
    }


    /**
     * Create builder for shard-defined rule
     *
     * @param rule      - rule
     * @param shardName - name of shard
     * @param <ELEMENT> - type of element to validate
     */
    public static <ELEMENT> ViolationBuilder<ELEMENT> builder(Rule<ELEMENT> rule, String shardName) {
        return new ViolationBuilder<>(rule, shardName);
    }

    /**
     * Create builder for simple rule
     * Default shard will be chosen
     *
     * @param rule      - rule
     * @param <ELEMENT> - type of element to validate
     */
    public static <ELEMENT> ViolationBuilder<ELEMENT> builder(Rule<ELEMENT> rule) {
        return new ViolationBuilder<>(rule, Violation.DEFAULT_SHARD);
    }

    /**
     * @return arguments map
     */
    public Map<String, Object> getArguments() {
        return arguments;
    }

    /**
     * @return shard name
     */
    public String getShardName() {
        return shardName;
    }

    /**
     * @return violation message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @return violation level
     */
    public ViolationLevel getViolationLevel() {
        return level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Violation violation = (Violation) o;
        return Objects.equals(arguments, violation.arguments) &&
                Objects.equals(message, violation.message) &&
                level == violation.level &&
                Objects.equals(shardName, violation.shardName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(arguments, message, level, shardName);
    }

    @Override
    public String toString() {
        return "Violation{" +
                "arguments=" + arguments +
                ", message='" + message + '\'' +
                ", level=" + level +
                ", shardName='" + shardName + '\'' +
                '}';
    }

    public static class ViolationBuilder<ELEMENT> {

        private final Rule<ELEMENT> rule;
        private final String shardName;

        public ViolationBuilder(Rule<ELEMENT> rule, String shardName) {
            this.rule = rule;
            this.shardName = shardName;
        }

        public Violation build(ELEMENT element) {
            return new Violation(rule.getArguments(element), rule.getMessage(element), rule.getLevel(), shardName);
        }
    }
}

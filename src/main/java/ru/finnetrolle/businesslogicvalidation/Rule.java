package ru.finnetrolle.businesslogicvalidation;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Business Logic Validation
 * Created by finnetrolle on 25.11.2015.
 */

/**
 * Parent of all rules
 *
 * @param <ELEMENT> type of value
 */
public abstract class Rule<ELEMENT> {

    protected final String description;

    public Rule(String description) {
        this.description = description;
    }

    /**
     * Implement your validation logic here
     *
     * @param element
     * @return
     */
    abstract protected boolean validate(ELEMENT element);

    /**
     * Override this method to output your violation message here
     *
     * @param element
     * @return
     */
    public String getMessage(ELEMENT element) {
        return description;
    }

    /**
     * Override this method to change default level
     *
     * @return
     */
    public ViolationLevel getLevel() {
        return ViolationLevel.ERROR;
    }

    private List<Violation> produceViolations(ELEMENT element, String groupName) {
        return Collections.singletonList(Violation.builder(this, groupName).build(element));
    }

    /**
     * Override this method to define key values from your element
     *
     * @param element
     * @return
     */
    public Map<String, Object> getArguments(ELEMENT element) {
        return Collections.emptyMap();
    }

    /**
     * Override this method to enable multiple violations per one validation
     * @param element
     * @param groupName
     * @return
     */
    protected List<Violation> check(ELEMENT element, String groupName) {
        if (validate(element)) {
            return Collections.emptyList();
        } else {
            return produceViolations(element, groupName);
        }
    }

}

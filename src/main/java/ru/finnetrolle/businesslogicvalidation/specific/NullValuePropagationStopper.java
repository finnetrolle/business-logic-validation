package ru.finnetrolle.businesslogicvalidation.specific;

import ru.finnetrolle.businesslogicvalidation.dto.ViolationLevel;

/**
 * Business Logic Validation
 * Created by finnetrolle on 25.11.2015.
 */

/**
 * Special class, that will stop validation chain if value is null
 * This can be useful if any of rules next in chain use value.some_method and can throw NPE
 * @param <T> type of value
 */
public class NullValuePropagationStopper<T> extends NoDataRule<T> {

    private String message;
    private final static String DEFAULT_MESSAGE = "Value is null";

    private NullValuePropagationStopper(String message) {
        this.message = message;
    }

    /**
     * Use this factory method to create object
     * @param message message to show
     * @param <T> type of value
     * @return constructed object
     */
    public static <T> NullValuePropagationStopper<T> create(String message) {
        return new NullValuePropagationStopper<>(message);
    }

    /**
     * Use this factory method to create object
     * @param <T> type of value
     * @return constructed object
     */
    public static <T> NullValuePropagationStopper<T> create() {
        return new NullValuePropagationStopper<>(DEFAULT_MESSAGE);
    }

    @Override
    protected boolean validate(T value) {
        return value != null;
    }

    @Override
    protected String getName() {
        return "NotNull Stop Propagation";
    }

    @Override
    protected String getMessage(T value) {
        return message;
    }

    @Override
    protected ViolationLevel getViolationLevel() {
        return ViolationLevel.CRITICAL;
    }

    @Override
    protected Integer getCode() {
        return 666;
    }
}

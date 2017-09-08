package ru.finnetrolle.businesslogicvalidation;

public class SimpleRule<V> extends Rule<V> {

    private final Validating<V> delegate;
    private final MessageGenerator<V> messageGenerator;
    private final ViolationLevel level;

    public SimpleRule(String description, Validating<V> delegate) {
        super(description);
        this.delegate = delegate;
        this.messageGenerator = null;
        this.level = ViolationLevel.ERROR;
    }

    public SimpleRule(Validating<V> delegate, MessageGenerator<V> messageGenerator) {
        super(null);
        this.delegate = delegate;
        this.messageGenerator = messageGenerator;
        this.level = ViolationLevel.ERROR;
    }

    public SimpleRule(String description, Validating<V> delegate, ViolationLevel level) {
        super(description);
        this.delegate = delegate;
        this.messageGenerator = null;
        this.level = level;
    }

    public SimpleRule(Validating<V> delegate, MessageGenerator<V> messageGenerator, ViolationLevel level) {
        super(null);
        this.delegate = delegate;
        this.messageGenerator = messageGenerator;
        this.level = level;
    }

    public static <V> SimpleRule<V> error(String description, Validating<V> delegate) {
        return new SimpleRule<>(description, delegate, ViolationLevel.ERROR);
    }

    public static <V> SimpleRule<V> error(Validating<V> delegate, MessageGenerator<V> messageGenerator) {
        return new SimpleRule<>(delegate, messageGenerator, ViolationLevel.ERROR);
    }

    public static <V> SimpleRule<V> permissible(String description, Validating<V> delegate) {
        return new SimpleRule<>(description, delegate, ViolationLevel.PERMISSIBLE);
    }

    public static <V> SimpleRule<V> permissible(Validating<V> delegate, MessageGenerator<V> messageGenerator) {
        return new SimpleRule<>(delegate, messageGenerator, ViolationLevel.PERMISSIBLE);
    }

    public static <V> SimpleRule<V> critical(String description, Validating<V> delegate) {
        return new SimpleRule<>(description, delegate, ViolationLevel.CRITICAL);
    }

    public static <V> SimpleRule<V> critical(Validating<V> delegate, MessageGenerator<V> messageGenerator) {
        return new SimpleRule<>(delegate, messageGenerator, ViolationLevel.CRITICAL);
    }

    public static <V> SimpleRule<V> notice(String description, Validating<V> delegate) {
        return new SimpleRule<>(description, delegate, ViolationLevel.NOTICE);
    }

    public static <V> SimpleRule<V> notice(Validating<V> delegate, MessageGenerator<V> messageGenerator) {
        return new SimpleRule<>(delegate, messageGenerator, ViolationLevel.NOTICE);
    }

    @Override
    public String getMessage(V v) {
        if (messageGenerator != null) {
            return messageGenerator.produceMessage(v);
        } else
            return super.getMessage(v);
    }

    @Override
    protected boolean validate(V v) {
        return delegate.validateObject(v);
    }

    @Override
    public ViolationLevel getLevel() {
        return level;
    }
}

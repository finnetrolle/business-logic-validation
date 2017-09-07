package ru.finnetrolle.businesslogicvalidation;

public class SimpleRule<V> extends Rule<V> {

    private final Validating<V> delegate;
    private final MessageGenerator<V> messageGenerator;

    public SimpleRule(String description, Validating<V> delegate) {
        super(description);
        this.delegate = delegate;
        this.messageGenerator = null;
    }

    public SimpleRule(Validating<V> delegate, MessageGenerator<V> messageGenerator) {
        super(null);
        this.delegate = delegate;
        this.messageGenerator = messageGenerator;
    }

    public static <V> SimpleRule<V> create(String description, Validating<V> delegate) {
        return new SimpleRule<>(description, delegate);
    }

    public static <V> SimpleRule<V> create(Validating<V> delegate, MessageGenerator<V> messageGenerator) {
        return new SimpleRule<>(delegate, messageGenerator);
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
}

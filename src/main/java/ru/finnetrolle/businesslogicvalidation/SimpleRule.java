package ru.finnetrolle.businesslogicvalidation;

public class SimpleRule<V> extends Rule<V> {

    private final Validating<V> delegate;

    public SimpleRule(String description, Validating<V> delegate) {
        super(description);
        this.delegate = delegate;
    }

    @Override
    protected boolean validate(V v) {
        return delegate.validateObject(v);
    }

    public static <V> SimpleRule<V> create(String description, Validating<V> delegate) {
        return new SimpleRule<>(description, delegate);
    }
}

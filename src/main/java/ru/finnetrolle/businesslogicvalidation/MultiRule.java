package ru.finnetrolle.businesslogicvalidation;

import java.util.List;

public class MultiRule<ELEMENT> extends Rule<ELEMENT> {

    private final MultiValidating<ELEMENT> delegate;

    public MultiRule(MultiValidating<ELEMENT> delegate) {
        super(null);
        this.delegate = delegate;
    }

    public static <T> MultiRule<T> create(MultiValidating<T> delegate) {
        return new MultiRule<>(delegate);
    }

    @Override
    protected boolean validate(ELEMENT element) {
        return true;
    }

    @Override
    protected List<Violation> check(ELEMENT element, String groupName) {
        return delegate.check(element);
    }
}

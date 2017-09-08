package ru.finnetrolle.businesslogicvalidation;

import java.util.List;

@FunctionalInterface
public interface MultiValidating<V> {

    List<Violation> check(V element);

}

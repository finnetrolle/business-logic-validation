package ru.finnetrolle.businesslogicvalidation.specific;

import ru.finnetrolle.businesslogicvalidation.dto.ViolationLevel;
import ru.finnetrolle.businesslogicvalidation.Rule;

/**
 * Business Logic Validation
 * Created by finnetrolle on 25.11.2015.
 */

/**
 * Class represents Rule with violation level = ERROR
 * @param <V> type of value
 * @param <D> type of data
 */
public abstract class ErrorRule<V, D> extends Rule<V, D> {

    public ErrorRule(D data) {
        super(data);
    }

    @Override
    protected ViolationLevel getViolationLevel() {
        return ViolationLevel.ERROR;
    }
}

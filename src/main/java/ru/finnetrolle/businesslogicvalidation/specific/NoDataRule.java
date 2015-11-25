package ru.finnetrolle.businesslogicvalidation.specific;

import ru.finnetrolle.businesslogicvalidation.Rule;

/**
 * Business Logic Validation
 * Created by finnetrolle on 25.11.2015.
 */

/**
 * This class makes life easier if you need to create Rule without inner data
 * @param <V> type of value
 */
public abstract class NoDataRule<V> extends Rule<V, Object> {

    public NoDataRule() {
        super(null);
    }

}

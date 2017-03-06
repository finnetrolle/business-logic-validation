package ru.finnetrolle.businesslogicvalidation;

/**
 * Created by finnetrolle on 06.03.2017.
 */

@FunctionalInterface
public interface Validating<IN> {

    boolean validateObject(IN object);

}

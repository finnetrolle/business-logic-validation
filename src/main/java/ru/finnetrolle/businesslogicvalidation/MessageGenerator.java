package ru.finnetrolle.businesslogicvalidation;

@FunctionalInterface
public interface MessageGenerator<ELEMENT> {

    String produceMessage(ELEMENT element);

}

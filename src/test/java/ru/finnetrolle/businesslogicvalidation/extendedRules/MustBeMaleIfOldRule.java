package ru.finnetrolle.businesslogicvalidation.extendedRules;

import ru.finnetrolle.businesslogicvalidation.Rule;
import ru.finnetrolle.businesslogicvalidation.ViolationLevel;

import java.util.HashMap;
import java.util.Map;

public class MustBeMaleIfOldRule extends Rule<Person> {

    public MustBeMaleIfOldRule() {
        super("Only male can be older than 24");
    }

    @Override
    public Map<String, Object> getArguments(Person person) {
        Map<String, Object> map = new HashMap<>();
        map.put("Name", person.getName());
        map.put("Surname", person.getSurname());
        return map;
    }

    @Override
    public ViolationLevel getLevel() {
        return ViolationLevel.PERMISSIBLE;
    }

    @Override
    public String getMessage(Person person) {
        return person.getName() + " " + person.getSurname() + " is older than 24 but not male";
    }

    @Override
    protected boolean validate(Person person) {
        if (person.getAge() > 24) {
            return person.isMale();
        } else {
            return true;
        }
    }
}

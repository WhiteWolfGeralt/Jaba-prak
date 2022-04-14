package ru.msu.cmc.webprak.DAO;

import lombok.Builder;
import lombok.Getter;
import ru.msu.cmc.webprak.models.Person;

import java.util.List;

public interface PersonDAO extends CommonDAO<Person, Long> {

    List<Person> getAllPersonByName(String personName);
    Person getSinglePersonByName(String personName);
    String getYearsOfLife(Person person);
    List<Person> getByFilter(Filter filter);

    @Builder
    @Getter
    class Filter {
        private String name;
    }

    static Filter.FilterBuilder getFilterBuilder() {
        return Filter.builder();
    }
}

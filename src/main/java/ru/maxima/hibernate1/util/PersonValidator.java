package ru.maxima.hibernate1.util;


import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.maxima.hibernate1.entity.Person;
import ru.maxima.hibernate1.services.PeopleService;

/**
 * @author AramaJava 05.08.2023
 */
@Component
public class PersonValidator implements Validator {

    @Autowired
    private final PeopleService peopleService;

    @Autowired
    public PersonValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public boolean supports(@Nonnull Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override

    public void validate(@Nonnull Object target,@Nonnull Errors errors) {
        Person person = (Person) target;
        // посмотреть, есть ли в базе данных человек с такой же почтой и с id отличным от текущего
        String email = person.getEmail();
        if (peopleService.findByEmail(email) != null) {
            Person founded = (peopleService.findByEmail(person.getEmail()));
            if (founded.getId() != person.getId()) {
                errors.rejectValue("email", "", "Эта почта уже используется!");
            }
        }
    }
}

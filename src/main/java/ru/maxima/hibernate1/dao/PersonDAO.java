package ru.maxima.hibernate1.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.maxima.hibernate1.entity.Person;

import java.util.List;


/**
 * @author AramaJava 26.07.2023
 */


@Component
public class PersonDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public PersonDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Person> index() {
        return sessionFactory.getCurrentSession().createQuery("From Person", Person.class).getResultList();
    }

    @Transactional(readOnly = true)
    public Person show(int id) {
        return sessionFactory.getCurrentSession().get(Person.class, id);
    }

    @Transactional
    public void save(Person person) {
        sessionFactory.getCurrentSession().persist(person);
    }

    @Transactional
    public void update(int id, Person updatedPerson) {
        Session session = sessionFactory.getCurrentSession();
        Person personBeUpdated = session.get(Person.class, id);
        personBeUpdated.setName(updatedPerson.getName());
        personBeUpdated.setSurname(updatedPerson.getSurname());
        personBeUpdated.setAge(updatedPerson.getAge());
        personBeUpdated.setEmail(updatedPerson.getEmail());

    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Person.class, id));
    }

    @Transactional
    public List<Person> findByName(String keyword) {
        Session session = sessionFactory.getCurrentSession();
        String sql = "From Person p where p.name like '%" + keyword + "%'";
        return session.createQuery(sql, Person.class).getResultList();
    }

    @Transactional
    public List<Person> findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        String sql = "From Person p where p.id = " + id ;
        return session.createQuery(sql, Person.class).getResultList();
    }
}

package ru.maxima.hibernate1.dao;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
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

        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("select p from Person p", Person.class).getResultList();
    }

    @Transactional(readOnly = true)
    public Person show(int id) {

        return sessionFactory.getCurrentSession().get(Person.class, id);
    }


    // перегрузил метод show еще и через емайл

    public  Person show(String email)  {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Person> cr = cb.createQuery(Person.class);

        Root<Person> root = cr.from(Person.class);
        cr.select(root).where(cb.equal(root.get("email"), email));  //here you pass a class field, not a table column (in this example they are called the same)

        Query<Person> query = session.createQuery(cr);
        query.setMaxResults(1);
        List<Person> result = query.getResultList();
        return result.get(0);
    }




    @Transactional
    public void save(Person person) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(person);
    }

    @Transactional
    public void update (int id, Person personFromForm) {
        Session session = sessionFactory.getCurrentSession();
        Person personBeUpdated = session.get(Person.class, id);

        personBeUpdated.setName(personFromForm.getName());
        personBeUpdated.setSurname(personFromForm.getSurname());
        personBeUpdated.setAge(personFromForm.getAge());
        personBeUpdated.setEmail(personFromForm.getEmail());

        session.persist(personBeUpdated);
    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Person.class, id));
    }

  /*  @Transactional
    public  List<Person> findByName(String keyword) {
        String sql = "select * from person p where p.name like ?";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Person.class),keyword+'%');
    }*/

}
